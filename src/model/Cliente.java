package model;

import java.time.LocalDate;

public class Cliente {
    private String nombre;
    private String nit;
    private LocalDate fechaExpedicion;
    private String tipoCliente;
    private double kilosTransportados;
    private double valorTotalPagado;

    /**
     * Constructor del cliente
     * 
     * @param nombre          del cliente
     * @param nit             del cliente
     * @param fechaExpedicion del cliente
     * @param tipoCliente     la categoria del cliente
     */
    public Cliente(String nombre, String nit, LocalDate fechaExpedicion, String tipoCliente) {
        this.nombre = nombre;
        this.nit = nit;
        this.fechaExpedicion = fechaExpedicion;
        this.tipoCliente = tipoCliente;
        this.kilosTransportados = 0;
        this.valorTotalPagado = 0;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public double getKilosTransportados() {
        return kilosTransportados;
    }

    public double getValorTotalPagado() {
        return valorTotalPagado;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void agregarCarga(double kilos, double costo) {
        this.kilosTransportados += kilos;
        this.valorTotalPagado += costo;
    }
}