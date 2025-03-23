package model;

import java.util.Scanner;

public class Validacion {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Valida que el usuario ingrese un número entero
     * 
     * @return Número entero ingresado
     */
    public static int validarEntrada() {

        while (true) {
            System.out.print("Ingrese una opción valida: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                return opcion;
            } else {
                System.out.println("Error: Entrada no válida. Inténtelo de nuevo.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Valida que el usuario ingrese un numero natural
     * 
     * @return Número natural ingresado
     */
    public static double validarDouble() {
        while (true) {
            System.out.print("Ingrese un número válido: ");
            if (scanner.hasNextDouble()) {
                double numero = scanner.nextDouble();
                scanner.nextLine();
                return numero;
            } else {
                System.out.println("Error: Entrada no válida. Inténtelo de nuevo.");
                scanner.nextLine();
            }
        }
    }
}
