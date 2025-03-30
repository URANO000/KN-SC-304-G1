
package org.example.Mod2Atencion;
import org.example.Mod1Cajas.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author marif
 */
public class ManagerAtencion {
    private ListaCajas listaCajas;
    private String nombreSucursal;

//Constructor encargado de recibir la lista de cajas y el nombre de la sucursal
    public ManagerAtencion(ListaCajas listaCajas, String nombreSucursal) {
        this.listaCajas = listaCajas;
        this.nombreSucursal = nombreSucursal;
    }

//Menú principal del módulo    
    public void mostrarMenuAtencion(){
        boolean continuar = true;
        
        while (continuar){
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(//Muestra el menú de este módulo
                    "Módulo de Atención de Clientes\n"
                    +"1. Ver cajas\n"
                    +"2. Atender siguiente en una caja\n"
                    +"3. Salir\n"
            ));
            
            switch (opcion) {
                case 1:
                    mostrarCajas(); // Ver cuántos clientes hay en cada caja
                    break;
                case 2:
                    atenderTiquete(); // Atender a la siguiente persona en una caja específica
                    break;
                case 3:
                    continuar = false; // Salir del menú
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    
// Muestra una ventana por cada caja con su ID, nombre y cantidad de clientes en cola
    private void mostrarCajas(){
        NodoLista actual = listaCajas.getCabeza();
        
        while (actual != null){
            Caja caja = actual.getDato();

        JOptionPane.showMessageDialog(null,
                "Caja ID: " + caja.getIdCaja() + "\n" +
                "Nombre: " + caja.getNombre() + "\n" +
                "Clientes en cola: " + caja.size());

        actual = actual.getSiguiente();           
        }
    }
    
    
// Atiende al primer cliente de una caja específica    
    private void atenderTiquete(){
        try {
            int idCaja = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la caja a atender: "));
            
            NodoLista actual = listaCajas.getCabeza();
            
// Recorre la lista de cajas hasta encontrar la caja con el ID solicitado
            while (actual != null){
                Caja caja = actual.getDato();
                
                if (caja.getIdCaja() == idCaja){
                // Si la caja está vacía, lo notifica
                    if (caja.isEmpty()){
                        JOptionPane.showMessageDialog(null, "La caja se encuentra vacía...");
                        return;
                    }
                    Ticket ticket = caja.frente(); // Obtiene el primer tiquete sin quitarlo
                    long horaAtencion = System.currentTimeMillis();
                    ticket.setHoraAtencion(horaAtencion); // Guarda la hora en el objeto
                    
                    // Mostrar información del tiquete
                    JOptionPane.showMessageDialog(null,
                            "Atendiendo cliente:\n" +
                            "Nombre: " + ticket.getNombre() + "\n" +
                            "Trámite: " + ticket.getTramite() + "\n" +
                            "Hora creación: " + ticket.getHoraCreacion() + "\n" +
                            "Hora atención: " + obtenerHoraFormateada(horaAtencion));
                    
                    // Guardar en archivo atendidos
                    guardarTiqueteAtendido(ticket, caja);

                    // Eliminar de la cola
                    caja.atender();
                    
                    // Actualiza el archivo tiquetes.json eliminando al cliente atendido
                    serializarColasActualizadas();

                    return;
                }
                actual = actual.getSiguiente(); // Siguiente caja en la lista
            }
            // Si llegó aquí, no se encontró la caja
            JOptionPane.showMessageDialog(null, "No se encontró la caja con ese ID.");
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error al atender tiquete.");
        }
    }
    
    /*Este método va sobreescribir los tiquetes json cada vez que las cajas 
    sean actualizadas cada vez que se atiende un cliente*/
    private void serializarColasActualizadas() {
    Serializacionticket serializador = new Serializacionticket();
    serializador.serializarListaCajas(listaCajas); // Guarda el nuevo estado después de la atención
    }
    
    // Formatea la hora para mostrarla en formato legible
    private String obtenerHoraFormateada(long milis){
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }
    
    // Guarda el tiquete atendido en el archivo atendidos.json junto con el ID de caja y sucursal
    private void guardarTiqueteAtendido(Ticket ticket, Caja caja) {
        try (FileWriter writer = new FileWriter("atendidos.json", true)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
         // Se crea una instancia extendida del ticket incluyendo más datos para historial    
            TicketAtendido atendido = new TicketAtendido(ticket, caja.getIdCaja(), caja.getNombre(), nombreSucursal);
            writer.write(gson.toJson(atendido) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
