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

            // Registro de usuarios
            List<User> users = new ArrayList<>();
            System.out.println("Registrarme:");
            while (true) {
                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                users.add(new User(username, password));

                System.out.print("¿Desea agregar otro usuario? (si/no): ");
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("si")) {
                    break;
                }
            }

            // Creacion de la configuración
            config = new BranchConfig();
            config.setBranchName(branchName);
            config.setTotalBoxes(totalBoxes);
            config.setUsers(users);

            // Guardar la configuración en el archivo JSON
            ConfigManager.saveConfig(config);
            System.out.println("Configuración guardada exitosamente en config.json.");
        } else {
            // Si existe, cargar la configuración
            System.out.println("Configuración cargada:");
            System.out.println("Nombre de la sucursal: " + config.getBranchName());
            System.out.println("Número de cajas: " + config.getTotalBoxes());
            System.out.println("Usuarios:");
            for (User user : config.getUsers()) {
                System.out.println("- Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }
        }
    }
}