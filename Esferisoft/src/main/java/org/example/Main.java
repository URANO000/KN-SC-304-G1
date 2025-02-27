package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Verificar si el archivo de configuración existe
        BranchConfig config = ConfigManager.loadConfig();

        if (config == null) {
            // Si no existe, solicitar la información al usuario
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese el nombre de la sucursal: ");
            String branchName = scanner.nextLine();

            System.out.print("Ingrese el número total de cajas: ");
            int totalBoxes = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Creación de usuarios
            List<User> users = new ArrayList<>();
            users.add(new User("admin", "admin123"));
            users.add(new User("user1", "user123"));

            // Creación de la configuración
            config = new BranchConfig();
            config.setBranchName(branchName);
            config.setTotalBoxes(totalBoxes);
            config.setUsers(users);

            // Guardar la configuración en el archivo
            ConfigManager.saveConfig(config);
            System.out.println("Configuración guardada exitosamente.");
        } else {
            // Si existe, cargar la configuración
            System.out.println("Configuración cargada:");
            System.out.println("Nombre de la sucursal: " + config.getBranchName());
            System.out.println("Número de cajas: " + config.getTotalBoxes());
        }
    }
}