package org.example.Mod1;

public class Nodo {
    private Ticket dato;
    private Nodo siguiente;

    //Constructores--------------------------

    public Nodo(Ticket dato) {
        this.dato = dato;
    }

    public Nodo() {
        this.siguiente = null;
    }


    //Getters & setters----------------------

    public Ticket getDato() {
        return dato;
    }

    public void setDato(Ticket dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
