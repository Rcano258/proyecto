import java.util.HashSet;
import java.util.Set;

public class ControladorReciclaje {
    private Usuario usuario;
    private int objetosReciclados = 0;

    // Conjuntos para registrar los tipos y zonas recicladas
    private Set<String> tiposReciclados = new HashSet<>();
    private Set<String> zonasRecicladas = new HashSet<>();

    // Conjuntos de requisitos para completar desafíos
    private final Set<String> tiposRequeridos = Set.of("metal", "vidrio", "orgánico", "plástico", "electrónico");
    private final Set<String> zonasRequeridas = Set.of("zona 1", "zona 2", "zona 3", "zona 4");

    // Bandera para evitar recompensas duplicadas
    private boolean recompensaTiposOtorgada = false;
    private boolean recompensaZonasOtorgada = false;
    private boolean recompensa100ObjetosOtorgada = false;
    private boolean recompensa1000ObjetosOtorgada = false;

    public ControladorReciclaje(Usuario usuario) {
        this.usuario = usuario;
    }

    public String registrarReciclaje(String tipo, String zona) {
        objetosReciclados++;
        usuario.agregarPuntos(10);

        StringBuilder mensajes = new StringBuilder("Has ganado 10 puntos por reciclar un objeto.\n");

        // Registrar tipo y zona reciclada
        tiposReciclados.add(tipo.toLowerCase());
        zonasRecicladas.add(zona.toLowerCase());

        // Verificar y otorgar recompensa por tipos reciclados
        if (!recompensaTiposOtorgada && tiposReciclados.containsAll(tiposRequeridos)) {
            usuario.agregarPuntos(100);
            mensajes.append("¡Felicidades! Has reciclado todos los tipos de materiales. Recompensa: 100 puntos.\n");
            recompensaTiposOtorgada = true; // Marcar como completada
        }

        // Verificar y otorgar recompensa por zonas recicladas
        if (!recompensaZonasOtorgada && zonasRecicladas.containsAll(zonasRequeridas)) {
            usuario.agregarPuntos(100);
            mensajes.append("¡Felicidades! Has reciclado en todas las zonas. Recompensa: 100 puntos.\n");
            recompensaZonasOtorgada = true; // Marcar como completada
        }

        // Verificar hitos de objetos reciclados para 100 y 1000 objetos
        if (!recompensa100ObjetosOtorgada && objetosReciclados >= 100) {
            usuario.agregarPuntos(100);
            mensajes.append("¡Felicitaciones! Has reciclado 100 objetos. Recompensa: 100 puntos.\n");
            recompensa100ObjetosOtorgada = true;
        }

        if (!recompensa1000ObjetosOtorgada && objetosReciclados >= 1000) {
            usuario.agregarPuntos(100);
            mensajes.append("¡Increíble! Has reciclado 1000 objetos. Recompensa: 100 puntos.\n");
            recompensa1000ObjetosOtorgada = true;
        }

        // Verificar subida de nivel
        while (usuario.getPuntos() >= 100) {
            usuario.subirNivel();
            usuario.restarPuntos(100); // Reiniciar puntos para el nuevo nivel
            mensajes.append("¡Has subido de nivel! Ahora estás en el nivel ").append(usuario.getNivelActual()).append(".\n");
        }

        return mensajes.toString();
    }
}
