package org.example.Mod1Cajas;

public class NodoLista {
    //El nodo de la lista. Dentro de este nodo van las cajas
    private Caja dato;
    private NodoLista siguiente;

    //Constructores-------------------------------------
    public NodoLista(Caja dato) {
        this.dato = dato;
    }

    //Getters & setters---------------------------------

    public Caja getDato() {
        return dato;
    }

    public void setDato(Caja dato) {
        this.dato = dato;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

    //ToString------------------------------------------
    public String toString() {
        return this.dato.toString();
    }
}
