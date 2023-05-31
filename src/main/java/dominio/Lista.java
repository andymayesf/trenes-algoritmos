package dominio;

import modelo.Conexion;

public class Lista<T extends Comparable> implements ILista<T> {
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
    public void insertarOrdenado(T dato){
        if (esVacia() || dato.compareTo(inicio.getDato()) < 0){
            insertar(dato);
        } else {
            NodoLista<T> aux = inicio;
            while (aux.getSig() != null && dato.compareTo(aux.getSig().getDato()) > 0) {
                aux = aux.getSig();
            }
            aux.setSig(new NodoLista<T>(dato,aux.getSig()));
            largo++;
        }
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
    public String imprimirDatos() {
        String retorno = "";
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null){
                retorno +=aux.getDato().toString() + "|";
            }else{
                retorno += aux.getDato().toString();
            }
            aux = aux.getSig();
        }
        return retorno;
    }

    //TODO: herencia
    @Override
    public double getMenorDato(String tipo) {
        Conexion c = new Conexion();
        if(this.inicio.getDato().getClass() == c.getClass()) {
            double ret = Integer.MAX_VALUE;
            NodoLista aux = inicio;
            while (aux != null) {
                Conexion auxConexion = (Conexion) aux.getDato();
                if(tipo == "distancia"){
                    if(auxConexion.getDistancia() < ret) ret = auxConexion.getDistancia();
                }
                if(tipo == "costo"){
                    if(auxConexion.getCosto() < ret) ret = auxConexion.getCosto();
                }
                aux = aux.getSig();
            }
            return ret;
        }
        return 0;
    }


    public void imprimirDatosV2(NodoLista<T> nodo ) {
        if (nodo!=null){
            System.out.println(nodo.getDato().toString());
            imprimirDatosV2(nodo.getSig());
        }
    }


    public class NodoLista<T extends Comparable>{
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
