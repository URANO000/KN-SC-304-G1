package org.example.Mod4;
import java.io.*;
import java.util.*;

import org.json.*; // Librería para manejar JSON

import javax.swing.JOptionPane;

public class GrafoServicios {
    private Map<String, NodoLista> grafo;
    private static final String ARCHIVO = "grafo.json";

    public GrafoServicios() {
        grafo = new HashMap<>();
        cargarGrafo();
    }

    public void agregarTramite(String tramite) {
        if (!grafo.containsKey(tramite)) {
            grafo.put(tramite, new NodoLista(tramite));
        }
    }

    public void agregarServicioComplementario(String tramite, String servicio) {
        grafo.putIfAbsent(tramite, new NodoLista(tramite));
        grafo.get(tramite).agregarServicio(servicio);
    }

    public List<String> obtenerServiciosComplementarios(String tramite) {
        return grafo.containsKey(tramite) ? grafo.get(tramite).obtenerServicios() : new LinkedList<>();
    }

    public void mostrarServicios(String tramite) {
        List<String> servicios = obtenerServiciosComplementarios(tramite);
        if (!servicios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Servicios complementarios para " + tramite + ":\n" + String.join("\n", servicios), "Notificación", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void guardarGrafo() {
        try (FileWriter file = new FileWriter(ARCHIVO)) {
            JSONObject json = new JSONObject();
            for (String tramite : grafo.keySet()) {
                JSONArray serviciosArray = new JSONArray(grafo.get(tramite).obtenerServicios());
                json.put(tramite, serviciosArray);
            }
            file.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarGrafo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
            
            JSONObject json = new JSONObject(contenido.toString());
            json.keys().forEachRemaining(tramite -> {
                JSONArray servicios = json.optJSONArray(tramite);
                if (servicios != null) {
                    NodoLista nodo = new NodoLista(tramite);
                    for (int i = 0; i < servicios.length(); i++) {
                        nodo.agregarServicio(servicios.getString(i));
                    }
                    grafo.put(tramite, nodo);
                }
            });
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GrafoServicios grafo = new GrafoServicios();
        
        // Ejemplo de uso
        grafo.agregarTramite("Depósitos");
        grafo.agregarServicioComplementario("Depósitos", "Seguro de Ahorros");
        grafo.agregarServicioComplementario("Depósitos", "Protección Contra Fraudes");
        
        grafo.mostrarServicios("Depósitos");
        
        grafo.guardarGrafo();
    }
}

class NodoLista {
    private String tramite;
    private LinkedList<String> servicios;
    
    public NodoLista(String tramite) {
        this.tramite = tramite;
        this.servicios = new LinkedList<>();
    }
    
    public void agregarServicio(String servicio) {
        servicios.add(servicio);
    }
    
    public List<String> obtenerServicios() {
        return servicios;
    }
}
