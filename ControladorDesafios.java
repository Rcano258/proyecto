import java.util.HashSet;
import java.util.Set;

public class ControladorDesafios {
    private Usuario usuario;
    private int objetosReciclados = 0;

    // Conjuntos para llevar el registro de tipos y zonas recicladas
    private Set<String> tiposReciclados = new HashSet<>();
    private Set<String> zonasRecicladas = new HashSet<>();

    // Conjuntos con los tipos y las zonas requeridas para la recompensa
    private final Set<String> tiposRequeridos = Set.of("metal", "vidrio", "orgánico", "plástico", "electrónico");
    private final Set<String> zonasRequeridas = Set.of("zona 1", "zona 2", "zona 3", "zona 4");

    public ControladorDesafios(Usuario usuario) {
        this.usuario = usuario;
    }

    // Registrar un objeto reciclado por su tipo y zona
    public void registrarObjetoReciclado(String tipo, String zona) {
        objetosReciclados++;

        // Registrar el tipo y la zona en minúsculas para evitar inconsistencias
        tiposReciclados.add(tipo.toLowerCase());
        zonasRecicladas.add(zona.toLowerCase());

        // Verificar si se han reciclado todos los tipos y otorgar recompensa
        if (recicloTodosLosTipos()) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicidades! Has reciclado todos los tipos de materiales. Recompensa: 100 puntos");
            tiposReciclados.clear(); // Limpiar el registro para evitar múltiples recompensas
        }

        // Verificar si se ha reciclado en todas las zonas y otorgar recompensa
        if (recicloEnTodasLasZonas()) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicidades! Has reciclado en todas las zonas. Recompensa: 100 puntos");
            zonasRecicladas.clear(); // Limpiar el registro para evitar múltiples recompensas
        }

        // Verificar hitos de objetos reciclados
        if (objetosReciclados == 100) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicitaciones! Has reciclado 100 objetos. Recompensa: 100 puntos");
        }

        if (objetosReciclados == 1000) {
            usuario.agregarPuntos(100);
            System.out.println("¡Increíble! Has reciclado 1000 objetos. Recompensa: 100 puntos");
        }
    }

    // Verificar si se han reciclado al menos una vez todos los tipos requeridos
    private boolean recicloTodosLosTipos() {
        return tiposReciclados.containsAll(tiposRequeridos);
    }

    // Verificar si se ha reciclado en todas las zonas requeridas
    private boolean recicloEnTodasLasZonas() {
        return zonasRecicladas.containsAll(zonasRequeridas);
    }
}
