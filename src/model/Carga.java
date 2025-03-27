package model;

/**
 * Representa una carga transportada en el barco.
 */
public class Carga {
    private Cliente cliente;
    private String tipoCarga;
    private int numCajas;
    private double pesoTotal;

    /**
     * Constructor de la carga.
     *
     * @param cliente   Cliente propietario de la carga.
     * @param tipoCarga Tipo de carga (Normal, Peligrosa, Perecedera).
     * @param numCajas  Número de cajas transportadas.
     * @param pesoTotal Peso total en kilogramos.
     */
    public Carga(Cliente cliente, String tipoCarga, int numCajas, double pesoTotal) {
        this.cliente = cliente;
        this.tipoCarga = tipoCarga;
        this.numCajas = numCajas;
        this.pesoTotal = pesoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }
}
