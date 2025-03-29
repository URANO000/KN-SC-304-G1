package org.example;

import org.example.Mod0.Menu;
import org.example.Mod1Cajas.ManagerCajas;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import org.example.Mod0.ConfigSucursal;
import org.example.Mod1Cajas.ListaCajas;
import org.example.Mod2Atencion.ManagerAtencion;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // Crear objetos
        Menu menu = new Menu();
        menu.mostrarMenu();

        ManagerCajas managerCajas = new ManagerCajas();
        managerCajas.menuCajas();

        // Recuperar los objetos necesarios
        ListaCajas listaCajas = managerCajas.getListaCajas();
        ConfigSucursal config = menu.getConfiguracion();

        // Usar ManagerAtencion con los datos correctos
        ManagerAtencion managerAtencion = new ManagerAtencion(listaCajas, config.getNombreSucursal());
        managerAtencion.mostrarMenuAtencion();
    }
}
