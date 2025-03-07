package org.example.Mod0;

public class ConfigSucursal {
    private String nombreSucursal;
    private int totalCajas;
    private ListaEnlazada tiposCajas;
    private ListaEnlazada usuarios;

    // Getters and Setters
    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public int getTotalCajas() {
        return totalCajas;
    }

    public void setTotalCajas(int totalCajas) {
        this.totalCajas = totalCajas;
    }

    public ListaEnlazada getTiposCajas() {
        return tiposCajas;
    }

    public void setTiposCajas(ListaEnlazada tiposCajas) {
        this.tiposCajas = tiposCajas;
    }

    public ListaEnlazada getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ListaEnlazada usuarios) {
        this.usuarios = usuarios;
    }
}