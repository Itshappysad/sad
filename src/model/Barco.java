package model;

import java.util.ArrayList;

/**
 * Representa un barco con una capacidad máxima de carga.
 * Permite agregar y eliminar cargas respetando la capacidad máxima.
 */
public class Barco {
    private String nombre;
    private double capacidad;
    private ArrayList<Carga> misCargas;

    /**
     * Constructor del barco.
     *
     * @param nombre    Nombre del barco.
     * @param capacidad Capacidad máxima en kilogramos.
     */
    public Barco(String nombre, double capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.misCargas = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del barco.
     *
     * @return Nombre del barco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la capacidad máxima del barco.
     *
     * @return Capacidad en kilogramos.
     */
    public double getCapacidad() {
        return capacidad;
    }

    /**
     * Calcula el peso total de todas las cargas a bordo.
     *
     * @return Peso total de las cargas.
     */
    public double getPesoTotal() {
        double total = 0;
        for (Carga carga : misCargas) {
            total += carga.getPesoTotal();
        }
        return total;
    }

    /**
     * Obtiene el peso actual del barco.
     *
     * @return Peso total de las cargas.
     */
    public double getPesoActual() {
        return getPesoTotal();
    }

    /**
     * Elimina todas las cargas del barco.
     */
    public void limpiarCarga() {
        misCargas.clear();
    }

    /**
     * Agrega una carga al barco si no excede la capacidad.
     *
     * @param nuevaCarga Carga a agregar.
     * @return `true` si la carga se agregó correctamente, `false` si excede la
     *         capacidad.
     */
    public boolean agregarCarga(Carga nuevaCarga) {
        if (getPesoTotal() + nuevaCarga.getPesoTotal() > capacidad) {
            return false;
        }
        misCargas.add(nuevaCarga);
        return true;
    }
}
