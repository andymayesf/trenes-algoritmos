package dominio;

import interfaz.Consulta;
import modelo.Estacion;

public class GrafoEstaciones implements IGrafo {
    private Estacion[] estaciones;
    private int maxEstaciones;

    private int cantEstaciones = 0;
    private ILista<Conexion>[][] matrizConexiones;
    public GrafoEstaciones(int maxEstaciones){
        this.maxEstaciones = maxEstaciones;
        this.matrizConexiones = new ILista[maxEstaciones][maxEstaciones];
        this.estaciones = new Estacion[maxEstaciones];
    }
    @Override
    public void agregarEstacion(Estacion e) {
        this.cantEstaciones++;
        int index = this.obtenerPosLibre();
        if (index >= 0) {
            estaciones[index] = e;
        }
    }
    @Override
    public boolean existeEstacion(Estacion e){
        if(this.obtenerPos(e) == -1)
            return false;
        return true;
    }

    @Override
    public void agregarArista(String origen, String destino, int costo, int distancia) {

    }

    @Override
    public void eliminarVertice(String v) {

    }

    @Override
    public void eliminarArista(int origen, int destino) {

    }

    @Override
    public boolean sonAdyacentes(String a, String b) {
        return false;
    }

    @Override
    public ILista verticesAdyacentes(String v) {
        return null;
    }

    @Override
    public boolean esVacio() {
        return false;
    }

    @Override
    public boolean estaLlena() {
        return maxEstaciones == cantEstaciones;
    }

    @Override
    public void dijkstra(String vert) {

    }

    private int obtenerPos(Estacion e) {
        for (int i = 0; i < maxEstaciones; i++) {
            if (estaciones[i] != null && estaciones[i].equals(e))
                return i;
        }
        return -1;
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < maxEstaciones; i++) {
            if (estaciones[i] == null) {
                return i;
            }
        }
        return -1;
    }

}
