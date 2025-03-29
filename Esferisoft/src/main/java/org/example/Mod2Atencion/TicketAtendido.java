// Aquí se define en qué clase se definió el ticket y a la vez el nombre de la sucursal
package org.example.Mod2Atencion;
import org.example.Mod1Cajas.Ticket;
/**
 *
 * @author marif
 */
public class TicketAtendido {
    private Ticket ticket;
    private int idCaja;
    private String nombreCaja;
    private String nombreSucursal;

    public TicketAtendido(Ticket ticket, int idCaja, String nombreCaja, String nombreSucursal) {
        this.ticket = ticket;
        this.idCaja = idCaja;
        this.nombreCaja = nombreCaja;
        this.nombreSucursal = nombreSucursal;
    }
    
    //Se insertaron los siguientes getters para acceder a ellos

    public Ticket getTicket() {
        return ticket;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public String getNombreCaja() {
        return nombreCaja;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }
    
}
