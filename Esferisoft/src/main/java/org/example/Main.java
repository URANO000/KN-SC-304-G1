package org.example;

import org.example.Mod0.FuncionConfig;
import org.example.Mod1.CreacionTicket;

public class Main {
    public static void main(String[] args) {
        //Creamos los nuevos objetos de los diferentes modulos
        FuncionConfig funcionConfig = new FuncionConfig();
        CreacionTicket creacionTicket = new CreacionTicket();

        funcionConfig.config();
        creacionTicket.funcionesTicket();


    }
}
