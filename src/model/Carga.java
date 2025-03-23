package model;

public class Carga {
    private Cliente cliente;
    private String tipoCarga;
    private int numCajas;
    private double pesoTotal;

    // Construtor
    public Carga(Cliente cliente, String tipoCarga, int numCajas, double pesoTotal) {
        this.cliente = cliente;
        this.tipoCarga = tipoCarga;
        this.numCajas = numCajas;
        this.pesoTotal = pesoTotal;
    }

    // Getters
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
