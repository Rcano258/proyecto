public class ControladorJuego {
    private Usuario usuario;

    public ControladorJuego(Usuario usuario) {
        this.usuario = usuario;
    }

    // Método para reciclar un objeto y asignar puntos
    public void reciclarObjeto(Residuos residuo) {
        usuario.agregarPuntos(10);  // Asignar 10 puntos por objeto reciclado

        // Mostrar mensajes al usuario
        System.out.println("¡Felicidades! Has reciclado: " + residuo.obtenerNombre());
        System.out.println("Clasificación: " + residuo.obtenerTipo());
        System.out.println("Has ganado 10 puntos.");
        System.out.println("Puntuación total: " + usuario.obtenerPuntuacion());

        // Verificar si el usuario sube de nivel
        if (usuario.obtenerPuntuacion() / 100 >= usuario.getNivelActual()) {
            usuario.subirNivel();
            System.out.println("¡Felicidades! Has subido al nivel " + usuario.getNivelActual() + "!");
        }
    }
}
