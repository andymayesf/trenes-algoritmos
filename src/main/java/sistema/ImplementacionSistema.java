package sistema;

import dominio.ABBPasajeros;
import dominio.GrafoEstaciones;
import interfaz.*;
import modelo.Estacion;
import modelo.Pasajero;

public class ImplementacionSistema implements Sistema {
    private ABBPasajeros pasajeros;
    private GrafoEstaciones grafoEstaciones;

    public ImplementacionSistema(){}
    //1
    @Override
    public Retorno inicializarSistema(int maxEsta) {
        if (maxEsta <= 5) {
            return Retorno.error1("La cantidad maxima de estaciones debe ser mayor a 5.");
        }
        pasajeros = new ABBPasajeros();
        grafoEstaciones = new GrafoEstaciones(maxEsta);

        return Retorno.ok();
    }

    //2
    @Override
    public Retorno registrarPasajero(String identificador, String nombre, int edad) {
        if (nombre == null || identificador == null || identificador.trim() == "" || nombre == "")
            return Retorno.error1("Debe ingresar un identificador, nombre y edad validos.");

        Pasajero nuevoPas = new Pasajero(identificador, nombre, edad);

        if (!nuevoPas.Validar())
            return Retorno.error2("El identificador ingresado no es valido.\n"
                    + "El formato puede tomar los siquientes formatos: \n"
                    + "(CodigoNacionalidad)P.NNN.NNN#N\n"
                    + "(CodigoNacionalidad)PNN.NNN#N");

        if (pasajeros.existe(nuevoPas))
            return Retorno.error3("Ya existe un pasajero con el mismo identificador al ingresado.");

        pasajeros.insertar(nuevoPas);

        return Retorno.ok();
    }

    //3
    @Override
    public Retorno filtrarPasajeros(Consulta consulta) {
        return Retorno.noImplementada();
    }

    //3
    @Override
    public Retorno buscarPasajero(String identificador) {
        if(identificador == null)
            return Retorno.error1("Identificador no valido");
        Pasajero p = new Pasajero(identificador);
        if(!p.Validar())
            return Retorno.error1("Identificador no valido");
        return pasajeros.buscar(p);
    }

    //4
    @Override
    public Retorno listarPasajerosAscendente() {
        pasajeros.listarAscendente();
        return Retorno.ok();
    }

    //5
    @Override
    public Retorno listarPasajerosDescendente() {
        pasajeros.listarDescendente();
        return Retorno.ok();
    }

    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        if(grafoEstaciones.estaLleno())
            return Retorno.error1("Ya hay registadas el maximo de estaciones");
        if(codigo == "" || codigo == null  || nombre == "" || nombre == null)
            return Retorno.error2("Datos vacios o nulos");
        Estacion nuevaEstacion = new Estacion(codigo, nombre);
        if(!nuevaEstacion.Validar())
            return Retorno.error3("Codigo no valido");

        if(grafoEstaciones.existeEstacion(nuevaEstacion))
            return Retorno.error4("Estacion ya existe en el sistema");
        grafoEstaciones.agregarEstacion(nuevaEstacion);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,
                                     int identificadorConexion, double costo, double tiempo, double kilometros,
                                     EstadoCamino estadoDeLaConexion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,
                                    int identificadorConexion, double costo, double tiempo,
                                    double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return null;
    }

}
