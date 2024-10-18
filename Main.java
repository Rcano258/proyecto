import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Inicializar vistas y controladores
        VistaConsola vista = new VistaConsola();
        VistaDesafios vistaDesafios = new VistaDesafios();
        ControladorRecordatorios controladorRecordatorios = new ControladorRecordatorios();
        Usuario usuario = vista.crearUsuario();  // Crear usuario
        ControladorJuego controladorJuego = new ControladorJuego(usuario);
        ControladorDesafios controladorDesafios = new ControladorDesafios(usuario);

        // Mostrar mensaje de bienvenida
        vista.mostrarMensaje("¡Bienvenido, " + usuario.getNombre() + "!");

        boolean salir = false;  // Control del ciclo del menú principal
        while (!salir) {
            int opcion = vista.mostrarMenuPrincipal();  // Mostrar menú y leer opción
            switch (opcion) {
                case 1:
                    vista.mostrarMensaje("Consejos para reciclar:");
                    new Consejos().getConsejos().forEach(vista::mostrarMensaje);
                    break;

                case 2:
                    vista.mostrarMensaje("Lugares de reciclaje:");
                    new Lugares().getLugaresReciclaje().forEach(vista::mostrarMensaje);
                    break;

                case 3:
                    vista.mostrarMensaje("Materiales reciclables:");
                    new Reciclaje().getMaterialesReciclables().forEach(vista::mostrarMensaje);
                    break;

                case 4:
                    vista.mostrarMensaje("Beneficios del reciclaje:");
                    new Reciclaje().getBeneficios().forEach(vista::mostrarMensaje);
                    break;

                case 5:
                    LocalDateTime fechaHora = vista.solicitarFechaHora();
                    controladorRecordatorios.agregarRecordatorio("¡Es momento de reciclar!", fechaHora);
                    break;

                case 6:
                    vistaDesafios.mostrarDesafios();
                    break;

                case 7:
                    // Ingresar un objeto reciclado y registrar la zona
                    Residuos residuo = vista.ingresarObjetoReciclado();
                    String zona = vista.solicitarZonaReciclaje();

                    // Registrar el reciclaje en el juego y en los desafíos
                    controladorJuego.reciclarObjeto(residuo);
                    controladorDesafios.registrarObjetoReciclado(residuo.obtenerTipo(), zona);

                    // Mostrar mensaje de confirmación
                    vista.mostrarMensaje("Reciclaste en " + zona + ". ¡Gracias por reciclar!");
                    break;

                case 8:
                    salir = true;
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida. Intenta de nuevo.");
                    break;
            }

            // Mostrar recordatorios pendientes (simulación en tiempo real)
            controladorRecordatorios.mostrarRecordatoriosPendientes(LocalDateTime.now());
        }

        vista.mostrarMensaje("Gracias por usar la aplicación de reciclaje. ¡Adiós!");
    }
}
