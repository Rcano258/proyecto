import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ControladorReciclaje {
    private Usuario usuario;
    private int objetosReciclados = 0;

    // Conjuntos para llevar el registro de tipos y zonas recicladas
    private Set<String> tiposReciclados = new HashSet<>();
    private Set<String> zonasRecicladas = new HashSet<>();

    // Conjuntos de tipos y zonas requeridos para las recompensas
    private final Set<String> tiposRequeridos = Set.of("metal", "vidrio", "orgánico", "plástico", "electrónico");
    private final Set<String> zonasRequeridas = Set.of("zona 1", "zona 2", "zona 3", "zona 4");

    public ControladorReciclaje(Usuario usuario) {
        this.usuario = usuario;
    }

    // Registrar objeto reciclado y verificar recompensas
    public void registrarReciclaje(String tipo, String zona) {
        objetosReciclados++;
        
        // Otorgar 10 puntos por cada objeto reciclado
        usuario.agregarPuntos(10);
        System.out.println("Has ganado 10 puntos por reciclar un objeto.");

        // Verificar y otorgar recompensas por reciclar todos los tipos de materiales
        tiposReciclados.add(tipo.toLowerCase());
        if (tiposReciclados.containsAll(tiposRequeridos)) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicidades! Has reciclado todos los tipos de materiales. Recompensa: 100 puntos");
            tiposReciclados.clear(); // Limpiar el registro para evitar recompensas duplicadas
        }

        // Verificar y otorgar recompensas por reciclar en todas las zonas
        zonasRecicladas.add(zona.toLowerCase());
        if (zonasRecicladas.containsAll(zonasRequeridas)) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicidades! Has reciclado en todas las zonas. Recompensa: 100 puntos");
            zonasRecicladas.clear(); // Limpiar el registro para evitar recompensas duplicadas
        }

        // Verificar hitos de reciclaje y otorgar recompensas
        if (objetosReciclados == 100) {
            usuario.agregarPuntos(100);
            System.out.println("¡Felicitaciones! Has reciclado 100 objetos. Recompensa: 100 puntos");
        }

        if (objetosReciclados == 1000) {
            usuario.agregarPuntos(100);
            System.out.println("¡Increíble! Has reciclado 1000 objetos. Recompensa: 100 puntos");
        }

        // Subir de nivel cada 100 puntos
        if (usuario.getPuntos() >= 100) {
            usuario.subirNivel();
            usuario.restarPuntos(100); // Reinicia puntos para el nuevo nivel
            // Mostrar notificación de subida de nivel
            JOptionPane.showMessageDialog(null, 
                "¡Felicitaciones! Has subido de nivel. Ahora estás en el nivel " + usuario.getNivelActual(),
                "Subida de Nivel", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
