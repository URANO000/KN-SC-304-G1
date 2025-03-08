package org.example.Mod1Cajas;

public class NodoCaja {
    //El nodo contiene el Ticket que es su dato, ya que en cada cola hay tiquetes

    private Ticket dato;
    private NodoCaja siguiente;

    //Constructores--------------------------

    public NodoCaja(Ticket dato) {
        this.dato = dato;
    }

    public NodoCaja() {
        this.siguiente = null;
    }


    //Getters & setters----------------------

    public Ticket getDato() {
        return dato;
    }

    public void setDato(Ticket dato) {
        this.dato = dato;
    }

    public NodoCaja getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCaja siguiente) {
        this.siguiente = siguiente;
    }
}
