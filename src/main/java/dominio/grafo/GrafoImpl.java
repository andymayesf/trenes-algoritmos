package dominio.grafo;
import dominio.cola.Cola;
import dominio.lista.ListaInt;

import java.util.Objects;

public class GrafoImpl implements Grafo {
    //Matriz de adyacencia
    private final int cantMaxVertices;
    private int cantVertices;
    private boolean esDirigido;
    private Arista[][] matrizAdyacencia;
    private Vertice[] vertices;

    public GrafoImpl(int cantMaxVertices, boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.cantMaxVertices = cantMaxVertices;
        this.matrizAdyacencia = new Arista[cantMaxVertices][cantMaxVertices];
        this.vertices = new Vertice[cantMaxVertices];

        for (int i = 0; i < cantMaxVertices; i++) {
            for (int j = 0; j < cantMaxVertices; j++) {
                matrizAdyacencia[i][j] = new Arista();
            }
        }
    }

    @Override
    public void agregarVertice(String nombre) {
        cantVertices++;
        int posLibre = obtenerPosLibre();
        if (posLibre >= 0) {
            vertices[posLibre] = new Vertice(nombre);
        }
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String vert) {
        for (int i = 0; i < cantMaxVertices; i++) {
            if (vertices[i] != null && vertices[i].equals(new Vertice(vert)))
                return i;
        }
        return -1;
    }

    @Override
    public void agregarArista(String origen, String destino, int costo, int distancia) {
        int obtenerPosOrigen = obtenerPos(origen);
        int obtenerPosDestino = obtenerPos(destino);
        if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {
            matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino] = new Arista(costo, distancia);
            if (!esDirigido) {
                matrizAdyacencia[obtenerPosDestino][obtenerPosOrigen] = matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino];
            }
        }
    }

    @Override
    public void eliminarVertice(String vert) {
        cantVertices--;
        int posVert = obtenerPos(vert);
        if (posVert >= 0) {
            vertices[posVert] = null;
            for (int i = 1; i <= cantMaxVertices; i++) {
                matrizAdyacencia[posVert][i].setExiste(false); //filas
                matrizAdyacencia[posVert][i].setCosto(0); //filas
                matrizAdyacencia[posVert][i].setDistancia(0); //filas

                matrizAdyacencia[i][posVert].setExiste(false); //columnas
                matrizAdyacencia[i][posVert].setCosto(0); //columnas
                matrizAdyacencia[i][posVert].setDistancia(0); //columnas
            }
        }
    }

    @Override
    public void eliminarArista(int origen, int destino) {
        matrizAdyacencia[origen][destino].setExiste(false);
        matrizAdyacencia[origen][destino].setCosto(0);
        matrizAdyacencia[origen][destino].setDistancia(0);
    }

    @Override
    public boolean existeVertice(String vert) {
        return obtenerPos(vert) >= 0;
    }

    @Override
    public boolean sonAdyacentes(String a, String b) {
        int obtenerPosOrigen = obtenerPos(a);
        int obtenerPosDestino = obtenerPos(b);
        if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {
            return matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino].isExiste();
        } else {
            return false;
        }
    }

    @Override
    public ListaInt verticesAdyacentes(String v) {
        ListaInt verticesAdy = new ListaInt();
        int obtenerPosOrigen = obtenerPos(v);
        if (obtenerPosOrigen >= 0) {
            for (int i = 0; i < cantMaxVertices; i++) {
                if (sonAdyacentes(v, vertices[i].nombre)) {
                    verticesAdy.insertar(i);
                }
            }
        }
        return verticesAdy;
    }

    public void dfs(String nombreVert) {
        boolean[] visitados = new boolean[cantMaxVertices];
        int pos = obtenerPos(nombreVert);
        dfs(pos, visitados);
    }

    private void dfs(int pos, boolean[] visitados) {
        System.out.println(vertices[pos]);
        visitados[pos] = true;
        for (int i = 0; i < cantMaxVertices; i++) {
            if (this.matrizAdyacencia[pos][i].isExiste() && !visitados[i]) {
                dfs(i, visitados);
            }
        }
    }

    public void bfs(String vert) {
        int pos = obtenerPos(vert);
        boolean[] visitados = new boolean[cantMaxVertices];
        Cola<Tupla> cola = new Cola<>();
        visitados[pos] = true;
        cola.encolar(new Tupla(pos, 0));
        while (!cola.esVacia()) {
            Tupla tuplaDesencolada = cola.desencolar();
            System.out.println(vertices[tuplaDesencolada.pos]);
            for (int i = 0; i < cantMaxVertices; i++) {
                if (this.matrizAdyacencia[tuplaDesencolada.pos][i].isExiste() && !visitados[i]) {
                    cola.encolar(new Tupla(i, tuplaDesencolada.salto + 1));
                    visitados[i] = true;
                }
            }
        }
    }

    @Override
    public void dijkstra(String vert) {
        boolean[] visitados = new boolean[cantMaxVertices];
        int[] costos = new int[cantMaxVertices];
        Vertice[] vengo = new Vertice[cantMaxVertices];

        int pos = obtenerPos(vert);

        for (int i = 0; i < cantMaxVertices; i++) {
            costos[i] = Integer.MAX_VALUE; //Seria nuestro infinito
        }

        costos[pos] = 0;

        for (int v = 0; v < cantVertices; v++) {
            int posV = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos, visitados);

            visitados[posV] = true;

            for (int i = 0; i < cantMaxVertices; i++) {
                if (matrizAdyacencia[posV][i].isExiste() && !visitados[i]) {
                    int distanciaNueva = costos[posV] + matrizAdyacencia[posV][i].distancia;
                    if (costos[i] > distanciaNueva) {
                        costos[i] = distanciaNueva;
                        vengo[i] = vertices[posV];
                    }
                }
            }
        }

        String camino = "";
        int posDestino = obtenerPos("B");
        camino = vertices[posDestino] + " " + camino;

        int posDestinoAux = obtenerPos("B");
        Vertice vAnt = vengo[posDestinoAux];
        camino = vAnt + " " + camino;

        while(vAnt!=null){
            posDestinoAux = obtenerPos(vAnt.getNombre());
            vAnt = vengo[posDestinoAux];
            if(vAnt!=null){
                camino = vAnt + " " + camino;
            }
        }

        System.out.println("El camino del vertice " + vert + " al vertice B es: " + camino);
        System.out.println("El costo del camino entre A y B es: " + costos[posDestino] );
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorCosto(int[] costos, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE; // Infinito
        for (int i = 0; i < cantMaxVertices; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;

            }
        }
        return posMin;
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


    @Override
    public boolean esVacio() {
        return cantVertices == 0;
    }

    @Override
    public boolean estaLlena() {
        return cantVertices == cantMaxVertices;
    }

    private class Vertice {
        private String nombre;

        public Vertice(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertice vertice = (Vertice) o;
            return Objects.equals(nombre, vertice.nombre);
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    private class Arista {
        public boolean existe;
        public int costo;
        public int distancia;

        public Arista(int costo, int distancia) {
            this.costo = costo;
            this.distancia = distancia;
            this.existe = true;
        }

        public Arista() {
        }

        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }

        public int getCosto() {
            return costo;
        }

        public void setCosto(int costo) {
            this.costo = costo;
        }

        public int getDistancia() {
            return distancia;
        }

        public void setDistancia(int distancia) {
            this.distancia = distancia;
        }
    }

}
