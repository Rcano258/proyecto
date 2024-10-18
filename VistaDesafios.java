import java.util.HashMap;
import java.util.Map;

public class VistaDesafios {
    private final Map<String, String> desafios = new HashMap<>();

    public VistaDesafios() {
        // Inicialización de los desafíos
        desafios.put("Recicla todos los tipos de materiales reciclables", "Recompensa: 100 puntos");
        desafios.put("Llega a los 100 objetos reciclados", "Recompensa: 100 puntos");
        desafios.put("Llega a los 1000 objetos reciclados", "Recompensa: 100 puntos");
        desafios.put("Recicla en todas las zonas", "Recompensa: 100 puntos");
    }

    public void mostrarDesafios() {
        System.out.println("Lista de desafíos:");
        desafios.forEach((desafio, recompensa) -> 
            System.out.println("- " + desafio + " | " + recompensa)
        );
    }

    public void mostrarMensajeRecompensa(String mensaje) {
        System.out.println(mensaje);
    }
}
