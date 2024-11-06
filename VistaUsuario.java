import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Set;

public class VistaUsuario {
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Conjunto de opciones válidas para la clasificación del objeto
    private final Set<String> clasificacionesValidas = Set.of("orgánico", "vidrio", "plástico", "metal", "electrónico");

    // Mostrar el menú principal y obtener la opción seleccionada
    public int mostrarMenuPrincipal() {
        System.out.println("\n¿Qué deseas hacer?");
        System.out.println("1. Ver consejos para reciclar");
        System.out.println("2. Ver lugares de reciclaje");
        System.out.println("3. Ver materiales reciclables");
        System.out.println("4. Ver beneficios del reciclaje");
        System.out.println("5. Configurar recordatorio");
        System.out.println("6. Ver desafíos y recompensas");
        System.out.println("7. Reciclar un objeto");
        System.out.println("8. Salir");

        return leerOpcion();
    }

    // Crear un nuevo usuario solicitando los datos
    public Usuario crearUsuario() {
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine().trim();

        int edad = solicitarEdad();

        System.out.print("Ingresa tu email: ");
        String email = scanner.nextLine().trim();

        return new Usuario(nombre, edad, email);
    }

    // Mostrar los desafíos y sus recompensas
    public void mostrarDesafios() {
        System.out.println("Lista de desafíos:");
        System.out.println("- Recicla todos los tipos de materiales reciclables | Recompensa: 100 puntos");
        System.out.println("- Llega a los 100 objetos reciclados | Recompensa: 100 puntos");
        System.out.println("- Llega a los 1000 objetos reciclados | Recompensa: 100 puntos");
        System.out.println("- Recicla en todas las zonas | Recompensa: 100 puntos");
    }

    // Solicitar al usuario la información del objeto reciclado y validar el tipo
    public Residuos ingresarObjetoReciclado() {
        System.out.print("Ingresa el objeto reciclado: ");
        String nombre = scanner.nextLine().trim();

        String tipo = solicitarClasificacion();
        return new Residuos(tipo, nombre, "Objeto reciclado ingresado por el usuario");
    }

    // Solicitar la clasificación y validar que sea una opción permitida
    private String solicitarClasificacion() {
        while (true) {
            System.out.print("Ingresa la clasificación del objeto (Orgánico, Vidrio, Plástico, Metal, Electrónico): ");
            String tipo = scanner.nextLine().trim().toLowerCase();

            if (clasificacionesValidas.contains(tipo)) {
                return tipo.substring(0, 1).toUpperCase() + tipo.substring(1);  // Capitalizar la primera letra
            } else {
                System.out.println("Clasificación no válida. Ingresa una opción correcta.");
            }
        }
    }

    // Solicitar la zona de reciclaje y validar la opción
    public String solicitarZonaReciclaje() {
        System.out.println("Selecciona la zona donde reciclaste:");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");

        while (true) {
            int opcion = leerOpcion();
            switch (opcion) {
                case 1: return "Zona 1";
                case 2: return "Zona 2";
                case 3: return "Zona 3";
                case 4: return "Zona 4";
                default:
                    System.out.println("Opción no válida. Selecciona nuevamente.");
            }
        }
    }

    // Solicitar fecha y hora para los recordatorios
    public LocalDateTime solicitarFechaHora() {
        System.out.print("Ingresa la fecha y hora (YYYY-MM-DD HH:mm): ");
        String input = scanner.nextLine().trim();

        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido. Intenta de nuevo.");
            return solicitarFechaHora();
        }
    }

    // Mostrar un mensaje en consola
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Leer una opción y manejar errores de entrada
    private int leerOpcion() {
        while (true) {
            try {
                System.out.print("Selecciona una opción: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número.");
            }
        }
    }

    // Solicitar la edad del usuario y validar
    private int solicitarEdad() {
        while (true) {
            try {
                System.out.print("Ingresa tu edad: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Edad no válida. Ingresa un número.");
            }
        }
    }
}
