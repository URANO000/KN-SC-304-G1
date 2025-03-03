package org.example.Mod0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionConfig {
    public void config() {
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

            // Validar que haya al menos 2 cajas
            if (totalBoxes < 2) {
                System.out.println("Debe haber al menos 2 cajas: una preferencial y una rapida.");
                return;
            }

            // Solicitar los tipos de cajas
            List<String> boxTypes = new ArrayList<>();
            boolean hasPreferential = false;
            boolean hasFast = false;

            for (int i = 1; i <= totalBoxes; i++) {
                System.out.print("Ingrese el tipo de la caja " + i + " (preferencial/rapida/normal): ");
                String type = scanner.nextLine().toLowerCase();

                // Validar el tipo de caja
                while (!type.equals("preferencial") && !type.equals("rapida") && !type.equals("normal")) {
                    System.out.print("Tipo no válido. Ingrese 'preferencial', 'rapida' o 'normal': ");
                    type = scanner.nextLine().toLowerCase();
                }

                boxTypes.add(type);

                // Verificar si hay al menos una caja preferencial y una rapida
                if (type.equals("preferencial")) {
                    hasPreferential = true;
                } else if (type.equals("rapida")) {
                    hasFast = true;
                }
            }

            if (!hasPreferential || !hasFast) {
                System.out.println("Debe haber al menos una caja preferencial y una caja rapida.");
                return;
            }

            // Solicitar datos de los usuarios
            List<User> users = new ArrayList<>();
            System.out.println("Ingrese los datos de los usuarios:");
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
            config.setBoxTypes(boxTypes);
            config.setUsers(users);

            // Guardar la configuración en el archivo JSON
            ConfigManager.saveConfig(config);
            System.out.println("Configuración guardada exitosamente en config.json.");
        } else {
            // Si existe, cargar la configuración
            System.out.println("Configuración cargada:");
            System.out.println("Nombre de la sucursal: " + config.getBranchName());
            System.out.println("Número de cajas: " + config.getTotalBoxes());
            System.out.println("Tipos de cajas: " + config.getBoxTypes());
            System.out.println("Usuarios:");
            for (User user : config.getUsers()) {
                System.out.println("- Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }
        }
    }
}