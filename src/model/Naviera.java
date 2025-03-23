package model;

public class Naviera {
    private Cliente[] clientes;
    private Barco barco;
    private int numClientes;

    /**
     * Inicializar el barco y el array de clientes
     */
    public Naviera() {
        this.clientes = new Cliente[5];
        this.barco = new Barco("El Pirata", 28000);
        this.numClientes = 0;
    }

    /**
     * Agrega un cliente a la lista si hay espacios disponibles
     * 
     * @param cliente a agregar
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
     * Busca un cliente por su NIT
     * 
     * @param nit del cliente
     * @return Cliente encontrado o null si no existe
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
     * Cargar el Barco pasandole los siguientes datos
     * 
     * @param nitCliente
     * @param tipoCarga
     * @param numCajas
     * @param pesoTotal
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
     * Calcular el precio a pagar
     * 
     * @param peso
     * @param tipoCarga
     * @return retorna el precio total
     */
    public double calcularCosto(double peso, int tipoCarga) {
        double tarifa = (tipoCarga == 1) ? 80000 : (tipoCarga == 2) ? 390000 : 250000;
        return peso * tarifa;
    }

    /**
     * Actualiza la categoria automaticamente
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
     * Actualizar la categoria manualmente
     * 
     * @param nit
     * @param nuevaCategoria
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

    public void descargarBarco() {
        barco.limpiarCarga();
        System.out.println("El barco ha sido descargado.");
    }

    public double getPesoActualBarco() {
        return barco.getPesoActual();
    }

    public Cliente[] getClientes() {
        return clientes;
    }
}
