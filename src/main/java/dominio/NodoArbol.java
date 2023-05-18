package dominio;

public class NodoArbol<T>{
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoArbol<T> nodoArbol = (NodoArbol<T>) o;
        return dato.equals(nodoArbol.dato);
    }


    //@Override
    //public int compareTo(NodoArbol<T> o) {
      //  return o.getDato().compareTo(this.getDato());
    //}

}
