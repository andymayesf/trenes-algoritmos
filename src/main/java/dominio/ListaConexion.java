package dominio;

import interfaz.EstadoCamino;
import modelo.Conexion;

public class ListaConexion extends Lista<Conexion> {
    public double getMenorDato(String tipo) {
        double ret = Integer.MAX_VALUE;
        NodoLista aux = inicio;
        while (aux != null) {
            Conexion auxConexion = (Conexion) aux.getDato();
            if (auxConexion.getEstado() != EstadoCamino.MALO) {
                if (tipo == "distancia") {
                    if (auxConexion.getDistancia() < ret) ret = auxConexion.getDistancia();
                }
                if (tipo == "costo") {
                    if (auxConexion.getCosto() < ret) ret = auxConexion.getCosto();
                }
            }
            aux = aux.getSig();
        }
        return ret;
    }
}
