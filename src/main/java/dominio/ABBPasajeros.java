package dominio;

import interfaz.Consulta;
import interfaz.Nacionalidad;
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
            // TODO: if(nodo.compareTo())
            if(p.getIdNum() < nodo.getDato().getIdNum())
                return existe(p, nodo.getIzquierdo());
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
        if(p.getIdNum() < nodo.getDato().getIdNum())
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoArbol(p));
            } else {
                insertar(p, nodo.getIzquierdo());
            }
        if(p.getIdNum() > nodo.getDato().getIdNum()){
            if (nodo.getDerecho() == null) {
                nodo.setDerecho(new NodoArbol(p));
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

    public Retorno filtrarPasajerosPor(Consulta consulta) {
        String resString = filtrarPasajerosRec(this.raiz, consulta);
        return Retorno.ok(resString);
    }

    private String filtrarPasajerosRec(NodoArbol nodo, Consulta consulta){
        if(nodo == null){
            return "";
        }
        String pasajero = "";
        if(consultarPasajero(nodo.getDato(), consulta)) {
            pasajero += "|\n"+ nodo.getDato().toString();
        }
        return filtrarPasajerosRec(nodo.getIzquierdo(), consulta) + pasajero + filtrarPasajerosRec(nodo.getDerecho(), consulta);

    };

    private boolean consultarPasajero(Pasajero p, Consulta c){
        return consultarPasajeroRec(p, c.getRaiz());
    };
    private boolean consultarPasajeroRec(Pasajero pasajero, Consulta.NodoConsulta nodoConsulta) {
        Consulta.TipoNodoConsulta tnc = nodoConsulta.getTipoNodoConsulta();

        switch(tnc) {
            case And:
                return consultarPasajeroRec(pasajero, nodoConsulta.getIzq())
                        && consultarPasajeroRec(pasajero, nodoConsulta.getDer());
            case Or:
                return consultarPasajeroRec(pasajero, nodoConsulta.getIzq()) || consultarPasajeroRec(pasajero, nodoConsulta.getDer());
            case EdadMayor:
                return pasajero.getEdad() > nodoConsulta.getValorInt();

            case NombreIgual:
                return pasajero.getNombre().equals(nodoConsulta.getValorString());

            case Nacionalidad:
                return pasajero.getNacionalidad().equals(nodoConsulta.getValorNacionalidad());
            default:
                break;
        }
        return false;
    }

    public void listarPorNacionalidad(Nacionalidad nacionalidad) {
        System.out.println(listarPorNacionalidad(this.raiz, nacionalidad));
    }


    private String listarPorNacionalidad(NodoArbol nodo, Nacionalidad nacionalidad) {
        if (nodo != null) {
            String lista = "";

            if (nodo.getDato().getNacionalidad().equals(nacionalidad))
                lista = nodo.getDato().toString();

            return listarPorNacionalidad(nodo.getIzquierdo(), nacionalidad) + "|\n" + lista + "|\n" + listarPorNacionalidad(nodo.getDerecho(), nacionalidad);
        }

        return "";

    }
    //endregion

}
