package dominio.grafo;
import dominio.lista.ListaInt;

public interface Grafo {

    void agregarVertice(String nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    void agregarArista(String origen, String destino, int costo, int distancia);
    void eliminarVertice(String v);
    void eliminarArista(int origen, int destino);
    boolean existeVertice(String v);
    boolean sonAdyacentes(String a, String b);
    ListaInt verticesAdyacentes(String v);
    boolean esVacio();
    boolean estaLlena();
    void dijkstra(String vert);

}
