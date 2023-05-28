package dominio;

import interfaz.Retorno;
import modelo.Pasajero;

public class ABBPasajeros {
    private NodoArbol raiz;

    //region Constructor


    public ABBPasajeros() {

    }

    public ABBPasajeros(NodoArbol raiz) {
        this.raiz = raiz;
    }

    private boolean existe(Pasajero p, NodoArbol nodo) {
        if(nodo == null)
            return false;
        if(nodo.getDato().equals(p))
            return true;
        else {
            // compara primero por nacionalidad
            // si tiene la misma nacionalidad compara el numero
            if(p.getNacionalidad().equals(nodo.getDato().getNacionalidad())) {
                if(p.getIdNum() < nodo.getDato().getIdNum())
                    return existe(p, nodo.getIzquierdo());
                return existe(p, nodo.getDerecho());
            } else if(p.getNacionalidad().getIndice() < nodo.getDato().getNacionalidad().getIndice()){
                return existe(p, nodo.getIzquierdo());
            }
            return existe(p, nodo.getDerecho());
        }
    }

    public boolean existe(Pasajero p) {

        return existe(p, this.raiz);
    }

    public void insertar(Pasajero p) {
        if (this.raiz == null) {
            this.raiz = new NodoArbol(p);
        }
        insertar(p, this.raiz);
    }

    private void insertar(Pasajero p, NodoArbol nodo){
        if(p.getNacionalidad().equals(nodo.getDato().getNacionalidad())) {
            if (p.getIdNum() < nodo.getDato().getIdNum())
                if (nodo.getIzquierdo() == null) {
                    nodo.setIzquierdo(new NodoArbol(p));
                    return;
                } else {
                    insertar(p, nodo.getIzquierdo());
                }
            if(p.getIdNum() > nodo.getDato().getIdNum()){
                if (nodo.getDerecho() == null) {
                    nodo.setDerecho(new NodoArbol(p));
                    return;
                } else {
                    insertar(p, nodo.getDerecho());
                }
            }
        } else if(p.getNacionalidad().getIndice() < nodo.getDato().getNacionalidad().getIndice()){
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoArbol(p));
                return;
            } else {
                insertar(p, nodo.getIzquierdo());
            }
        } else {
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoArbol(p));
                return;
            } else {
                insertar(p, nodo.getDerecho());
            }
        }
    }
    public void listarDescendente() {
        System.out.println(listarDescendente(this.raiz));
    }

    private String listarDescendente(NodoArbol nodo) {
        if (nodo != null)
            return listarDescendente(nodo.getDerecho()) + "|\n" + nodo.getDato().toString() +  "|\n" + listarDescendente(nodo.getIzquierdo());
        else
            return "";
    }
    public void listarAscendente() {
        System.out.println(listarAscendente(this.raiz));
    }

    private String listarAscendente(NodoArbol nodo) {
        if (nodo != null)
            return listarAscendente(nodo.getIzquierdo()) + "|\n" + nodo.getDato().toString() + "|\n" + listarAscendente(nodo.getDerecho());
        else
            return "";
    }


    public Retorno buscar(Pasajero p){
        return buscar(p, this.raiz, 1);
    }

    private Retorno buscar(Pasajero p, NodoArbol nodo, int cuenta){
        if(nodo == null)
            return Retorno.error2("No existe pasajero con este identificador");
        if(nodo.getDato().equals(p))
            return Retorno.ok(cuenta, p.toString());
        else {
            if(p.getIdNum() < nodo.getDato().getIdNum())
                return buscar(p, nodo.getIzquierdo(), cuenta + 1);
            return buscar(p, nodo.getDerecho(), cuenta + 1);
        }
    }
    //endregion

}
