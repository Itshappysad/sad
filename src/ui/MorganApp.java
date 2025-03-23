package ui;

import model.Naviera;
import model.Cliente;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MorganApp {
    private Naviera naviera;
    private Scanner scanner;

    public MorganApp() {
        this.naviera = new Naviera();
        this.scanner = new Scanner(System.in);
    }

    /**
     * El Menu :)
     */
    public void menu() {
        int opcion;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Cargar Barco");
            System.out.println("3. Ver Clientes");
            System.out.println("4. Actualizar Categorias");
            System.out.println("5. Descargar Barco");
            System.out.println("6. Mostrar peso actual del barco");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    cargarBarco();
                    break;
                case 3:
                    mostrarClientes();
                    break;
                case 4:
                    actualizarCategoriaClientes();
                    break;
                case 5:
                    descargarBarco();
                    break;
                case 6:
                    mostrarPesoBarco();
                    break;
                case 7:
                    System.out.println("¡Hasta luego, Capitan Morgan!");
                    break;
                default:
                    System.out.println("Opción invalida.");
            }
        } while (opcion != 7);
    }

    /**
     * Pedir los datos para crear el cliente
     */
    private void crearCliente() {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el NIT: ");
        String nit = scanner.nextLine();
        LocalDate fechaExpedicion = ingresarFecha();

        Cliente nuevoCliente = new Cliente(nombre, nit, fechaExpedicion, "Normal");
        naviera.addCliente(nuevoCliente);
        System.out.println("Cliente registrado correctamente.");
    }

    /**
     * Permite al usuario ingresar una fecha en formato "dd/MM/yyyy" y la convierte
     * en LocalDate
     * 
     * @return la fecha ingresada como localDate
     */
    private LocalDate ingresarFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.print("Ingrese la fecha de expedición (DD/MM/AAAA): ");
            String fechaStr = scanner.nextLine().trim();

            try {
                return LocalDate.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use DD/MM/AAAA.");
            }
        }
    }

    /**
     * Pedir datos para cargar el barco
     */
    private void cargarBarco() {
        System.out.print("Ingrese el NIT del cliente: ");
        String nit = scanner.nextLine();

        System.out.println("Seleccione el tipo de carga:");
        System.out.println("1 - Normal");
        System.out.println("2 - Peligrosa");
        System.out.println("3 - Perecedera");
        System.out.print("Opción: ");
        int tipoCarga = scanner.nextInt();

        System.out.print("Ingrese la cantidad de cajas: ");
        int numCajas = scanner.nextInt();
        System.out.print("Ingrese el peso por caja (en gramos): ");
        double pesoPorCaja = scanner.nextDouble();
        scanner.nextLine();

        double pesoTotal = (pesoPorCaja * numCajas) / 1000; // Convertir gramos a kg
        naviera.cargarBarco(nit, tipoCarga, numCajas, pesoTotal);
    }

    /**
     * Mostrar los clientes existentes
     */
    private void mostrarClientes() {
        System.out.println("\n Lista de clientes:");
        boolean hayClientes = false;

        for (Cliente cliente : naviera.getClientes()) {
            if (cliente != null) { // Validamos que el cliente no sea null antes de acceder a sus datos
                System.out.println(cliente.getNit() + " | " + cliente.getNombre() + " | Tipo: "
                        + cliente.getTipoCliente());
                hayClientes = true;
            }
        }

        if (!hayClientes) {
            System.out.println("No hay clientes registrados.");
        }
    }

    /**
     * Actualizar la categoria de los clientes, con dos opciones automatica y manual
     */
    private void actualizarCategoriaClientes() {
        System.out.println("\n Actualizar categorias de clientes");

        System.out.println("1. Actualizar automaticamente todas las categorias");
        System.out.println("2. Editar manualmente la categoria de un cliente");
        System.out.print("Seleccione una opcion: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                naviera.actualizarCategorias();
                System.out.println("Categorias actualizadas automaticamente.");
                break;
            case 2:
                editarCategoriaManual();
                break;
            default:
                System.out.println("Opción invalida.");
        }
    }

    /**
     * Poder editar manualmente la categoria del cliente pidiendo el nit
     */
    private void editarCategoriaManual() {
        System.out.print("Ingrese el NIT del cliente que desea editar: ");
        String nit = scanner.nextLine();
        Cliente cliente = naviera.buscarClientePorNit(nit);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("\n Categorias disponibles:");
        System.out.println("1. Normal");
        System.out.println("2. Plata");
        System.out.println("3. Oro");
        System.out.println("4. Platinum");
        System.out.print("Seleccione la nueva categoria: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String nuevaCategoria;
        switch (opcion) {
            case 1:
                nuevaCategoria = "Normal";
                break;
            case 2:
                nuevaCategoria = "Plata";
                break;
            case 3:
                nuevaCategoria = "Oro";
                break;
            case 4:
                nuevaCategoria = "Platinum";
                break;
            default:
                System.out.println("Opción invalida. No se actualizo la categoria.");
                return;
        }

        cliente.setTipoCliente(nuevaCategoria);
        System.out.println("Categoria actualizada con exito a: " + nuevaCategoria);
    }

    private void descargarBarco() {
        naviera.descargarBarco();
    }

    private void mostrarPesoBarco() {
        double peso = naviera.getPesoActualBarco();
        System.out.println("El barco actualmente transporta " + peso + " kg.");
    }

    public static void main(String[] args) {
        MorganApp app = new MorganApp();
        app.menu();
    }
}