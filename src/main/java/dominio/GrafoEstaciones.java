package dominio;

import modelo.Estacion;

import java.util.ArrayList;

public class GrafoEstaciones {
    private Estacion[] estaciones;
    private int maxEstaciones;
    public GrafoEstaciones(int maxEstaciones){
        this.maxEstaciones = maxEstaciones;
        //TODO: inicializar matriz conexiones
    }

    public void agregarEstacion(Estacion e){
        //TODO: agregarEstacion
    }

    public boolean existeEstacion(Estacion e){
        //TODO: existe estacion
        return false;
    }

    public boolean estaLleno(){
        return estaciones.length == maxEstaciones;
    }
}
