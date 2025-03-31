/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod4;

/**
 *
 * @author Adbeel
 */

//import javax.swing.JOptionPane;
//import java.io.*;
//import org.json.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class GrafoServicios {
    /**
    private NodoTramite inicio;
    private static final String ARCHIVO = "grafo.txt";

    // Clase interna para nodos de trámite
    private static class NodoTramite {
        String nombre;
        NodoServicio primerServicio;
        NodoTramite siguienteTramite;

        NodoTramite(String nombre) {
            this.nombre = nombre;
        }
    }

    // Clase interna para nodos de servicio
    private static class NodoServicio {
        String nombre;
        NodoServicio siguienteServicio;

        NodoServicio(String nombre) {
            this.nombre = nombre;
        }
    }

    public GrafoServicios() {
        cargarGrafo();
        if (inicio == null) {
            inicializarDatos();
        }
    }

    private void inicializarDatos() {
        // Grupo 1: Depósitos, Monedas, Divisas
        agregarTramite("Depósitos");
        agregarServicioComplementario("Depósitos", "Seguro de Ahorros");
        agregarServicioComplementario("Depósitos", "Protección Contra Fraudes");
        agregarServicioComplementario("Depósitos", "Seguro de Vida Gold");

        // Grupo 2: Retiros, Cancelaciones, Planes Ecológicos
        agregarTramite("Retiros");
        agregarServicioComplementario("Retiros", "Retiro sin tarjeta en cajeros");
        agregarServicioComplementario("Retiros", "Ayuda a la fauna de Costa Rica");
        agregarServicioComplementario("Retiros", "Compensación de huella de carbono");

        // Grupo 3: Emergencia Ejecutivo
        agregarTramite("Emergencia Ejecutivo");
        agregarServicioComplementario("Emergencia Ejecutivo", "Plan Salva Vidas");
        
        guardarGrafo();
    }

    public void agregarTramite(String tramite) {
        NodoTramite nuevo = new NodoTramite(tramite);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoTramite actual = inicio;
            while (actual.siguienteTramite != null) {
                actual = actual.siguienteTramite;
            }
            actual.siguienteTramite = nuevo;
        }
    }

    public void agregarServicioComplementario(String tramite, String servicio) {
        NodoTramite nodoTramite = buscarTramite(tramite);
        if (nodoTramite != null) {
            NodoServicio nuevo = new NodoServicio(servicio);
            if (nodoTramite.primerServicio == null) {
                nodoTramite.primerServicio = nuevo;
            } else {
                NodoServicio actual = nodoTramite.primerServicio;
                while (actual.siguienteServicio != null) {
                    actual = actual.siguienteServicio;
                }
                actual.siguienteServicio = nuevo;
            }
        }
    }

    private NodoTramite buscarTramite(String tramite) {
        NodoTramite actual = inicio;
        while (actual != null) {
            if (actual.nombre.equals(tramite)) {
                return actual;
            }
            actual = actual.siguienteTramite;
        }
        return null;
    }

    public void mostrarServicios(String tramite) {
        NodoTramite nodo = buscarTramite(tramite);
        if (nodo != null) {
            StringBuilder servicios = new StringBuilder();
            NodoServicio actual = nodo.primerServicio;
            while (actual != null) {
                servicios.append("- ").append(actual.nombre).append("\n");
                actual = actual.siguienteServicio;
            }
            
            if (servicios.length() > 0) {
                JOptionPane.showMessageDialog(null, 
                    "Servicios complementarios para " + tramite + ":\n" + servicios.toString(),
                    "Oferta Complementaria", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void mostrarGrafo() {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "El grafo está vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder grafoCompleto = new StringBuilder("=== GRAFO DE SERVICIOS ===\n");
        NodoTramite actualTramite = inicio;
        
        while (actualTramite != null) {
            grafoCompleto.append("\nTrámite: ").append(actualTramite.nombre).append("\n");
            grafoCompleto.append("Servicios complementarios:\n");
            
            NodoServicio actualServicio = actualTramite.primerServicio;
            if (actualServicio == null) {
                grafoCompleto.append("  - No tiene servicios asociados\n");
            } else {
                while (actualServicio != null) {
                    grafoCompleto.append("  - ").append(actualServicio.nombre).append("\n");
                    actualServicio = actualServicio.siguienteServicio;
                }
            }
            
            actualTramite = actualTramite.siguienteTramite;
        }
        
        JOptionPane.showMessageDialog(null, grafoCompleto.toString(), "Grafo Completo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void guardarGrafo() {
        try (FileWriter file = new FileWriter(ARCHIVO)) {
            JSONObject json = new JSONObject();
            NodoTramite actualTramite = inicio;
            
            while (actualTramite != null) {
                JSONArray serviciosArray = new JSONArray();
                NodoServicio actualServicio = actualTramite.primerServicio;
                
                while (actualServicio != null) {
                    serviciosArray.put(actualServicio.nombre);
                    actualServicio = actualServicio.siguienteServicio;
                }
                
                json.put(actualTramite.nombre, serviciosArray);
                actualTramite = actualTramite.siguienteTramite;
            }
            
            file.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarGrafo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
            
            JSONObject json = new JSONObject(contenido.toString());
            for (String tramite : json.keySet()) {
                agregarTramite(tramite);
                JSONArray servicios = json.getJSONArray(tramite);
                for (int i = 0; i < servicios.length(); i++) {
                    agregarServicioComplementario(tramite, servicios.getString(i));
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean existeCamino(String tramiteOrigen, String servicioBuscado) {
        NodoTramite nodo = buscarTramite(tramiteOrigen);
        if (nodo == null) return false;
        
        return dfsBuscarServicio(nodo, servicioBuscado, new java.util.HashSet<>());
    }

    private boolean dfsBuscarServicio(NodoTramite nodo, String servicio, java.util.Set<NodoTramite> visitados) {
        if (visitados.contains(nodo)) return false;
        visitados.add(nodo);
        
        NodoServicio actual = nodo.primerServicio;
        while (actual != null) {
            if (actual.nombre.equals(servicio)) {
                return true;
            }
            actual = actual.siguienteServicio;
        }
        
        return false;
    }
     **/
}
