package org.example.Mod1;

import org.example.Mod0.BranchConfig;
import org.example.Mod0.ConfigManager;

import javax.swing.*;
import java.util.HashMap;



public class CreacionTicket {

    //Como en realidad no tengo la menor idea de como hacerlo de otra manera, investigué opciones
    //Lo mas beneficioso en este caso es utilizar HashMap para separarlo segun el KEY (P, B, o A)

    static HashMap<String, Cola> colas = new HashMap<>(); //Por que? Porque el numero de cajas en el config puede ser dinamico


    public static void main(String[] args) {
        //Manejar errores con try & catch es importante

        //primero a leer la configuracion del archivo JSON config
        BranchConfig config = ConfigManager.loadConfig();
        if (config == null) {
            JOptionPane.showMessageDialog(null, "Hubo un Error al cargar la configuracion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Creo las colas dinamicamente segun el array de tipos de caja
        for (String tipoCaja : config.getBoxTypes()) {
            if (!colas.containsKey(tipoCaja)) {
                colas.put(tipoCaja, new Cola());
            }
        }

        //Por ahora voy a crear un menu de asignacion de ticket

        boolean continuar = true;

        while(continuar) {
            String[] opciones = {"Crear nuevo ticket", "Ver posicion de ticket", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Sistema de tiquetes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    crearNuevoTicket();
                    break;
                case 1:
                    verPosicionTicket();
                    break;
                case 2:
                    //Al salir, automaticamente se van a serializar las colas----------------
                    Serializacionticket serializar = new Serializacionticket();
                    serializar.serializarColas(colas, "tiquetes.json");
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida!");
                    break;


            }
        }


    }

    //Por ahora lo voy a poner aqui hasta pensar en un mejor lugar (Mi cerebro no sirve). Por que JOptionPane tiene tantas opciones??, ayuda

    //Estoy segura de que hay una mejor manera, pero por ahora, no la encuentro

    public static void crearNuevoTicket() {
        //Se piden los datos del usuario
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID!:"));
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad:"));
        String[] opcionesMoneda = {"Colones", "Dólares"};
        String monedaCuenta = (String) JOptionPane.showInputDialog(null, "Seleccione su tipo de cuenta:", "Cuenta",
                JOptionPane.QUESTION_MESSAGE, null, opcionesMoneda, opcionesMoneda[0]);

        String[] opcionesTramite = {"Depósitos", "Retiros", "Cambio de Divisas", "Servicios"}; //Nunca he ido a un banco, en realidad
        String tramite = (String) JOptionPane.showInputDialog(null, "Seleccione el trámite:", "Tipo de Trámite",
                JOptionPane.QUESTION_MESSAGE, null, opcionesTramite, opcionesTramite[0]);

        String[] opcionesTipo = {"Preferencial (Discapacitados, Embarazadas, Adultos Mayores)", "Rápida (Solo un trámite)", "Normal(dos trámites o más)"};
        char tipoTramite = switch (JOptionPane.showOptionDialog(null, "Seleccione el tipo de trámite:",
                "Tipo de Cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcionesTipo, opcionesTipo[0])) {
            case 0 -> 'P';  //Es como un siwtch case mas chico (es lo mismo), pero ver mucho texto me pone nerviosa
            case 1 -> 'A';
            default -> 'B';
        };

        //Creamos el tiquete
        Ticket nuevoTiquete = new Ticket(nombre, id, edad, monedaCuenta, System.currentTimeMillis(), -1, tramite, tipoTramite);
        asignarTiquete(nuevoTiquete);
    }

    //Mas metodos, no se que hacer con mi vida

    public static void asignarTiquete(Ticket ticket) {
        String claveCola = switch (ticket.getTipoTramite()) {
            case 'P' -> "preferencial";
            case 'A' -> "rapida";
            default -> "normal";
        };

        Cola colaAsignada = colas.get(claveCola);
        if (colaAsignada != null) {
            colaAsignada.encolar(ticket);
            int posicion = colaAsignada.obtenerPosicion(ticket.getId()); // Aquí se usa el ID correcto

            String mensaje = "Su tiquete ha sido asignado a la caja " + claveCola.toUpperCase() + ".\n"
                    + (posicion == 0 ? "Es su turno!!!" : "Hay " + posicion + " personas antes de usted en la cola.");
            JOptionPane.showMessageDialog(null, mensaje, "Tiquete Asignado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se encontró una cola válida para este trámite.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void verPosicionTicket() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del tiquete para consultar su posición:"));

        for (String tipo : colas.keySet()) {
            int posicion = colas.get(tipo).obtenerPosicion(id);
            if (posicion != -1) {
                JOptionPane.showMessageDialog(null, "El tiquete con el ID " + id + " está en la posición " + posicion + " en la cola " + tipo.toUpperCase());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "El tiquete con el ID " + id + " no se encontró", "Error", JOptionPane.ERROR_MESSAGE);
    }




}
