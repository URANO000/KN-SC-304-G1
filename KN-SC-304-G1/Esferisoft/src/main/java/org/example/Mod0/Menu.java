/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod0;
import javax.swing.JOptionPane;

public class Menu {
    private ConfigSucursal configuracion;

    // Constructor
    public Menu() {
        this.configuracion = new ConfigSucursal();
        this.configuracion = ConfigJson.cargarConfiguracion();
        if (this.configuracion == null) {
        this.configuracion = new ConfigSucursal();    
        }
    }

    // Método para mostrar el menú principal
    
    public void mostrarMenu() {
            // Método para mostrar el menú principal
        if (configuracion == null) {
            // Si no existe el archivo, solicitar la configuración inicial
            JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración. Se creará uno nuevo.");
            solicitarConfiguracionInicial();
        } else {
            // Si existe el archivo, mostrar la configuración cargada
            JOptionPane.showMessageDialog(null, "Configuración cargada:\n" +
                    "Nombre de la sucursal: " + configuracion.getNombreSucursal() + "\n" +
                    "Número de cajas: " + configuracion.getTotalCajas() + "\n" +
                    "Tipos de cajas: \n" + configuracion.getTiposCajas().obtenerElementos() + "\n" +
                    "Usuarios: \n" + configuracion.getUsuarios().obtenerElementos());
        }
    }

    // Método para solicitar la configuración inicial
    private void solicitarConfiguracionInicial() {
        // Solicitar el nombre de la sucursal
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
            while (!tipo.equals("preferencial") && !tipo.equals("rápida") && !tipo.equals("normal")) {
                tipo = JOptionPane.showInputDialog("Tipo no válido. Ingrese 'preferencial', 'rápida' o 'normal':");
                tipo = tipo.toLowerCase();
            }

            tiposCajas.agregar(tipo);

            // Verificar si hay al menos una caja preferencial y una rápida
            if (tipo.equals("preferencial")) {
                tienePreferencial = true;
            } else if (tipo.equals("rápida")) {
                tieneRapida = true;
            }
        }

        // Validar que haya al menos una caja preferencial y una rápida
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

            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
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