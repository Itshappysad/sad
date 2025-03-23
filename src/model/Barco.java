package model;

public class Barco {
    // Atributos
    private String nombre;
    private double capacidad;

    // Relaciones
    private Carga[] misCargas;

    // Constructor
    public Barco(String n, double c) {
        this.nombre = n;
        this.capacidad = c;
        misCargas = new Carga[200]; // Se mantiene el arreglo
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String newNombre) {
        nombre = newNombre;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double newCap) {
        capacidad = newCap;
    }

    /**
     * Retorna el peso actual de la carga en el barco
     * 
     * @return Peso total de la carga
     */
    public double getPesoTotal() {
        double total = 0;
        for (int i = 0; i < misCargas.length; i++) {
            if (misCargas[i] != null) {
                total += misCargas[i].getPesoTotal();
            }
        }
        return total;
    }

    // Método para obtener el peso actual en el barco
    public double getPesoActual() {
        return getPesoTotal();
    }

    // Método para limpiar la carga después de un viaje
    public void limpiarCarga() {
        for (int i = 0; i < misCargas.length; i++) {
            misCargas[i] = null;
        }
    }

    // Método para agregar carga si es posible
    public boolean agregarCarga(Carga nuevaCarga) {
        if (getPesoTotal() + nuevaCarga.getPesoTotal() > capacidad) {
            return false;
        }

        for (int i = 0; i < misCargas.length; i++) {
            if (misCargas[i] == null) {
                misCargas[i] = nuevaCarga;
                return true;
            }
        }
        return false;
    }

}
