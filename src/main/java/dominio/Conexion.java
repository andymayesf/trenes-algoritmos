package dominio;

import interfaz.EstadoCamino;
import modelo.Estacion;

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
}
