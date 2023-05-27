package dominio.lista;

public class ListaInt {

    protected NodoEnteros inicio;
    protected int largo;

    public ListaInt() {
        this.inicio = null;
        this.largo = 0;
    }


    public void insertar(int dato) {
        inicio = new NodoEnteros(dato, inicio);
        largo++;
    }

    public void imprimirDatos() {
        NodoEnteros aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null){
                System.out.print(aux.getDato() + " -> ");
            }else{
                System.out.print(aux.getDato());
            }
            aux = aux.getSig();
        }
        System.out.println();
    }


    private class NodoEnteros {

        private int dato;
        private NodoEnteros sig;

        public NodoEnteros(int dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoEnteros(int dato, NodoEnteros sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public int getDato() {
            return dato;
        }

        public void setDato(int dato) {
            this.dato = dato;
        }

        public NodoEnteros getSig() {
            return sig;
        }

        public void setSig(NodoEnteros sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato + "";
        }
    }
}
