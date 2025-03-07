/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Mod0;

/**
 *
 * @author Adbeel
 */
public class ListaEnlazada {
    private Nodo cabeza;

    // Método para agregar un dato a la lista
    public void agregar(String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    // Método String para obtener todos los elementos
    public String obtenerElementos() {
        StringBuilder elementos = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            elementos.append(actual.dato).append("\n");
            actual = actual.siguiente;
        }
        return elementos.toString();
    }

    // Método para verificar si la lista contiene algun dato específico
    public boolean contiene(String dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}