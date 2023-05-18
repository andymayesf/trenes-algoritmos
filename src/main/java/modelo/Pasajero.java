package modelo;

import java.util.Objects;

public class Pasajero {
    private String id;
    private String nombre;
    private int edad;

    //region Constructor
    public Pasajero(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Pasajero(String id) {
        this.id = id;
    }

    public static boolean idEsValido(String identificador) {
        // TODO: Validar id con regex
        //  (^(FR|DE|UK|ES|OT)[1-9]\.(\d{3})\.(\d{3})#(\d)$)|(^(FR|DE|UK|ES|OT)[1-9](\d{2})\.(\d{3})#(\d)$)
        return true;
    }
    //endregion

    //region Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    //endregion

    public int extraerCIDeIdentificador(String id) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return extraerCIDeIdentificador(pasajero.getId()) == extraerCIDeIdentificador(this.getId());
    }


}
