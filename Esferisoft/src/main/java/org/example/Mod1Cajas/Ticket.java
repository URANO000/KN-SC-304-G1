package org.example.Mod1Cajas;

public class Ticket {
    //La clase Ticket es basicamente como decir la clase: Dato   ya que es el dato dentro del nodo en cajas

    //Primeramente los atriibutos del modulo 1.1
    private String nombre;
    private int id;
    private int edad;
    private String monedaCuenta;
    String horaCreacion;  //Esto para poner la fecha actual dentro de
    long horaAtencion = -1;
    private String tramite;
    private String tipoTramite;  //P: preferencial, B:dos o mas tramites (regular), o A: un solo tramite(rapida)

    //El ticket puede tener un tipo solamente

    //Constructores--------------------------------

    public Ticket(String nombre, int id, int edad, String monedaCuenta, String horaCreacion, long horaAtencion, String tramite, String tipoTramite) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.monedaCuenta = monedaCuenta;
        this.horaCreacion = horaCreacion;
        this.horaAtencion = horaAtencion;
        this.tramite = tramite;
        this.tipoTramite = tipoTramite;
    }

    public Ticket() {

    }


    //Getters & setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMonedaCuenta() {
        return monedaCuenta;
    }

    public void setMonedaCuenta(String monedaCuenta) {
        this.monedaCuenta = monedaCuenta;
    }

    public String getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(String horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public long getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(long horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
}
