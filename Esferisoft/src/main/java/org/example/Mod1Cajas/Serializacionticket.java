package org.example.Mod1Cajas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;

public class Serializacionticket {
    private static final String ARCHIVO_2 = "tiquetes.json";

    //Método para serializar la Lista de Cajas
    public void serializarListaCajas (ListaCajas lista){
        Gson gson = new GsonBuilder ().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ARCHIVO_2)) {
            
            gson.toJson(lista ,writer);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    //Método para desserializar la Lista de Cajas
    public ListaCajas deserializarListaCajas(){
        Gson gson = new Gson();
        ListaCajas lista = null;

        try(FileReader reader = new FileReader(ARCHIVO_2)){
            lista = gson.fromJson(reader, ListaCajas.class);

            NodoLista actual = lista.getCabeza();
            while (actual != null) {
                Caja caja = actual.getDato();

                // Se reconstruye el contador y fin a partir de frente
                int count = 0;
                NodoCaja cursor = caja.getFrente();
                NodoCaja ultimo = null;

                while (cursor != null) {
                    count++;
                    ultimo = cursor;
                    cursor = cursor.getSiguiente(); //Aquí se recorre la lista enlazada
                }

                caja.setContador(count);    //
                caja.setFin(ultimo);        //sin esto, no se podría encolar correctamente

                actual = actual.getSiguiente(); // siguiente caja
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}
