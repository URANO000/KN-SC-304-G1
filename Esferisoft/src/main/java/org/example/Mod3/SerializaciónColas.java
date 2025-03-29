package org.example.Mod3;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Mod1Cajas.ListaCajas;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    private ListaCajas deserializarColas(){
        Gson gson = new Gson();
        ListaCajas lista = null;

        try(FileReader reader = new FileReader(ARCHIVO_3)){
            //Aqui creo que intento deserializar
            lista = gson.fromJson(reader, ListaCajas.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }


}
