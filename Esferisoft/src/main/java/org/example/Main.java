package org.example;

import org.example.Mod0.FuncionConfig;
import org.example.Mod1Cajas.ManagerCajas;
import org.example.Mod1TODELETE.CreacionTicket;

public class Main {
    public static void main(String[] args) {
        //Creamos los nuevos objetos de los diferentes modulos
        FuncionConfig funcionConfig = new FuncionConfig();
        ManagerCajas managerCajas = new ManagerCajas();

        funcionConfig.config();
        managerCajas.managerCajas();


    }
}
