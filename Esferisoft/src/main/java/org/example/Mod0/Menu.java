/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod0;

/**
 *
 * @author Adbeel
 */
import org.json.simple.parser.ParseException;

import javax.swing.JOptionPane;
import java.io.IOException;

public class Menu {
    private ConfigSucursal configuracion;

    public Menu() throws IOException, ParseException {
        this.configuracion = ConfigJson.cargarConfiguracion();
    }

    //Mostrar el menú principal
    public void mostrarMenu() {
        if (configuracion == null) {        // si no existe el archivo JSON, solitar la configuración para crearlo
            solicitarConfiguracionInicial();
        } else {
            JOptionPane.showMessageDialog(null, "Configuración cargada:\n" +
            "Nombre de la sucursal: " + configuracion.getNombreSucursal() + "\n"
             + "Número de cajas: " + configuracion.getTotalCajas() + "\n" +
             "Tipos de cajas: \n" + configuracion.getTiposCajas().obtenerElementos() + "\n" +
                    "Usuarios: \n" + configuracion.getUsuarios().obtenerElementos());
        }
    }
    private void solicitarConfiguracionInicial() {
        //Nombre de la sucursal
        this.configuracion = new ConfigSucursal();
        String nombreSucursal = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal:");
        configuracion.setNombreSucursal(nombreSucursal);

        // Solicitar el número total de cajas
        int totalCajas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número total de cajas:"));
        configuracion.setTotalCajas(totalCajas);

        // Solicitar los tipos de cajas
        ListaEnlazada tiposCajas = new ListaEnlazada();
        boolean tienePreferencial = false;
        boolean tieneRapida = false;

        for (int i = 1; i <= totalCajas; i++) {
            String tipo = JOptionPane.showInputDialog("Ingrese el tipo de la caja " + i + " (preferencial/rápida/normal):");
            tipo = tipo.toLowerCase();

            // Validar el tipo de caja
            while (!tipo.equals("preferencial") && !tipo.equals("rápida") && !tipo.equals("rapida") && !tipo.equals("normal")) {
                tipo = JOptionPane.showInputDialog("Tipo no válido. Ingrese 'preferencial', 'rápida' o 'normal':");
                tipo = tipo.toLowerCase();
            }

            tiposCajas.agregar(tipo);

            // Verificar si hay al menos una caja preferencial y una rápida
            if (tipo.equals("preferencial")) {
                tienePreferencial = true;
            } else if (tipo.equals("rápida") || tipo.equals("rapida")) {
                tieneRapida = true;
            }
        }

        if (!tienePreferencial || !tieneRapida) {
            JOptionPane.showMessageDialog(null, "Debe haber al menos una caja preferencial y una caja rápida.");
            return;
        }

        configuracion.setTiposCajas(tiposCajas);

        // Solicitar datos de los usuarios
        ListaEnlazada usuarios = new ListaEnlazada();
        while (true) {
            String nombreUsuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");

            usuarios.agregar(nombreUsuario + ":" + contrasena);

            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (respuesta != JOptionPane.YES_OPTION) {
                break;
            }
        }

        configuracion.setUsuarios(usuarios);

        // Guardar la configuración en el archivo JSON
        ConfigJson.guardarConfiguracion(configuracion);
        JOptionPane.showMessageDialog(null, "Configuración guardada exitosamente en config.json.");
    }
}
