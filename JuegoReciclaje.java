import java.util.ArrayList;

public class JuegoReciclaje {
    private ArrayList<Residuos> listaResiduos;
    private int puntuacion;

    // Constructor
    public JuegoReciclaje() {
        listaResiduos = new ArrayList<>();
        puntuacion = 0;
    }

    // Método para iniciar el juego y cargar residuos de ejemplo
    public void iniciarJuego() {
        listaResiduos.add(new Residuos("Orgánico", "Cáscara de plátano", "Residuo biodegradable."));
        listaResiduos.add(new Residuos("Vidrio", "Botella", "Recipiente de vidrio transparente."));
        listaResiduos.add(new Residuos("Plástico", "Bolsa", "Plástico de un solo uso."));
    }

    // Validar si la clasificación realizada por el usuario es correcta
    public boolean validarClasificacion(String tipoUsuario, Residuos residuo) {
        return tipoUsuario.equalsIgnoreCase(residuo.obtenerTipo());
    }

    // Calcular la puntuación según la clasificación
    public int calcularPuntuacion(boolean esCorrecto) {
        if (esCorrecto) {
            puntuacion += 10;  // Gana 10 puntos
        } else {
            puntuacion -= 5;   // Pierde 5 puntos
        }
        return puntuacion;
    }

    // Obtener la puntuación actual
    public int obtenerPuntuacion() {
        return puntuacion;
    }

    // Obtener la lista de residuos (por si se necesita en la vista)
    public ArrayList<Residuos> getListaResiduos() {
        return listaResiduos;
    }
}
