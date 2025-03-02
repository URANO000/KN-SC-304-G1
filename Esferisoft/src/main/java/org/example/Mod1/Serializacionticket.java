package org.example.Mod1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;


public class Serializacionticket {

    //SERIALIZAR, gracias profe, por el ejemplo
    //COla + nombre del archivo
    public void serializarColas(Map<String, Cola> colas, String archivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(archivo)){
            gson.toJson(colas, writer);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Para deserializar (creo que el modulo 1.2 lo necesita
    public Map<String, Cola> deserializarColas(String archivo) {
        Gson gson = new GsonBuilder().create();
        try(FileReader reader = new FileReader(archivo)) {
            gson.fromJson(reader, new TypeToken<Map<String, Cola>>(){}.getType());
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
