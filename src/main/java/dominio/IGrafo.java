package dominio;

import interfaz.Retorno;
import modelo.Conexion;
import modelo.Estacion;

public interface IGrafo {

    void agregarEstacion(Estacion nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    void agregarConexion(Conexion nuevaConexion);
    boolean existeEstacion(Estacion v);
    boolean estaLlena();

    boolean existeConexion(Conexion nueva);

    void actualizarConexion(Conexion actualizada);

    Retorno listarDestinosPorTrasbordos(Estacion nueva, int cantidad);

    Retorno caminoMinKm(Estacion origen, Estacion destino);
    Retorno caminoMinEuros(Estacion origen, Estacion destino);

    Retorno dijkstra(Estacion origen, Estacion destino, String tipo);
}
