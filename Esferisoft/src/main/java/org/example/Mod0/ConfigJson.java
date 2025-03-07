/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod0;

/**
 *
 * @author Adbeel
 */
import java.io.FileWriter;
import java.io.IOException;

public class ConfigJson {
    private static final String ARCHIVO_CONFIG = "config.json";

    // Método para guardar la configuración en el archivo JSON
    public static void guardarConfiguracion(ConfigSucursal configuracion) {
        try (FileWriter escritor = new FileWriter(ARCHIVO_CONFIG)) {
            escritor.write("{\n");
            escritor.write("  \"nombreSucursal\": \"" + configuracion.getNombreSucursal() + "\",\n");
            escritor.write("  \"totalCajas\": " + configuracion.getTotalCajas() + ",\n");
            escritor.write("  \"tiposCajas\": [\n");
            escritor.write(configuracion.getTiposCajas().obtenerElementos().replace("\n", ",\n"));
            escritor.write("  ],\n");
            escritor.write("  \"usuarios\": [\n");
            escritor.write(configuracion.getUsuarios().obtenerElementos().replace("\n", ",\n"));
            escritor.write("  ]\n");
            escritor.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}