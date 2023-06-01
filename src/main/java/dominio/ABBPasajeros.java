package dominio;

import interfaz.Consulta;
import interfaz.Nacionalidad;
import interfaz.Retorno;
import modelo.Pasajero;

public class ABBPasajeros {
    private NodoArbol raiz;


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
    public String listarDescendente() {
        String ret = listarDescendente(this.raiz);
        if(ret.length() > 0) return ret.substring(0, ret.length()-1);
        return ret;

    }

    private String listarDescendente(NodoArbol nodo) {
        if (nodo != null)
            return listarDescendente(nodo.getDerecho()) + nodo.getDato().toString() +  "|" + listarDescendente(nodo.getIzquierdo());
        else
            return "";
    }
    public String listarAscendente() {
        String ret = listarAscendente(this.raiz);
        if(ret.length() > 0) return ret.substring(0, ret.length()-1);
        return ret;
    }

    private String listarAscendente(NodoArbol nodo) {
        if (nodo != null)
            return listarAscendente(nodo.getIzquierdo()) + nodo.getDato().toString() + "|" + listarAscendente(nodo.getDerecho());
        else
            return "";
    }


    public Retorno buscar(Pasajero p){
        return buscar(p, this.raiz, 0);
    }

    private Retorno buscar(Pasajero p, NodoArbol nodo, int cuenta){
        if(nodo == null)
            return Retorno.error2("No existe pasajero con este identificador");
        if(nodo.getDato().equals(p))
            return Retorno.ok(cuenta, nodo.getDato().toString());
        else {
            if(p.getIdNum() < nodo.getDato().getIdNum())
                return buscar(p, nodo.getIzquierdo(), cuenta + 1);
            return buscar(p, nodo.getDerecho(), cuenta + 1);
        }
    }

    public Retorno filtrarPasajerosPor(Consulta consulta) {
        String resString = filtrarPasajerosRec(this.raiz, consulta);
        if(resString.length() > 0) return Retorno.ok(resString.substring(0, resString.length()-1));
        return Retorno.ok(resString);
    }

    private String filtrarPasajerosRec(NodoArbol nodo, Consulta consulta){
        if(nodo == null){
            return "";
        }
        String pasajero = "";
        if(consultarPasajero(nodo.getDato(), consulta)) {

                pasajero += nodo.getDato().getId() + "|";


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
}
