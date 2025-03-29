package org.example.Mod3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Mod1Cajas.ListaCajas;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;

public class SerializaciónColas {
    //Aqui se hace la serialización pertinente a colas.json -->
    private static final String ARCHIVO_3 = "colas.json";

    public void serializarColas (ListaCajas lista){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ARCHIVO_3)) {

            gson.toJson(lista ,writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
