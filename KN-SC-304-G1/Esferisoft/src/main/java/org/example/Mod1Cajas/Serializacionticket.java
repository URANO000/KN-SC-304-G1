package org.example.Mod1Cajas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;

public class Serializacionticket {

    public Serializacionticket() {
    }
    
    //Método para serializar la Lista de Cajas
    public void serializarListaCajas (ListaCajas lista, String archivo){
        Gson gson = new GsonBuilder ().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(archivo)) {
            
            gson.toJson(lista ,writer);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    //Método para desserializar la Lista de Cajas
    public ListaCajas desseralizarPila(String archivo){
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(archivo)) {            
            return gson.fromJson(reader, ListaCajas.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
