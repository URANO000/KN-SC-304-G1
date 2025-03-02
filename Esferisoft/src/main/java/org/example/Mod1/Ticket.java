package org.example.Mod1;

import java.time.*;

public class Ticket {
    //Primeramente los atriibutos del modulo 1.0
    private String nombre;
    private int id;
    private int edad;
    private LocalTime horaCreacion;
    private int horaAtencion;
    private String tramite;  //P: preferencial, A: un solo tramite, B: dos o mas tramites

    //El ticket puede tener un tipo solamente

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

    public LocalTime getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(LocalTime horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public int getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(int horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }


}
