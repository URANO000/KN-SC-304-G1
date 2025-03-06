package org.example.Mod1Cajas;
import org.example.Mod0.*;
import javax.swing.*;   //Solo la libreria de JAVA SWING
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerCajas {

    //En esta clase se va a manejar t odo lo correspondiente a las cajas

    public void managerCajas() {
        //Primero se deben de crear las cajas de acuerdo a el input del usuario

        //primero a leer la configuracion del archivo JSON config-------------------------------------
        BranchConfig config = ConfigManager.loadConfig();
        if (config == null) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al cargar la configuracion",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Creo la lista de cajas ---------------------------------
        ListaCajas listaCajas = new ListaCajas();
        for(int i = 0; i < config.getTotalBoxes(); i++) {   //Un loop de creación de cajas por cada iteración según las cajas totales
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
                        "2. Personas adelante \n" +
                        "3. Salir"));

                switch (seleccion) {
                    case 1:
                        //Metodo de crear un nuevo tiquete
                        crearTicket();
                        break;
                    case 2:
                        //Metodo de ver personas o algo asi
                        break;
                    case 3:
                        //Aquí va el metodo de serializar

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

    public static void crearTicket(){
        try{
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID, puede ser número de cédula:"));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad:"));
            String opcionesMoneda = JOptionPane.showInputDialog("DOLARES o COLONES");
            if (opcionesMoneda != "DOLARES" || opcionesMoneda != "COLONES") {
                JOptionPane.showMessageDialog(null, "La moneda no es válida");
                ManagerCajas managerCajas = new ManagerCajas();
                managerCajas.managerCajas();   //Se envía de nuevo al menú sin agregar la información
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
                case 2:
                    tipoTramite = "A";
                case 3:
                    tipoTramite = "B";
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");

            }


            //Creamos el tiquete y asignamos--------------------------------------------------------------------

            //Aqui lo que necesito es hacer que el tiquete tenga la hora. La lógica la encontré en: https://www.w3schools.com/java/java_date.asp
            LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //Aqui esta el formateo

            String fechaConFormato = fecha.format(formato);

            Ticket ticket = new Ticket(nombre,id, edad, opcionesMoneda, fechaConFormato, -1, tramite, tipoTramite);


        } catch(Exception e) {
            e.printStackTrace();
        }


    }

}
