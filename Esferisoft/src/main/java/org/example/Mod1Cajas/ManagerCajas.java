package org.example.Mod1Cajas;
import org.example.Mod0.*;
import org.example.Mod3.SerializacionColas;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerCajas {
    Serializacionticket serializador = new Serializacionticket();
    ListaCajas listaCajas = serializador.deserializarListaCajas();
    boolean continuar = true;  //Para el menú
    int seleccion; //Para el menú
    SerializacionColas json = new SerializacionColas();  //Para metodo add cajas


    //En esta clase se va a manejar lo correspondiente a las cajas

    public void menuCajas() throws IOException, ParseException {
        boolean continuar = true;
        
        //primero a leer la configuracion del archivo JSON config-------------------------------------
        ConfigSucursal config = ConfigJson.cargarConfiguracion();

        if (config == null) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al cargar la configuracion",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Ahora con la deserialización de tiquetes-------------------------------------------

        if (listaCajas == null){
            //Creo la lista de cajas ---------------------------------
            for(int i = 1; i < config.getTotalCajas() + 1; i++) {   //Un loop de creación de cajas por cada iteración según las cajas totales
                String tipo;
                if(i == 1) {   //Este if lo que hace es asignar el tipo de caja según el ID
                    tipo = "Preferencial";
                } else if(i == 2) {
                    tipo = "Rápida";
                } else {
                    tipo = "Normal";
                }
                Caja caja = new Caja(i, "Caja " + tipo);
                listaCajas.insertar(caja);

            }

            System.out.println(listaCajas.toString());

        } else{
            System.out.println(listaCajas.toString());

        }

        //---------------Ahora sigue la creación del tiquete con JOptionPane---------------------

        try{
            while(continuar) {
                int seleccion; //Lo vuelve una variable local

                try{
                    seleccion = Integer.parseInt(JOptionPane.showInputDialog("""
                            Sistema de tiquetes\s
                            1. Crear nuevo tiquete\s
                            2. Salir"""));
                }catch (Exception e){
                    System.out.println("User hit Cancel!");
                    seleccion = 2;
                    continuar = false;
                }


                switch (seleccion) {
                    case 1:
                        //Metodo de crear un nuevo tiquete
                        crearTicket();
                        break;

                    case 2:
                        // Aquí va la llamada de la clase serializar
                        serializador.serializarListaCajas(listaCajas);
                        continuar = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                        break;

                }


            }
        }catch(Exception e) {
            System.err.println("Ha ocurrido un error!" + e);
        }


    } //FIN de metodo

    public void crearTicket(){
        try{
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID, puede ser número de cédula:"));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad:"));

            //To check opciones moneda
            String opcionesMoneda;

            opcionesMoneda = JOptionPane.showInputDialog("DOLARES o COLONES");

            String tramite = JOptionPane.showInputDialog("Ingrese el trámite a realizar (Depósitos, Servicios, Retiros, Cambio de divisas, Emergencia Ejecutivo)");

            int tramiteAUX = Integer.parseInt(JOptionPane.showInputDialog(
                    """
                        Ingrese el tipo de trámite
                        1.  Preferencial\s
                        2.  Rápida\s
                        3.  Normal:"""
            ));
            String tipoTramite = "";
            switch (tramiteAUX) {
                case 1:
                    tipoTramite = "P";
                    break;
                case 2:
                    tipoTramite = "A";
                    break;
                case 3:
                    tipoTramite = "B";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");

            }


            //Creamos el tiquete y asignamos--------------------------------------------------------------------

            //Aqui lo que necesito es hacer que el tiquete tenga la hora. La lógica la encontré en: https://www.w3schools.com/java/java_date.asp
            LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //Aqui esta el formateo

            String fechaConFormato = fecha.format(formato);

            Ticket ticket = new Ticket(nombre,id, edad, opcionesMoneda, fechaConFormato, -1, tramite, tipoTramite);
            asignarCaja(ticket, listaCajas);

            //Mostrar el tiquete con toda su información--------------------------------------------------
            JOptionPane.showMessageDialog(null,
                    "Ticket creado! \n" +
                            "Nombre: " + nombre + "\n" +
                            "ID: " + id + "\n" +
                            "Edad: " + edad + "\n" +
                            "Opciones de moneda: " + opcionesMoneda +"\n" +
                            "Fecha y hora de creación: " + fechaConFormato +"\n" +
                            "Trámite: " + tramite + "\n" +
                            "Tipo de trámite: " + tipoTramite);

        } catch(Exception e) {
            e.printStackTrace();
        }


    }
    public void asignarCaja(Ticket ticket, ListaCajas lista) {
        NodoLista actual = lista.getCabeza();

        switch (ticket.getTipoTramite()) {
            case "P" -> {
                while (actual != null) {
                    if (actual.getDato().getIdCaja() == 1) {
                        actual.getDato().encolar(ticket);
                        JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Preferencial");
                        int adelante = actual.getDato().size() - 1;
                        personasAdelante(adelante);

                        return;
                    }
                    actual = actual.getSiguiente();
                }
            }
            case "A" -> {
                while (actual != null) {
                    if (actual.getDato().getIdCaja() == 2) {
                        actual.getDato().encolar(ticket);
                        JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Rápida");
                        int adelante = actual.getDato().size() - 1;
                        personasAdelante(adelante);
                        return;

                    }
                    actual = actual.getSiguiente();
                }
            }
            case "B" -> {
                Caja cajaMenosLlena = null;
                int min = Integer.MAX_VALUE; //El mayor entero!!

                while (actual != null) {  //mientra la lista no esté vacía
                    Caja cajaActual = actual.getDato();
                    int size = actual.getDato().size();
                    if (cajaActual.getIdCaja() != 1 && cajaActual.getIdCaja() != 2) {  //Si la caja no es ni preferencial ni rapida
                        if (size <= min) {
                            min = size;
                            cajaMenosLlena = cajaActual; //Constantemente va a cambiar a las cajas de menor tamaño
                        }
                    }
                    actual = actual.getSiguiente(); //Actualizar el puntero a la siguiente caja
                }
                if (cajaMenosLlena != null) { //si la caja no es nula
                    cajaMenosLlena.encolar(ticket);
                    JOptionPane.showMessageDialog(null, "Tiquete asignado a Caja Normal " + cajaMenosLlena.getIdCaja());
                    int adelante = cajaMenosLlena.size() -1;
                    personasAdelante(adelante);
                    json.serializarColas(listaCajas);  //Se crea colas.json

                } else {
                    JOptionPane.showMessageDialog(null, "Oh Snap! X_X  No hay cajas para el tiquete");

                }   //Si la caja es normal
            }
            default -> JOptionPane.showMessageDialog(null, "No hay un trámite válido"); //No debería de pasar!!!
        }


    }

    public void personasAdelante(int adelante){
        if (adelante == 0) {
            JOptionPane.showMessageDialog(null, "Es su turno!");
        } else if(adelante == 1) {
            JOptionPane.showMessageDialog(null, "Hay " + adelante + " persona adelante" );
        } else {
            JOptionPane.showMessageDialog(null, "Hay " + adelante + " personas adelante");
        }

    }



    //Getters & setters


    public ListaCajas getListaCajas() {
        return listaCajas;
    }

    public void setListaCajas(ListaCajas listaCajas) {
        this.listaCajas = listaCajas;
    }
}