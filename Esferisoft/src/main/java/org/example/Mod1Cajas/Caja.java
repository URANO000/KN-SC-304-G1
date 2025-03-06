package org.example.Mod1Cajas;

public class Caja {
    //La clase cajas es basicamente como decir la clase: Colas
    //Le voy a dar un id a la caja, de esta manera se puede tener un control de cual es cual
    private int idCaja;
    private String nombre;
    private NodoCaja frente;
    private NodoCaja fin;
    private int contador;

    //Constructor----------------------------
    //A la hora de crear cada caja

    public Caja(int idCaja, String nombre) {
        this.idCaja = idCaja;
        this.nombre = nombre;
    }

    public Caja(NodoCaja frente, NodoCaja fin) {
        this.frente = frente;
        this.fin = fin;
    }

    public Caja() {
        this.frente = frente;
        this.fin = fin;
    }

    //Metodos---------------------------------
    public void encolar(Ticket t) {
        NodoCaja nuevo = new NodoCaja(t); //Creo un nuevo nodo con el tiquete

        //Si la cola no está vacía
        if(fin != null) {
            fin.setSiguiente(nuevo);  //El puntero a punta el last node con el nuevo nodo
        }
        fin = nuevo; //Actualizo el puntero al last nodo
        if(frente == null) {  //Si la cola está vacía
            frente = nuevo; //El nuevo nodo es el primero tmb
        }
        contador ++;
    }

    public Ticket atender() throws Exception {
        if(frente == null) {
            throw new Exception("La caja está vacía");  //Si no hay elementos
        }
        Ticket dato = frente.getDato();  //Guarda el dato del primer elemento
        frente = frente.getSiguiente(); //El puntero se mueve al siguiente nodo
        if(frente == null) {
            fin = null; //El puntero tmb se actualiza
        }
        contador --;
        return dato;  //Devuelve el dato
    }

    public Ticket frente() throws Exception {
        if(frente == null) {
            throw new Exception("La caja está vacía");
        }
        return frente.getDato();  //Regresa el dato al frente sin eliminarlo
    }

    public boolean isEmpty() {
        return frente == null;  //Devuelve true si no hay elementos dentro de la caja
    }

    public int size() {
        return contador;  //retorna los tiquetes por cola
    }


    public String toString() {
        return "Caja ID: " + getIdCaja() + " - " +getNombre();

    }



    //Getter & setter-----------------------

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
