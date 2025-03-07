/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod0;

/**
 *
 * @author Adbeel
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigJson {
    private static final String ARCHIVO_CONFIG = "config.json";

    // Método para guardar la configuración en el archivo JSON
    public static void guardarConfiguracion(ConfigSucursal configuracion) {
  Gson gson = new GsonBuilder().setPrettyPrinting(). create();
        try (FileWriter escritor = new FileWriter(ARCHIVO_CONFIG)) {
            gson.toJson(configuracion, escritor);
            System.out.println("Configuración guardada exitosamente en " + ARCHIVO_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    //Cargar la configuración en el archivo JSON
    public static ConfigSucursal cargarConfiguracion() {
        Gson gson = new Gson();
        try (FileReader lector = new FileReader(ARCHIVO_CONFIG)){
            return gson.fromJson(lector, ConfigSucursal.class);
        } catch (IOException e) {
           System.out.println("El archivo de no configuración no existe. Creando uno nuevo");
            return null;
}
    }
}
