package org.example;

import org.example.Mod0.*;
import org.example.Mod1Cajas.*;
import org.example.Mod2Atencion.*;
import org.json.simple.parser.ParseException;
import org.example.Mod4.*;
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

        //Ahora sí, el menú principal
        boolean proseguir = true;
        while (proseguir) {
            try {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        """
                                ¡Bienvenido al sistema de EcoColones!
                                ---------------------------------------------
                                -------- Sistema de Gestión --------
                                ---------------------------------------------
                                1. Gestión de Tiquetes\s
                                2. Gestión de Atención\s
                                3. Ver tipo de cambio\s
                                4. Salir
                               \s"""));

                switch (opcion) {
                    case 1:
                        managerCajas.menuCajas(); //Menú del módulo de Cajas
                        break;

                    case 2:
                        managerAtencion.mostrarMenuAtencion(); //Menú del módulo de Atención de Cajas
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Invoca web services, en construcción...");
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
