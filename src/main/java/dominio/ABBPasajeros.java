package dominio;

import modelo.Pasajero;

public class ABBPasajeros {
    private NodoArbol raiz;

    //region Constructor


    public ABBPasajeros() {

    }

    public ABBPasajeros(NodoArbol raiz) {
        this.raiz = raiz;
    }

    private boolean existe(String identificador, NodoArbol nodo) {
        if(nodo == null)
            return false;

        if(nodo.getDato().equals(new Pasajero(identificador)))
            return true;
        else {
            // TODO: Comparar objetos
            // if(nodo.compareTo())
            return existe(identificador, nodo.getIzquierdo()) || existe(identificador, nodo.getDerecho());
        }
    }

    public boolean existe(String identificador) {
        return existe(identificador, this.raiz);
    }

    public void insertar(Pasajero nuevo) {
        //TODO: insertar nuevos nodos
        if (raiz == null) {
            this.raiz = new NodoArbol(nuevo);
        }

    }
    //endregion
}
