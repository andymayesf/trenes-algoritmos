package dominio;

public interface ILista<T> {
    void insertar(T dato);
    void insertarOrdenado(T dato);
    T borrar();
    int largo();
    boolean existe(T dato);
    T recuperar(T dato);
    boolean esVacia();
    boolean esLlena();
    String imprimirDatos();

}
