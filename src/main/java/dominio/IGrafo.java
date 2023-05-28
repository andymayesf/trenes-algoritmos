package dominio;

import interfaz.Retorno;
import modelo.Conexion;
import modelo.Estacion;

public interface IGrafo {

    void agregarEstacion(Estacion nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    void agregarConexion(Conexion nuevaConexion);
    void eliminarVertice(String v);
    void eliminarArista(int origen, int destino);
    boolean existeEstacion(Estacion v);
    boolean sonAdyacentes(Estacion origen, Estacion destino);
    ILista verticesAdyacentes(String v);
    boolean esVacio();
    boolean estaLlena();
    void dijkstra(String vert);

    boolean existeConexion(Conexion nueva);

    void actualizarConexion(Conexion actualizada);

    Retorno listarDestinosPorTrasbordos(Estacion nueva, int cantidad);
}
