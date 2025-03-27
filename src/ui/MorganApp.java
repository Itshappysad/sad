package ui;

import model.Naviera;
import model.Cliente;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase principal que gestiona la interfaz de usuario del sistema de la
 * naviera.
 * Permite la creación de clientes, carga del barco, actualización de
 * categorías,
 * y otras funciones relacionadas con la administración del barco y clientes.
 */
public class MorganApp {
    private Naviera naviera;
    private Scanner scanner;

    /**
     * Constructor de la aplicación.
     * Inicializa la naviera y el escáner para la entrada de datos.
     */
    public MorganApp() {
        this.naviera = new Naviera();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método principal del menú de la aplicación.
     * Permite al usuario navegar entre las opciones del sistema.
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
            scanner.nextLine(); // Limpiar buffer

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
     * Permite al usuario ingresar los datos necesarios para registrar un nuevo
     * cliente.
     * Se solicita el nombre, NIT y la fecha de expedición.
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
     * en LocalDate.
     *
     * @return La fecha ingresada como LocalDate.
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
     * Permite al usuario ingresar los datos necesarios para cargar el barco con una
     * carga.
     * Se solicita el NIT del cliente, el tipo de carga, el número de cajas y el
     * peso por caja.
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
        scanner.nextLine(); // Limpiar buffer

        double pesoTotal = (pesoPorCaja * numCajas) / 1000; // Convertir gramos a kg
        naviera.cargarBarco(nit, tipoCarga, numCajas, pesoTotal);
    }

    /**
     * Muestra la lista de clientes registrados en la naviera.
     */
    private void mostrarClientes() {
        System.out.println("\nLista de clientes:");
        boolean hayClientes = false;

        for (Cliente cliente : naviera.getClientes()) {
            if (cliente != null) {
                System.out.println(
                        cliente.getNit() + " | " + cliente.getNombre() + " | Tipo: " + cliente.getTipoCliente());
                hayClientes = true;
            }
        }

        if (!hayClientes) {
            System.out.println("No hay clientes registrados.");
        }
    }

    /**
     * Permite actualizar la categoría de los clientes.
     * Se ofrece la opción de actualización automática o manual.
     */
    private void actualizarCategoriaClientes() {
        System.out.println("\nActualizar categorías de clientes");
        System.out.println("1. Actualizar automáticamente todas las categorías");
        System.out.println("2. Editar manualmente la categoría de un cliente");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                naviera.actualizarCategorias();
                System.out.println("Categorías actualizadas automáticamente.");
                break;
            case 2:
                editarCategoriaManual();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    /**
     * Permite al usuario editar manualmente la categoría de un cliente mediante su
     * NIT.
     */
    private void editarCategoriaManual() {
        System.out.print("Ingrese el NIT del cliente que desea editar: ");
        String nit = scanner.nextLine();
        Cliente cliente = naviera.buscarClientePorNit(nit);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("\nCategorías disponibles:");
        System.out.println("1. Normal");
        System.out.println("2. Plata");
        System.out.println("3. Oro");
        System.out.println("4. Platinum");
        System.out.print("Seleccione la nueva categoría: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

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
                System.out.println("Opción inválida. No se actualizó la categoría.");
                return;
        }

        cliente.setTipoCliente(nuevaCategoria);
        System.out.println("Categoría actualizada con éxito a: " + nuevaCategoria);
    }

    /**
     * Descarga todas las cargas del barco.
     */
    private void descargarBarco() {
        naviera.descargarBarco();
    }

    /**
     * Muestra el peso actual del barco con las cargas transportadas.
     */
    private void mostrarPesoBarco() {
        double peso = naviera.getPesoActualBarco();
        System.out.println("El barco actualmente transporta " + peso + " kg.");
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        MorganApp app = new MorganApp();
        app.menu();
    }
}
