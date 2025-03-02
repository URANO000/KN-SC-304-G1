package org.example.Mod1;

public class Cola {
    private Nodo cabeza;



    //----------------------------------------ENCOLAR-----------------------------------------------

    public void encolar(Ticket t) {
        //1- Si la lista esta vacia

        if(cabeza == null) {

            //Cualquier Ticket que se inserte sera el primero
            cabeza = new Nodo(t);
        }
        else {

            //2. La logica es insertar en orden por ID.
            if(t.getId() < cabeza.getDato().getId()) {

                //Entonces se inserta el nuevo tickete primero ( en la cabeza)
                Nodo auxiliar = new Nodo();
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            }
            else {
                //La lista sólo tiene un elemento y se debe insertar la
                //nueva persona a la derecha de la cabeza.
                //Aquí el id de la persona es mayor al id de la cabeza --------------> Sigo las intrucciones de la leccion 07
                if(cabeza.getSiguiente() == null) {
                    Nodo nuevo = new Nodo();
                    cabeza.setSiguiente(nuevo);

                }
                else{
                    //Si ninguna de las condiciones anteriores se cumple entonces insertar
                    Nodo auxiliar = cabeza;
                    while(auxiliar.getSiguiente() != null && auxiliar.getSiguiente().getDato().getId() < t.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    Nodo otroAuxiliar = new Nodo(t);  //Se almacena el nuevo nodo para mantener las referencias
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }

            }
        }
    }

    //-----------------------------------------------ATENDER---------------------------------------------------
    public void atiende(int id)
    {
        Nodo auxiliar = cabeza;
        Nodo anterior = null;

        while(auxiliar != null)
        {
            if(auxiliar.getDato().getId() == id)
            {
                //Aquí eliminamos
                if(auxiliar == cabeza)
                {
                    cabeza = cabeza.getSiguiente();
                    auxiliar.setSiguiente(null);
                    break;
                }
                else
                {
                    anterior.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(null);
                    break;
                }
            }
            else
            {
                //No se encuantra el ticket a eliminar
                anterior = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
    }

    //-----------------------------------------------EXISTE ( en caso de necesitar)----------------------------
    public boolean existe(int id) {
        boolean respuesta = false;

        Nodo auxiliar = cabeza;

        while(auxiliar != null) {
            if (auxiliar.getDato().getId() == id) {
                respuesta = true;
                break;
            }
            else{
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return  respuesta;
    }


}
