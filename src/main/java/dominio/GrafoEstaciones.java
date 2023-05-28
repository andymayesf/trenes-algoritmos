package dominio;

import modelo.Conexion;
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
    public void agregarConexion(Conexion nueva) {
        int posOrigen = obtenerPos(nueva.getOrigen());
        int posDestino = obtenerPos(nueva.getDestino());

        ILista<Conexion> conexiones = matrizConexiones[posOrigen][posDestino];
        conexiones.insertar(nueva);
    }

    @Override
    public void eliminarVertice(String v) {

    }

    @Override
    public void eliminarArista(int origen, int destino) {

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

    @Override
    public boolean existeConexion(Conexion nueva) {
        int posOrigen = obtenerPos(nueva.getOrigen());
        int posDestino = obtenerPos(nueva.getDestino());

        ILista<Conexion> conexiones = matrizConexiones[posOrigen][posDestino];
        return conexiones.existe(nueva);
    }

    @Override
    public void actualizarConexion(Conexion actualizada) {
        int posOrigen = obtenerPos(actualizada.getOrigen());
        int posDestino = obtenerPos(actualizada.getDestino());

        ILista<Conexion> conexiones = matrizConexiones[posOrigen][posDestino];
        Conexion vieja = conexiones.recuperar(actualizada);
        vieja.actualizar(actualizada);
    }

    @Override
    public boolean sonAdyacentes(Estacion origen, Estacion destino) {
        int obtenerPosOrigen = obtenerPos(origen);
        int obtenerPosDestino = obtenerPos(destino);

        if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {
            return !matrizConexiones[obtenerPosOrigen][obtenerPosDestino].esVacia();
        } else {
            return false;
        }
    }

    @Override
    public void listarDestinosPorTrasbordos(Estacion estacion, int cantidad) {
        Lista<Estacion> aux = verticesAdyacentes(estacion);

        listarDestinosPorTrasbordos(aux, cantidad - 1);
    }

    private void listarDestinosPorTrasbordos(Lista<Estacion> estaciones, int cantidad) {
        if(cantidad > 0) {
            // Foreach estacion : estaciones
                // print estacion
                // aux = verticesAdyacentes(estacion);
                // listarDestinosPorTrasbordos(estaciones)
        } else {
            return;
        }
    }

    public Lista<Estacion> verticesAdyacentes(Estacion estacion) {
        Lista<Estacion> estacionesAdy = new Lista();
        int obtenerPosOrigen = obtenerPos(estacion);

        if (obtenerPosOrigen >= 0) {
            for (int i = 0; i < maxEstaciones; i++) {
                if (sonAdyacentes(estacion, estaciones[i])) {
                    estacionesAdy.insertar(estaciones[i]);
                }
            }
        }

        return estacionesAdy;
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
