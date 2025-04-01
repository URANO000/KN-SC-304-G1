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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GrafoServicios {
    private NodoTramite inicio;
    private static final String ARCHIVO = "grafo.txt";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

    private static class NodoTramite {
        String nombre;
        NodoServicio primerServicio;
        NodoTramite siguienteTramite;

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

    private void guardarGrafo() {
        try (FileWriter file = new FileWriter(ARCHIVO)) {
            file.write(gson.toJson(inicio));
            file.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el grafo: " + e.getMessage());
        }
    }

    private void cargarGrafo() {
        try {
            File file = new File(ARCHIVO);
            if (!file.exists()) return;

            try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
                inicio = gson.fromJson(reader, NodoTramite.class);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el grafo: " + e.getMessage());
        }
    }
}
