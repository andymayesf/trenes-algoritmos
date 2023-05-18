package sistema;

import dominio.ABBPasajeros;
import interfaz.*;
import modelo.Pasajero;

public class ImplementacionSistema implements Sistema {
    private ABBPasajeros pasajeros;
    @Override
    public Retorno inicializarSistema(int maxEstaciones) {
        if (maxEstaciones <= 5) {
            return Retorno.error1("La cantidad maxima de estaciones debe ser mayor a 5.");
        }
        pasajeros = new ABBPasajeros();

        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String identificador, String nombre, int edad) {
        if (nombre == null || identificador == null || identificador.trim() == "" || nombre == "")
            return Retorno.error1("Debe ingresar un identificador, nombre y edad validos.");

        if (!Pasajero.idEsValido(identificador))
            return Retorno.error2("El identificador ingresado no es valido.\n"
                    + "El formato puede tomar los siquientes formatos: \n"
                    + "(CodigoNacionalidad)P.NNN.NNN#N\n"
                    + "(CodigoNacionalidad)PNN.NNN#N");

        if (pasajeros.existe(identificador))
            return Retorno.error3("Ya existe un pasajero con el mismo identificador al ingresado.");

        Pasajero nuevoPas = new Pasajero("FR1.234.567#8", "Karim Benzema", 35);

        pasajeros.insertar(nuevoPas);

        return Retorno.ok();
    }

    @Override
    public Retorno filtrarPasajeros(Consulta consulta) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarPasajero(String identificador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        return Retorno.noImplementada();
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
