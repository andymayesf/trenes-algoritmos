package dominio;

import modelo.Pasajero;

public class NodoArbol{
    private Pasajero dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    //region Constructores

    public NodoArbol(Pasajero dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbol(Pasajero dato, NodoArbol izquierdo, NodoArbol derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    //endregion

    //region Getters y setters
    public Pasajero getDato() { return dato; }
    public void setDato(Pasajero dato) { this.dato = dato; }

    public NodoArbol getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }

    public NodoArbol getDerecho() { return derecho; }
    public void setDerecho(NodoArbol derecho) { this.derecho = derecho; }
    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoArbol nodoArbol = (NodoArbol) o;
        return dato.equals(nodoArbol.dato);
    }


}
