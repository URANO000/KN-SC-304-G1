package org.example.Mod1Cajas;
import org.example.Mod0.*;
import javax.swing.*;   //Solo la libreria de JAVA SWING
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ManagerCajas {
    private ListaCajas listaCajas = new ListaCajas();

    //Getters & setter

    //En esta clase se va a manejar t odo lo correspondiente a las cajas

    public void menuCajas() {
        //Primero se deben de crear las cajas de acuerdo a el input del usuario

        //primero a leer la configuracion del archivo JSON config-------------------------------------
        ConfigSucursal config = ConfigJson.cargarConfiguracion();

        if (config == null) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al cargar la configuracion",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Creo la lista de cajas ---------------------------------
        for(int i = 0; i < config.getTotalCajas(); i++) {   //Un loop de creación de cajas por cada iteración según las cajas totales
            String tipo;
            if(i == 0) {   //Este if lo que hace es asignar el tipo de caja según el ID
                tipo = "Preferencial";
            } else if(i == 1) {
                tipo = "Rápida";
            } else {
                tipo = "Normal";
            }
            Caja caja = new Caja(i, "Caja " + tipo);
            listaCajas.insertar(caja);

        }

        System.out.println(listaCajas.toString());


        //---------------Ahora sigue la creación del ticuete con JOptionPane---------------------
        boolean continuar = true;  //Para el menú

        try{
            while(continuar) {
                int seleccion = Integer.parseInt(JOptionPane.showInputDialog("Sistema de tiquetes \n" +
                        "1. Crear nuevo tiquete \n" +
                        "2. Salir"));

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
            while (true) {
                opcionesMoneda = JOptionPane.showInputDialog("DOLARES o COLONES");
                if (!opcionesMoneda.equals("DOLARES") || !opcionesMoneda.equals("COLONES")) {
                    break;
                }
            }

            String tramite = JOptionPane.showInputDialog("Ingrese el trámite a realizar (Depósitos, Servicios, Retiros, Cambio de divisas)");

            int tramiteAUX = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de trámite \n" +
                    "1. P: preferencial  \n" +
                    "2. A: Rápida  \n" +
                    "3. B: Normal: "));
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
            }else {
                JOptionPane.showMessageDialog(null, "Hay " + personasAdelante(ticket, listaCajas) + " persona(s) frente a usted");
            }




        } catch(Exception e) {
            e.printStackTrace();
        }


    }
    public void asignarCaja(Ticket ticket, ListaCajas lista) {
        NodoLista actual = lista.getCabeza();
        Random rand = new Random(); //Quiero utilizar lógica para asignar tiquete a cualquier caja random si es una caja normal

        if(ticket.getTipoTramite().equals("P")){
            while(actual != null){
                if (actual.getDato().getIdCaja() == 0){
                    actual.getDato().encolar(ticket);
                    JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Preferencial");
                    return;
                }
                actual = actual.getSiguiente();
            }
        }
        else if(ticket.getTipoTramite().equals("A")) {
            while(actual != null){
                if(actual.getDato().getIdCaja() == 1){
                    actual.getDato().encolar(ticket);
                    JOptionPane.showMessageDialog(null, "Tiquete asginado a Caja Rápida");
                    return;

                }
                actual = actual.getSiguiente();
            }
        }
        else {  //Si la caja es normal
            int totalCajas = ConfigJson.cargarConfiguracion().getTotalCajas();
            int cajaRandom;

            do{
                cajaRandom = rand.nextInt(totalCajas); //Se pone en cualquier caja normal

            }while(cajaRandom == 0 || cajaRandom == 1);  //Mientras el ID de la caja no sea 0 o 1

            //Ahora asignamos a la caja normal, cualquiera que sea
            while(actual != null) {  //Mientras el nodo actual no esté vacío
                if(actual.getDato().getIdCaja() == cajaRandom) {
                    actual.getDato().encolar(ticket);  //encolamos el ticket en la caja random
                    JOptionPane.showMessageDialog(null, "Tiquete asignado a Caja Normal " + cajaRandom);
                    return;
                }
                actual = actual.getSiguiente(); //Siempre hay que actualizar el puntero

            }

        }
        JOptionPane.showMessageDialog(null, "No se encontró una caja"); //No debería de pasar!!!

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
