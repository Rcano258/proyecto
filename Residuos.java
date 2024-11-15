public class Residuos {
    private String tipo;
    private String nombre;
    private String descripcion;

    public Residuos(String tipo, String nombre, String descripcion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String obtenerTipo() {
        return tipo;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }
}
