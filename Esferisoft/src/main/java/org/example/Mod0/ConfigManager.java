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

public class ConfigManager {
    private static final String CONFIG_FILE = "config.json";

    // Guardar configuración en el archivo JSON
    public static void saveConfig(BranchConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
        }
    }

    // Cargar configuración desde el archivo JSON
    public static BranchConfig loadConfig() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, BranchConfig.class);
        } catch (IOException e) {
            return null; // Si el archivo no existe, retorna null
        }
    }
}
