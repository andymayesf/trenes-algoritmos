package dominio;

public class ArbolBinario implements IArbolBinario {
    private NodoArbol raiz;

    //region Constructor
    public ArbolBinario(NodoArbol raiz) {
        this.raiz = raiz;
    }
    //endregion

    public NodoArbol getRaiz() {
        return this.raiz;
    }

    @Override
    public int cantNodos() {
        return cantNodos(this.raiz);
    }
    private int cantNodos(NodoArbol nodo) {
        if (nodo == null)
            return 0;
        else
            return 1 + cantNodos(nodo.getIzquierdo()) + cantNodos(nodo.getDerecho());
    }


    @Override
    public int cantHojas() {
        return cantHojas(this.raiz);
    }
    private int cantHojas(NodoArbol nodo) {
        if (nodo == null)
            return 0;
        else if (nodo.getIzquierdo() == null && nodo.getDerecho() == null)
            return 1;
        else
            return cantHojas(nodo.getIzquierdo()) + cantHojas(nodo.getDerecho());

    }


//    @Override
//    public boolean todosPares() {
//        return todosPares(raiz);
//    }
//    private boolean todosPares(NodoArbol nodo) {
//        if (nodo == null)
//            return true;
//        else
//            return nodo.getDato() % 2 == 0
//                    && todosPares(nodo.getIzquierdo())
//                    && todosPares(nodo.getDerecho());
//    }



}
