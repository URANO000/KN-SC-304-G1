package org.example.Mod1Cajas;
import org.example.Mod0.*;
import org.json.simple.parser.ParseException;

import javax.swing.*;   //Solo la libreria de JAVA SWING
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ManagerCajas {
    private ListaCajas listaCajas = new ListaCajas();
    boolean continuar = true;  //Para el menú



    //En esta clase se va a manejar t odo lo correspondiente a las cajas

    public void menuCajas() throws IOException, ParseException {
        //Primero se deben de crear las cajas de acuerdo a el input del usuario

        int seleccion;

        //primero a leer la configuracion del archivo JSON config-------------------------------------
        ConfigSucursal config = ConfigJson.cargarConfiguracion();

        if (config == null) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al cargar la configuracion",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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


        //---------------Ahora sigue la creación del tiquete con JOptionPane---------------------

        try{
            while(continuar) {

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
                        Serializacionticket serializador = new Serializacionticket();
                        serializador.serializarListaCajas(listaCajas, "tiquetes.json");
                        continuar = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                        break;

                }


            }
        }catch(Exception e) {
            e.printStackTrace();
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

            String tramite = JOptionPane.showInputDialog("Ingrese el trámite a realizar (Depósitos, Servicios, Retiros, Cambio de divisas)");

            int tramiteAUX = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de trámite \n" +
                    "1.  Preferencial  \n" +
                    "2.  Rápida  \n" +
                    "3.  Normal: "));
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

            //Para mostrar las personas al frente
            if (personasAdelante(ticket, listaCajas) == 0) {
                JOptionPane.showMessageDialog(null, "Es su turno!!!");
            }else if (personasAdelante(ticket, listaCajas) ==1){
                JOptionPane.showMessageDialog(null, "Hay " + personasAdelante(ticket, listaCajas) + " persona frente a usted!");
            } else {
                JOptionPane.showMessageDialog(null, "Hay " + personasAdelante(ticket, listaCajas) + "personas frente a usted!");
            }


        } catch(Exception e) {
            e.printStackTrace();
        }


    }
    public void asignarCaja(Ticket ticket, ListaCajas lista) throws IOException, ParseException {
        NodoLista actual = lista.getCabeza();

        if(ticket.getTipoTramite().equals("P")){
            while(actual != null){
                if (actual.getDato().getIdCaja() == 1){
                    actual.getDato().encolar(ticket);
                    JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Preferencial");
                    return;
                }
                actual = actual.getSiguiente();
            }
        }
        else if(ticket.getTipoTramite().equals("A")) {
            while(actual != null){
                if(actual.getDato().getIdCaja() == 2){
                    actual.getDato().encolar(ticket);
                    JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Rápida");
                    return;

                }
                actual = actual.getSiguiente();
            }
        }
        else if(ticket.getTipoTramite().equals("B")){  //Si la caja es normal
            Caja cajaMenosLlena = null;
            int min = Integer.MAX_VALUE; //El mayor entero!!
            while(actual != null){  //mientra la lista no esté vacía
                Caja cajaActual = actual.getDato();
                int size = actual.getDato().size();
                if(cajaActual.getIdCaja() != 1 && cajaActual.getIdCaja() != 2){  //Si la caja no es ni preferencial ni rapida
                    if(size <= min) {
                        min = size;
                        cajaMenosLlena = cajaActual; //Constantemente va a cambiar a las cajas de menor tamaño
                    }
                }
                actual = actual.getSiguiente(); //Actualizar el puntero a la siguiente caja
            }
            if (cajaMenosLlena != null){ //si la caja contiene tiquetes
                cajaMenosLlena.encolar(ticket);
                JOptionPane.showMessageDialog(null,"Tiquete asignado a Caja Normal " + cajaMenosLlena.getIdCaja() );

            } else {
                JOptionPane.showMessageDialog(null,"Oh Snap! X_X  No hay cajas para el tiquete");

            }

        }else{
            JOptionPane.showMessageDialog(null, "No hay un trámite válido"); //No debería de pasar!!!
        }

    }

    //El método para mostrar personas Adelante
    public int personasAdelante(Ticket ticket, ListaCajas lista) {
        //Voy a utilizar el método SIZE que tengo en caja, retorna un número
        NodoLista actual = lista.getCabeza();

        while(actual != null) {
            Caja caja = actual.getDato();
            NodoCaja nodoTicket = caja.getFrente();


            while(nodoTicket !=null) {
                if (nodoTicket.getDato() == ticket) { //si se encuentra el tiquete
                    return caja.size() - 1; //Personas adelante -1
                }

                nodoTicket = nodoTicket.getSiguiente(); //Se mueve al siguiente tiquete
            }
            actual = actual.getSiguiente(); //Se mueve a la siguiente caja
        }
        return -1; //Si no se encontró el tiquete

    }


}