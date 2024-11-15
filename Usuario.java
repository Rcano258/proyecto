public class Usuario {
    private String nombre;
    private int edad;
    private String email;
    private int puntos;
    private int nivelActual;

    public Usuario(String nombre, int edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.puntos = 0;
        this.nivelActual = 1; // Nivel inicial
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void restarPuntos(int puntos) {
        this.puntos -= puntos;
    }

    public void subirNivel() {
        this.nivelActual++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getNivelActual() {
        return nivelActual;
    }
}
