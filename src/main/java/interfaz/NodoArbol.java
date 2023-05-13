package interfaz;

public class NodoArbol<T> {
    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;

    //region Constructores

    public NodoArbol(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbol(T dato, NodoArbol izquierdo, NodoArbol derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    //endregion

    //region Getters y setters
    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public NodoArbol<T> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoArbol<T> izquierdo) { this.izquierdo = izquierdo; }

    public NodoArbol<T> getDerecho() { return derecho; }
    public void setDerecho(NodoArbol<T> derecho) { this.derecho = derecho; }
    //endregion

}
