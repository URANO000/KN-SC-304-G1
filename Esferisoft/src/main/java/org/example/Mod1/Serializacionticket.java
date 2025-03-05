package org.example.Mod1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;



public class Serializacionticket {

    //SERIALIZAR, gracias profe, por el ejemplo
    // Cola + nombre un archivo
    public void serializarCola(Cola colas, String archivo){
        Gson gson = new GsonBuilder().create();
        try (FileWriter writer = new FileWriter(archivo)) {
            
            gson.toJson(colas,writer);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public Cola desseralizarCola(String archivo){
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(archivo)) {            
            return gson.fromJson(reader, Cola.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
