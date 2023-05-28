package sistema;

import dominio.ABBPasajeros;
import modelo.Conexion;
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
        if(consulta == null)
            return Retorno.error1("Consulta es vacia");
        return pasajeros.filtrarPasajerosPor(consulta);
    }

    //4
    @Override
    public Retorno buscarPasajero(String identificador) {
        if(identificador == null)
            return Retorno.error1("Identificador no valido");
        Pasajero p = new Pasajero(identificador);
        if(!p.Validar())
            return Retorno.error1("Identificador no valido");
        return pasajeros.buscar(p);
    }

    //5
    @Override
    public Retorno listarPasajerosAscendente() {
        pasajeros.listarAscendente();
        return Retorno.ok();
    }

    //6
    @Override
    public Retorno listarPasajerosDescendente() {
        pasajeros.listarDescendente();
        return Retorno.ok();
    }

    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        if (nacionalidad == null)
            return Retorno.error1("La nacionalidad debe ser distinta de nula.");

        pasajeros.listarPorNacionalidad(nacionalidad);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        if(grafoEstaciones.estaLlena())
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
        if (costo <= 0 || tiempo <= 0 || kilometros <= 0)
            return Retorno.error1("El costo, tiempo y distancia deben ser mayores a 0");
        if(codigoEstacionOrigen == null || codigoEstacionDestino == null
                || codigoEstacionOrigen == "" || codigoEstacionDestino == "" || estadoDeLaConexion == null)
            return Retorno.error2("Ni las estaciones de origen y destino ni el estado de la conexion pueden ser vacios o nulos");

        Estacion origen = new Estacion(codigoEstacionOrigen);
        Estacion destino = new Estacion(codigoEstacionDestino);

        if (!origen.Validar() || !destino.Validar())
            return Retorno.error3("Los codigos origen y/o destino no son validos");

        if(!grafoEstaciones.existeEstacion(origen))
            return Retorno.error4("No existe la estacion de origen ingresada");

        if(!grafoEstaciones.existeEstacion(destino))
            return Retorno.error5("No existe la estacion de destino ingresada");

        Conexion nueva = new Conexion(origen, destino, identificadorConexion, costo, tiempo, kilometros, estadoDeLaConexion);

        if(grafoEstaciones.existeConexion(nueva))
            return Retorno.error6("Ya existe una conexion con el identificador ingresado");

        grafoEstaciones.agregarConexion(nueva);

        return Retorno.ok();
    }

    @Override
    public Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,
                                    int identificadorConexion, double costo, double tiempo,
                                    double kilometros, EstadoCamino estadoDeLaConexion) {

        if (costo <= 0 || tiempo <= 0 || kilometros <= 0 || identificadorConexion <= 0)
            return Retorno.error1("El costo, tiempo, distancia e identificador deben ser mayores a 0");

        if(codigoEstacionOrigen == null || codigoEstacionDestino == null
                || codigoEstacionOrigen == "" || codigoEstacionDestino == "" || estadoDeLaConexion == null)
            return Retorno.error2("Ni las estaciones de origen y destino ni el estado de la conexion pueden ser vacios o nulos");

        Estacion origen = new Estacion(codigoEstacionOrigen);
        Estacion destino = new Estacion(codigoEstacionDestino);

        if (!origen.Validar() || !destino.Validar())
            return Retorno.error3("Los codigos origen y/o destino no son validos");

        if(!grafoEstaciones.existeEstacion(origen))
            return Retorno.error4("No existe la estacion de origen ingresada");

        if(!grafoEstaciones.existeEstacion(destino))
            return Retorno.error5("No existe la estacion de destino ingresada");

        Conexion actualizada = new Conexion(origen, destino, identificadorConexion, costo, tiempo, kilometros, estadoDeLaConexion);

        if(!grafoEstaciones.existeConexion(actualizada))
            return Retorno.error6("No existe una conexion con el identificador ingresado");

        grafoEstaciones.actualizarConexion(actualizada);

        return Retorno.ok();
    }

    @Override
    public Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad) {
        if (cantidad < 0)
            return Retorno.error1("La cantidad de trasbordos debe ser mayor a 0.");

        if (codigo == null)
            return Retorno.error2("El codigo debe ser distinto de nulo.");

        Estacion nueva = new Estacion(codigo);

        if(!nueva.Validar())
            return Retorno.error3("El codigo de la estacion no es valido.");

        if(!grafoEstaciones.existeEstacion(nueva))
            return Retorno.error4("La estacion no esta ingresada en el sistema");

        grafoEstaciones.listarDestinosPorTrasbordos(nueva, cantidad);

        return Retorno.ok();
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
