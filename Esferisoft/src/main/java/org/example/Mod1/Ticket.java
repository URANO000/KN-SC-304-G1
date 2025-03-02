package org.example.Mod1;

import java.time.*;

public class Ticket {
    //Primeramente los atriibutos del modulo 1.0
    private String nombre;
    private int id;
    private int edad;
    private String monedaCuenta;
    String horaCreacion;  //Ya probé múltiples veces
    long horaAtencion = -1;
    private String tramite;
    private char tipoTramite;  //P: preferencial, B:dos o mas tramites (regular), o A: un solo tramite(rapida)

    //El ticket puede tener un tipo solamente

    //Constructores--------------------------------

    public Ticket(String nombre, int id, int edad, String monedaCuenta, String horaCreacion, long horaAtencion, String tramite, char tipoTramite) {
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

    public char getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(char tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
}
