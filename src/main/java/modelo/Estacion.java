package modelo;

import java.util.Objects;

public class Estacion implements Comparable {
    private String codigo;
    private String nombre;
    static String regexCodigo = "[A-Z]{3}\\d{3}$";
    public Estacion(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Estacion(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public boolean Validar(){
        return validarCodigo();
    }

    private boolean validarCodigo(){
        return getCodigo().matches(regexCodigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacion estacion = (Estacion) o;
        return Objects.equals(codigo, estacion.codigo);
    }

    @Override
    public int compareTo(Object o) {
            return -1;
    }

    @Override
    public String toString() {
        return getCodigo() + ";" + getNombre();
    }
}
