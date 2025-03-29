
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

    public ManagerAtencion(ListaCajas listaCajas, String nombreSucursal) {
        this.listaCajas = listaCajas;
        this.nombreSucursal = nombreSucursal;
    }
    
    public void mostrarMenuAtencion(){
        boolean continuar = true;
        
        while (continuar){
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(//Muestra el menú de este módulo
                    """
                            Módulo de Atención de Tiquetes
                            1. Ver cajas disponibles
                            2. Atender siguiente en una caja
                            3. Salir"""
            ));
            
            switch (opcion) {
                case 1:
                    mostrarCajas();
                    break;
                case 2:
                    atenderTiquete();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }
    
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
    
    private void atenderTiquete(){
        try {
            int idCaja = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la caja a atender: "));
            
            NodoLista actual = listaCajas.getCabeza();
            while (actual != null){
                Caja caja = actual.getDato();
                
                if (caja.getIdCaja() == idCaja){
                    if (caja.isEmpty()){
                        JOptionPane.showMessageDialog(null, "La caja se encuentra vacía...");
                        return;
                    }
                    Ticket ticket = caja.frente(); // Obtener el primer tiquete sin quitarlo
                    long horaAtencion = System.currentTimeMillis();
                    ticket.setHoraAtencion(horaAtencion);
                    
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
                    return;
                }
                actual = actual.getSiguiente();
            }
            // Si llegó aquí, no se encontró la caja
            JOptionPane.showMessageDialog(null, "No se encontró la caja con ese ID.");
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error al atender tiquete.");
        }
    }
    
    private String obtenerHoraFormateada(long milis){
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }
    
    private void guardarTiqueteAtendido(Ticket ticket, Caja caja) {
        try (FileWriter writer = new FileWriter("atendidos.json", true)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            TicketAtendido atendido = new TicketAtendido(ticket, caja.getIdCaja(), caja.getNombre(), nombreSucursal);
            writer.write(gson.toJson(atendido) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
