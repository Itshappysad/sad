package model;

/**
 * Clase que gestiona la administración de la naviera.
 * Controla clientes, cargas y la gestión del barco.
 */
public class Naviera {
    private Cliente[] clientes;
    private Barco barco;
    private int numClientes;

    /**
     * Constructor de la naviera.
     * Inicializa el barco "El Pirata" con una capacidad de 28000 kg y un máximo de
     * 5 clientes.
     */
    public Naviera() {
        this.clientes = new Cliente[5];
        this.barco = new Barco("El Pirata", 28000);
        this.numClientes = 0;
    }

    /**
     * Agrega un cliente a la naviera.
     *
     * @param cliente Cliente a agregar.
     */
    public void addCliente(Cliente cliente) {
        if (numClientes < clientes.length) {
            clientes[numClientes++] = cliente;
            System.out.println("Cliente agregado.");
        } else {
            System.out.println("No se pueden agregar más clientes.");
        }
    }

    /**
     * Busca un cliente por su NIT.
     *
     * @param nit NIT del cliente.
     * @return Cliente encontrado o `null` si no existe.
     */
    public Cliente buscarClientePorNit(String nit) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getNit().equals(nit)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Agrega una carga al barco si hay espacio disponible.
     *
     * @param nitCliente NIT del cliente.
     * @param tipoCarga  Tipo de carga (1=Normal, 2=Peligrosa, 3=Perecedera).
     * @param numCajas   Número de cajas.
     * @param pesoTotal  Peso total de la carga en kg.
     */
    public void cargarBarco(String nitCliente, int tipoCarga, int numCajas, double pesoTotal) {
        Cliente cliente = buscarClientePorNit(nitCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        String tipoCargaStr = (tipoCarga == 1) ? "Normal" : (tipoCarga == 2) ? "Peligrosa" : "Perecedera";
        Carga nuevaCarga = new Carga(cliente, tipoCargaStr, numCajas, pesoTotal);

        if (barco.agregarCarga(nuevaCarga)) {
            double costo = calcularCosto(pesoTotal, tipoCarga);
            cliente.agregarCarga(pesoTotal, costo);
            System.out.println("Carga añadida con éxito. Valor a pagar: $" + costo);
        } else {
            System.out.println("No se pudo añadir la carga al barco.");
        }
    }

    /**
     * Calcula el costo de transporte de una carga según su tipo.
     *
     * @param peso      Peso de la carga.
     * @param tipoCarga Tipo de carga (1=Normal, 2=Peligrosa, 3=Perecedera).
     * @return Costo total del transporte.
     */
    public double calcularCosto(double peso, int tipoCarga) {
        double tarifa = (tipoCarga == 1) ? 80000 : (tipoCarga == 2) ? 390000 : 250000;
        return peso * tarifa;
    }

    /**
     * Actualiza la categoría de los clientes según sus kilos transportados y dinero
     * pagado.
     */
    public void actualizarCategorias() {
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                if (cliente.getKilosTransportados() >= 55000 || cliente.getValorTotalPagado() >= 2000000) {
                    cliente.setTipoCliente("Oro");
                }
                if (cliente.getValorTotalPagado() >= 5000000) {
                    cliente.setTipoCliente("Platinum");
                }
                if (cliente.getKilosTransportados() >= 35000) {
                    cliente.setTipoCliente("Plata");
                }
            }
        }
        System.out.println("Categorías actualizadas.");
    }

    /**
     * Cambia la categoría de un cliente manualmente.
     *
     * @param nit            NIT del cliente.
     * @param nuevaCategoria Nueva categoría a asignar.
     */
    public void editarCategoriaCliente(String nit, String nuevaCategoria) {
        Cliente cliente = buscarClientePorNit(nit);
        if (cliente != null) {
            cliente.setTipoCliente(nuevaCategoria);
            System.out.println("Categoría actualizada.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Descarga todas las cargas del barco.
     */
    public void descargarBarco() {
        barco.limpiarCarga();
        System.out.println("El barco ha sido descargado.");
    }

    /**
     * Obtiene el peso actual del barco.
     *
     * @return Peso total de las cargas a bordo.
     */
    public double getPesoActualBarco() {
        return barco.getPesoActual();
    }

    /**
     * Obtiene la lista de clientes registrados.
     *
     * @return Arreglo de clientes.
     */
    public Cliente[] getClientes() {
        return clientes;
    }
}
