package org.example.Mod1Cajas;

public class ListaCajas {
    //Las cajas van dentro de esta lista
    //Es una lista de cajas -> Linked List
    private NodoLista cabeza;
    private int contador;


    //Getters & setters--------------------------------

    public NodoLista getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoLista cabeza) {
        this.cabeza = cabeza;
    }

    //Metodos------------------------------------------
    public void insertar(Caja c) {

        //1. Si la lista esta vacia
        if(cabeza == null) {

            cabeza = new NodoLista(c); //Si la lista esta vacia entonces agregar nueva caja
        } else {

            //2. Insertar por ID de caja
            //Si la caja tiene un id menor al que referencia la cabeza
            // Entonces se inserta a la izquierda de la lista
            if(c.getIdCaja() < cabeza.getDato().getIdCaja()) {

                //Se inserta la nueva caja a la cabeza
                NodoLista auxiliar = new NodoLista(c);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                //3. Si la lista solo tiene un elemento y se debe de insertar
                //la nueva caja a la derecha de la cabeza    cabeza -> nueva Caja
                if(cabeza.getSiguiente() == null) {
                    NodoLista nuevo = new NodoLista(c); //se agrega la caja
                    cabeza.setSiguiente(nuevo); //Se cambia el puntero
                } else{

                    //4. Si ls condiciones anteriores no aplican, entonces
                    //la nueva caja se inserta en el medio o al final de la lista de cajas

                    NodoLista auxiliar = cabeza;
                    while(auxiliar.getSiguiente() != null && auxiliar.getSiguiente().getDato().getIdCaja() < c.getIdCaja()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoLista otroAuxiliar = new NodoLista(c);  //El nuevo nodo se almacena

                    //Esto es para no perder la referencia
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }

        }
        contador ++;

    }

    public boolean existe(int id) {  //No estoy segura de si esto se va a utilizar
        boolean respuesta = false;
        NodoLista auxiliar = cabeza;

        while(auxiliar != null) {

            if(auxiliar.getDato().getIdCaja() == id) {
                respuesta = true;
                break;
            }
            else {
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return  respuesta;
    }

    public void elimina(int id) {

        NodoLista auxiliar = cabeza;
        NodoLista anterior = null;

        while(auxiliar != null) {

            if (auxiliar.getDato().getIdCaja() == id) {
                //Se elimina
                if(auxiliar == cabeza) {
                    cabeza = cabeza.getSiguiente();
                    auxiliar.setSiguiente(null);
                    break;
                }
                else{

                    anterior.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(null);
                    break;
                }
            }
            else{
                //No se encuentra el id
                anterior = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        contador --;
    }

    public int size() {
        return contador;
    }



    //ToString------------------------------------------
    public String toString() {
        NodoLista auxiliar = cabeza;
        String respuesta = "Lista: \n";

        while(auxiliar != null)
        {
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
        }

        return respuesta;
    }
}
