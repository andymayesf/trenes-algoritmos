package dominio;

import modelo.Estacion;

public interface IGrafo {

    void agregarEstacion(Estacion nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    void agregarArista(String origen, String destino, int costo, int distancia);
    void eliminarVertice(String v);
    void eliminarArista(int origen, int destino);
    boolean existeEstacion(Estacion v);
    boolean sonAdyacentes(String a, String b);
    ILista verticesAdyacentes(String v);
    boolean esVacio();
    boolean estaLlena();
    void dijkstra(String vert);

}
