package dominio;

public class Lista<T> implements ILista<T> {
    protected NodoLista<T> inicio;
    protected int largo;

    public Lista() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    @Override
    public void borrar(T dato) {

    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    @Override
    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public boolean esLlena() {
        return false;
    }

    @Override
    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null){
                System.out.print(aux.getDato().toString() + " -> ");
            }else{
                System.out.print(aux.getDato().toString());
            }
            aux = aux.getSig();
        }
        System.out.println();
    }

    public void imprimirDatosV2(NodoLista<T> nodo ) {
        if (nodo!=null){
            System.out.println(nodo.getDato().toString());
            imprimirDatosV2(nodo.getSig());
        }
    }


    class NodoLista<T>{
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }


}
