package dominio;

import interfaz.Retorno;
import modelo.Conexion;
import modelo.Estacion;
import dominio.Lista.NodoLista;

import java.sql.SQLOutput;

public class GrafoEstaciones implements IGrafo {
    private Estacion[] estaciones;
    private int maxEstaciones;

    private int cantEstaciones = 0;
    private ListaConexion[][] matrizConexiones;
    public GrafoEstaciones(int maxEstaciones){
        this.maxEstaciones = maxEstaciones;
        this.matrizConexiones = new ListaConexion[maxEstaciones][maxEstaciones];
        this.estaciones = new Estacion[maxEstaciones];
        inicializarMatriz();
    }

    private void inicializarMatriz(){
        for (int i = 0; i < maxEstaciones; i++) {
            for (int j = 0; j < maxEstaciones; j++) {
                matrizConexiones[i][j] = new ListaConexion();
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

        matrizConexiones[posOrigen][posDestino].insertar(nueva);
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
    public Retorno dijkstra(Estacion origen, Estacion destino, String tipo) {
        boolean[] visitados = new boolean[maxEstaciones];
        double[] costos = new double[maxEstaciones];
        Estacion[] vengo = new Estacion[maxEstaciones];

        int pos = obtenerPos(origen);

        for (int i = 0; i < maxEstaciones; i++) {
            costos[i] = Integer.MAX_VALUE;
        }

        costos[pos] = 0;

        for (int v = 0; v < maxEstaciones; v++) {
            int posV = obtenerSiguienteEstacionNoVisitadaDeMenorDistancia(costos, visitados);

            if(posV > -1){
                visitados[posV] = true;
                for (int i = 0; i < maxEstaciones; i++) {
                    if (!matrizConexiones[posV][i].esVacia() && !visitados[i]) {
                        System.out.println(matrizConexiones[posV][i].imprimirDatos());
                        double distanciaNueva = costos[posV] + matrizConexiones[posV][i].getMenorDato(tipo);
                        System.out.println(distanciaNueva);
                        if (costos[i] > distanciaNueva) {
                            costos[i] = distanciaNueva;
                            vengo[i] = estaciones[posV];
                        }
                    }
                }
            }

        }

        int posDestino = obtenerPos(destino);

        if(costos[posDestino] == Integer.MAX_VALUE){
            return Retorno.error3("No existe el camino");
        }

        String camino = "";
        camino = estaciones[posDestino].toString() + "|" + camino;

        int posDestinoAux = obtenerPos(destino);
        Estacion eAnt = vengo[posDestinoAux];
        camino = eAnt.toString() + "|" + camino;

        while(eAnt!=null){
            posDestinoAux = obtenerPos(eAnt);
            eAnt = vengo[posDestinoAux];
            if(eAnt!=null){
                camino = eAnt.toString() + "|" + camino;
            }
        }
        if(camino.length() > 0){
            camino = camino.substring(0, camino.length()-1);
        }
        if(costos[posDestino] > 0)
            return Retorno.ok((int) costos[posDestino],camino);
        return  Retorno.error3("No hay caminos posibles");
    }

    private int obtenerSiguienteEstacionNoVisitadaDeMenorDistancia(double[] distancias, boolean[] visitados) {
        int posMin = -1;
        double min = Integer.MAX_VALUE;

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

        Conexion vieja = matrizConexiones[posOrigen][posDestino].recuperar(actualizada);
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
    public Retorno listarDestinosPorTrasbordos(Estacion estacion, int distancia) {
        String ret = listarDestinosPorTrasbordosBfs(estacion, distancia);
        return Retorno.ok(ret);
    }

    private String listarDestinosPorTrasbordosBfs(Estacion origen, int distancia) {
        int pos = obtenerPos(origen);
        boolean[] visitados = new boolean[maxEstaciones];
        Cola<Tupla> cola = new Cola<Tupla>();

        String ret = "";
        visitados[pos] = true;
        cola.encolar(new Tupla(pos, 0));

        Lista<Estacion> retEstaciones = new Lista<>();

        while (!cola.esVacia() && distancia >= 0) {
            Tupla tuplaDesencolada = cola.desencolar();

            retEstaciones.insertarOrdenado(estaciones[tuplaDesencolada.pos]);

            for (int i = 0; i < maxEstaciones; i++) {
                if (!this.matrizConexiones[tuplaDesencolada.pos][i].esVacia() && !visitados[i]) {
                    cola.encolar(new Tupla(i, tuplaDesencolada.salto + 1));
                    visitados[i] = true;
                }
            }

            distancia--;
        }

        System.out.println(ret);
        return retEstaciones.imprimirDatos();
    }
    @Override
    public Retorno caminoMinKm(Estacion origen, Estacion destino) {
        return dijkstra(origen, destino, "distancia");
    }

    @Override
    public Retorno caminoMinEuros(Estacion origen, Estacion destino) {
        return dijkstra(origen, destino, "costo");
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

    private class Tupla {
        private int pos;
        private int salto;

        public Tupla(int pos, int salto) {
            this.pos = pos;
            this.salto = salto;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getSalto() {
            return salto;
        }

        public void setSalto(int salto) {
            this.salto = salto;
        }
    }
}
