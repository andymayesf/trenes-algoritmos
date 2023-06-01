package modelo;

import interfaz.EstadoCamino;

public class Conexion implements Comparable {
    private Estacion origen;
    private Estacion destino;
    private int id;
    private double costo;
    private double tiempo;
    private double distancia;
    private EstadoCamino estado;

    public Conexion(Estacion origen, Estacion destino, int id, double costo, double tiempo, double distancia, EstadoCamino estado) {
        this.origen = origen;
        this.destino = destino;
        this.id = id;
        this.costo = costo;
        this.tiempo = tiempo;
        this.distancia = distancia;
        this.estado = estado;
    }

    public Estacion getOrigen() {
        return origen;
    }

    public Estacion getDestino() {
        return destino;
    }

    public double getDistancia(){ return this.distancia; }

    public double getCosto(){ return this.costo; }

    public EstadoCamino getEstado(){return this.estado;}

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conexion conexion = (Conexion) o;
        return id == conexion.id;
    }

    public void actualizar(Conexion actualizada) {
        this.costo = actualizada.costo;
        this.tiempo = actualizada.tiempo;
        this.distancia = actualizada.distancia;
        this.estado = actualizada.estado;
    }
}
