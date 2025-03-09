package org.example;

import org.example.Mod0.Menu;
import org.example.Mod1Cajas.ManagerCajas;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        //Creamos los nuevos objetos de los diferentes modulos
        ManagerCajas managerCajas = new ManagerCajas();

        Menu menu = new Menu();

        menu.mostrarMenu();
        managerCajas.menuCajas();


    }
}
