package interfaz;

public class ArbolBinarioBusqueda<T> {
    private NodoArbol<T> raiz;

    //region Constructor
    public ArbolBinarioBusqueda(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    public boolean existe(String identificador) {
        // TODO: Buscar nodo con identificador
        return false;
    }

    public void insertar(T nuevo) {
        //TODO: insertar nuevos nodos
    }
    //endregion
}
