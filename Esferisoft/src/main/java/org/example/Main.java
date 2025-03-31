package org.example;

import org.example.Mod0.*;
import org.example.Mod1Cajas.*;
import org.example.Mod2Atencion.*;
import Mod4.GrafoServicios;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        
        // Crear objeto de módulo 1.0 (configuración del sistema)
        Menu menu = new Menu();
        menu.mostrarMenu(); // Si no existe config.json, la crea; si sí existe, la muestra
        
        //Validación del usuario (login)
        ConfigSucursal config = menu.getConfiguracion();
        if (!menu.validarUsuario(config.getUsuarios())) {
            JOptionPane.showMessageDialog(null, "Sesión cancelada. Cerrando sistema.");
            return;
        }
        
        //Objetos de módulos principales
        ManagerCajas managerCajas = new ManagerCajas();
        ListaCajas listaCajas = managerCajas.getListaCajas();
        ManagerAtencion managerAtencion = new ManagerAtencion(listaCajas, config.getNombreSucursal());
         GrafoServicios grafo = new GrafoServicios(); // Instanciar el grafo de servicios
        
        //Ahora sí, el menú principal
        boolean proseguir = true;
        while (proseguir){
            try {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "¡Bienvenido al sistema de EcoColones!\n"+
                        "---------------------------------------------\n"+
                        "-------- Sistema de Gestión --------\n"+
                        "---------------------------------------------\n"+
                        "1. Gestión de Tiquetes\n"+
                        "2. Gestión de Atenciones\n"+ 
                        "3. Gestión de Servicios (Grafo)\n"+
                        "4. Salir\n"));

                    switch (opcion){
                        case 1:
                            managerCajas.menuCajas(); //Menú del módulo de Cajas
                            break;

                        case 2:
                            managerAtencion.mostrarMenuAtencion(); //Menú del módulo de Atención de Cajas
                            break;

                        case 3:
                            int opcionGrafo = Integer.parseInt(JOptionPane.showInputDialog(
                                "Gestión de Servicios (Grafo):\n" +
                                "1. Mostrar Grafo\n" +
                                "2. Mostrar Servicios de un Trámite\n" +
                                "3. Verificar Camino entre Trámite y Servicio\n" +
                                "4. Regresar al Menú Principal\n"));

                            switch (opcionGrafo) {
                            case 1:
                                grafo.mostrarGrafo();
                                break;

                            case 2:
                                String tramite = JOptionPane.showInputDialog("Ingrese el nombre del trámite:");
                                grafo.mostrarServicios(tramite);
                                break;

                            case 3:
                                String tramiteOrigen = JOptionPane.showInputDialog("Ingrese el nombre del trámite de origen:");
                                String servicioBuscado = JOptionPane.showInputDialog("Ingrese el nombre del servicio buscado:");
                                boolean existe = grafo.existeCamino(tramiteOrigen, servicioBuscado);
                                JOptionPane.showMessageDialog(null, 
                                    existe ? "El servicio está disponible desde el trámite indicado." 
                                           : "No se encontró un camino al servicio desde el trámite indicado.");
                                break;

                            case 4:
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Opción inválida");
                                break;
                        }

                            break;

                        case 4:
                            JOptionPane.showMessageDialog(null, "Gracias por usar EcoColones. ¡Hasta pronto!");
                            proseguir = false;
                            break;

                        default:
                                JOptionPane.showMessageDialog(null, "Opción inválida");
                                break;
                    }
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
                }
        }
        
    }
}
