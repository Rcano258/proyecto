public class Usuario {
    private String nombre;
    private int edad;
    private String email;
    private int puntuacionTotal;
    private int nivelActual;

    public Usuario(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.puntuacionTotal = 0;
        this.nivelActual = 1;
    }

    public void agregarPuntos(int puntos) {
        puntuacionTotal += puntos;
    }

    public int obtenerPuntuacion() {
        return puntuacionTotal;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void subirNivel() {
        nivelActual++;
    }

    public String getNombre() {
        return nombre;
    }
}
