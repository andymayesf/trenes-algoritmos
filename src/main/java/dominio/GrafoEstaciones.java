package dominio;

import interfaz.Retorno;
import modelo.Conexion;
import modelo.Estacion;
import dominio.Lista.NodoLista;

public class GrafoEstaciones implements IGrafo {
    private Estacion[] estaciones;
    private int maxEstaciones;

    private int cantEstaciones = 0;
    private ILista<Conexion>[][] matrizConexiones;
    public GrafoEstaciones(int maxEstaciones){
        this.maxEstaciones = maxEstaciones;
        this.matrizConexiones = new ILista[maxEstaciones][maxEstaciones];
        this.estaciones = new Estacion[maxEstaciones];
        inicializarMatriz();
    }

    private void inicializarMatriz(){
        for (int i = 0; i < maxEstaciones; i++) {
            for (int j = 0; j < maxEstaciones; j++) {
                matrizConexiones[i][j] = new Lista<Conexion>();
            }
        }
    }
    @Override
    public void agregarEstacion(Estacion e) {
        if(!estaLlena()){
            int index = this.obtenerPosLibre();
            if (index >= 0) {
                this.cantEstaciones++;
                estaciones[index] = e;
            }
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
    public void dijkstra(Estacion origen, Estacion destino, String tipo) {
        boolean[] visitados = new boolean[maxEstaciones];
        double[] costos = new double[maxEstaciones];
        Estacion[] vengo = new Estacion[maxEstaciones];

        int pos = obtenerPos(origen);

        for (int i = 0; i < maxEstaciones; i++) {
            costos[i] = Integer.MAX_VALUE; //Seria nuestro infinito
        }

        costos[pos] = 0;

        for (int v = 0; v < maxEstaciones; v++) {
            //TODO obtenerSig
            int posV = obtenerSiguienteEstacionNoVisitadaDeMenorDistancia(costos, visitados);

            visitados[posV] = true;

            for (int i = 0; i < maxEstaciones; i++) {
                if (!matrizConexiones[posV][i].esVacia() && !visitados[i]) {
                    double distanciaNueva = costos[posV] + matrizConexiones[posV][i].getMenorDato(tipo);
                    if (costos[i] > distanciaNueva) {
                        costos[i] = distanciaNueva;
                        vengo[i] = estaciones[posV];
                    }
                }
            }
        }

        String camino = "";
        int posDestino = obtenerPos(destino);
        camino = estaciones[posDestino] + " " + camino;

        int posDestinoAux = obtenerPos(destino);
        Estacion eAnt = vengo[posDestinoAux];
        camino = eAnt + " " + camino;

        while(eAnt!=null){
            posDestinoAux = obtenerPos(eAnt);
            eAnt = vengo[posDestinoAux];
            if(eAnt!=null){
                camino = eAnt + " " + camino;
            }
        }

    //    System.out.println("El camino del vertice " + origen.toString() + " al vertice B es: " + camino);
    //    System.out.println("El costo del camino entre A y B es: " + costos[posDestino] );
    }
    //TODO: rever metodo
    private int obtenerSiguienteEstacionNoVisitadaDeMenorDistancia(double[] distancias, boolean[] visitados) {
        int posMin = -1;
        double min = Integer.MAX_VALUE; // Infinito

        for (int i = 0; i < maxEstaciones; i++) {
            if (!visitados[i] && distancias[i] < min) {
                min = distancias[i];
                posMin = i;
            }
        }
        return posMin;
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
    public Retorno listarDestinosPorTrasbordos(Estacion estacion, int cantidad) {
        Lista<Estacion> aux = verticesAdyacentes(estacion);
        Lista<Estacion> retorno = new Lista<Estacion>();
        listarDestinosPorTrasbordos(retorno, aux, cantidad);
        return Retorno.ok(retorno.imprimirDatos());
    }

    @Override
    public Retorno caminoMinKm(Estacion origen, Estacion destino) {
        return null;
    }



    private void listarDestinosPorTrasbordos(Lista<Estacion> retorno, Lista<Estacion> estaciones, int cantidad) {
        if(cantidad > 0) {
            NodoLista aux = estaciones.inicio;
            for(int i = 0; i < estaciones.largo; i++ ){
                retorno.insertarOrdenado((Estacion)aux.getDato());
                listarDestinosPorTrasbordos(retorno, verticesAdyacentes((Estacion)aux.getDato()), cantidad -1);
                aux = aux.getSig();
            }
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
