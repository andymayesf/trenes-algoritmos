package modelo;

public class Estacion {
    private String codigo;
    private String nombre;
    static String regexCodigo = "[A-Z]{3}\\d{3}$";
    public Estacion(String c, String n){
        this.codigo = c;
        this.nombre = n;
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
}
