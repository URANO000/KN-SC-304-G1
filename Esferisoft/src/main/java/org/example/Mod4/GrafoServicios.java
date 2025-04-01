/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mod4;

/**
 *
 * @author Adbeel
 */
import javax.swing.JOptionPane;
import java.io.*;
import org.json.*;

public class GrafoServicios {
    private NodoTramite inicio;
    private static final String ARCHIVO = "grafo.txt";

    private static class NodoTramite {
        String nombre;
        NodoServicio primerServicio;
        NodoTramite siguienteTramite;
        NodoTramite siguienteVisitado;

        NodoTramite(String nombre) {
            this.nombre = nombre;
        }
    }

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
        agregarTramite("Depósitos");
        agregarServicioComplementario("Depósitos", "Seguro de Ahorros");
        agregarServicioComplementario("Depósitos", "Protección Contra Fraudes");
        agregarServicioComplementario("Depósitos", "Seguro de Vida Gold");

        agregarTramite("Retiros");
        agregarServicioComplementario("Retiros", "Retiro sin tarjeta en cajeros");
        agregarServicioComplementario("Retiros", "Ayuda a la fauna de Costa Rica");
        agregarServicioComplementario("Retiros", "Compensación de huella de carbono");

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

    public boolean existeCamino(String tramiteOrigen, String servicioBuscado) {
        NodoTramite nodo = buscarTramite(tramiteOrigen);
        if (nodo == null) return false;
        
        NodoTramite visitados = null; // Lista enlazada de nodos visitados
        return dfsBuscarServicio(nodo, servicioBuscado, visitados);
    }

    private boolean dfsBuscarServicio(NodoTramite nodo, String servicio, NodoTramite visitados) {
        if (estaEnLista(visitados, nodo)) return false;
        visitados = agregarALista(visitados, nodo);
        
        NodoServicio actual = nodo.primerServicio;
        while (actual != null) {
            if (actual.nombre.equals(servicio)) {
                return true;
            }
            actual = actual.siguienteServicio;
        }
        
        return false;
    }

    private boolean estaEnLista(NodoTramite lista, NodoTramite nodo) {
        NodoTramite actual = lista;
        while (actual != null) {
            if (actual == nodo) return true;
            actual = actual.siguienteVisitado;
        }
        return false;
    }

    private NodoTramite agregarALista(NodoTramite lista, NodoTramite nodo) {
        nodo.siguienteVisitado = lista;
        return nodo;
    }
    private void guardarGrafo() {
    try (FileWriter file = new FileWriter(ARCHIVO)) {
        JSONArray tramitesArray = new JSONArray();

        NodoTramite actual = inicio;
        while (actual != null) {
            JSONObject tramiteJSON = new JSONObject();
            tramiteJSON.put("nombre", actual.nombre);
            
            JSONArray serviciosArray = new JSONArray();
            NodoServicio servicioActual = actual.primerServicio;
            while (servicioActual != null) {
                serviciosArray.put(servicioActual.nombre);
                servicioActual = servicioActual.siguienteServicio;
            }
            
            tramiteJSON.put("servicios", serviciosArray);
            tramitesArray.put(tramiteJSON);
            
            actual = actual.siguienteTramite;
        }

        file.write(tramitesArray.toString(4)); // Guardar con formato indentado
        file.flush();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el grafo: " + e.getMessage());
    }
}

private void cargarGrafo() {
    try {
        File file = new File(ARCHIVO);
        if (!file.exists()) return;

        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea);
            }
        }

        JSONArray tramitesArray = new JSONArray(contenido.toString());
        inicio = null;

        for (int i = 0; i < tramitesArray.length(); i++) {
            JSONObject tramiteJSON = tramitesArray.getJSONObject(i);
            String nombreTramite = tramiteJSON.getString("nombre");
            agregarTramite(nombreTramite);

            JSONArray serviciosArray = tramiteJSON.getJSONArray("servicios");
            for (int j = 0; j < serviciosArray.length(); j++) {
                agregarServicioComplementario(nombreTramite, serviciosArray.getString(j));
            }
        }
    } catch (IOException | JSONException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar el grafo: " + e.getMessage());
    }
}

public String obtenerGrafoComoTexto() {
    StringBuilder resultado = new StringBuilder("Trámites y sus servicios:\n");
    NodoTramite actual = inicio;

    if (actual == null) {
        return "El grafo está vacío.";
    }

    while (actual != null) {
        resultado.append("- ").append(actual.nombre).append("\n");
        NodoServicio servicioActual = actual.primerServicio;
        while (servicioActual != null) {
            resultado.append("  * ").append(servicioActual.nombre).append("\n");
            servicioActual = servicioActual.siguienteServicio;
        }
        actual = actual.siguienteTramite;
    }

    return resultado.toString();
}

public String obtenerServiciosComoTexto(String tramite) {
    NodoTramite nodo = buscarTramite(tramite);
    if (nodo == null) {
        return "El trámite no existe.";
    }

    StringBuilder resultado = new StringBuilder("Servicios de " + tramite + ":\n");
    NodoServicio servicioActual = nodo.primerServicio;
    while (servicioActual != null) {
        resultado.append("- ").append(servicioActual.nombre).append("\n");
        servicioActual = servicioActual.siguienteServicio;
    }

    return resultado.toString();
}


}
