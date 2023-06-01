package dominio;

public interface ILista<T> {
    void insertar(T dato);
    void insertarOrdenado(T dato);
    boolean existe(T dato);
    T recuperar(T dato);
    boolean esVacia();
    String imprimirDatos();

}
