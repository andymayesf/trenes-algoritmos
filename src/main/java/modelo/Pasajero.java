package modelo;

import interfaz.Nacionalidad;

public class Pasajero {
    private String id;
    private String nombre;
    private int edad;

    static String regexId = "(^(FR|DE|UK|ES|OT)[1-9]\\.(\\d{3})\\.(\\d{3})#(\\d)$)|(^(FR|DE|UK|ES|OT)[1-9](\\d{2})\\.(\\d{3})#(\\d)$)";

    //region Constructor
    public Pasajero(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Pasajero(String id) {
        this.id = id;
    }

    public boolean Validar(){
        return idEsValido(this.getId());
    }

    public static boolean idEsValido(String identificador) {
        // TODO: Validar id con regex
        //  (^(FR|DE|UK|ES|OT)[1-9]\.(\d{3})\.(\d{3})#(\d)$)|(^(FR|DE|UK|ES|OT)[1-9](\d{2})\.(\d{3})#(\d)$)
        return identificador.matches(regexId);
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

    public int getIdNum() {
        String id = this.getId();
        int i = 0;
        String numero = "";
        while (id.charAt(i) != '#'){
            if(Character.isDigit(id.charAt(i))){
                numero += id.charAt(i);
            }
            i++;
        }
        return Integer.parseInt(numero);
    }

    public String getNacionalidadCod(){
        String id = this.getId();
        return id.substring(0,2);
    }

    public Nacionalidad getNacionalidad(){
        return Nacionalidad.fromCodigo(this.getNacionalidadCod());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return pasajero.getIdNum() == getIdNum();
    }

    @Override
    public String toString() {
        return getId()+";"+getNombre()+";"+getEdad()+getNacionalidadCod();
    }
}
