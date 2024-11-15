import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class VistaUsuario {
    private JFrame frame;
    private Usuario usuario;
    private CatalogoReciclaje catalogo;
    private ControladorRecordatorios controladorRecordatorios;
    private ControladorReciclaje controladorReciclaje;

    public VistaUsuario() {
        catalogo = new CatalogoReciclaje();
        controladorRecordatorios = new ControladorRecordatorios();
        inicializar();
    }

    private void inicializar() {
        frame = new JFrame("Aplicación de Reciclaje");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(12, 1));

        usuario = crearUsuario();
        controladorReciclaje = new ControladorReciclaje(usuario);

        JButton btnConsejos = new JButton("Ver Consejos para Reciclar");
        JButton btnLugares = new JButton("Ver Lugares de Reciclaje");
        JButton btnMateriales = new JButton("Ver Materiales Reciclables");
        JButton btnBeneficios = new JButton("Ver Beneficios del Reciclaje");
        JButton btnRecordatorio = new JButton("Configurar Recordatorio");
        JButton btnDesafios = new JButton("Ver Desafíos y Recompensas");
        JButton btnReciclar = new JButton("Reciclar un Objeto");
        JButton btnVerPuntaje = new JButton("Ver Puntaje Actual");
        JButton btnVerNivel = new JButton("Ver Nivel Actual"); // Nuevo botón
        JButton btnSalir = new JButton("Salir");

        btnConsejos.addActionListener(e -> mostrarLista("Consejos para reciclar", catalogo.getConsejos()));
        btnLugares.addActionListener(e -> mostrarLista("Lugares de reciclaje", catalogo.getLugaresReciclaje()));
        btnMateriales.addActionListener(e -> mostrarLista("Materiales reciclables", catalogo.getMaterialesReciclables()));
        btnBeneficios.addActionListener(e -> mostrarLista("Beneficios del reciclaje", catalogo.getBeneficios()));
        btnRecordatorio.addActionListener(e -> configurarRecordatorio());
        btnDesafios.addActionListener(e -> mostrarDesafios());
        btnReciclar.addActionListener(e -> reciclarObjeto());
        btnVerPuntaje.addActionListener(e -> verPuntaje());
        btnVerNivel.addActionListener(e -> verNivelActual()); // Acción del nuevo botón
        btnSalir.addActionListener(e -> salir());

        frame.add(new JLabel("¡Bienvenido, " + usuario.getNombre() + "!"));
        frame.add(btnConsejos);
        frame.add(btnLugares);
        frame.add(btnMateriales);
        frame.add(btnBeneficios);
        frame.add(btnRecordatorio);
        frame.add(btnDesafios);
        frame.add(btnReciclar);
        frame.add(btnVerPuntaje);
        frame.add(btnVerNivel); // Añadir el botón al GUI
        frame.add(btnSalir);

        frame.setVisible(true);
    }

    private Usuario crearUsuario() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingresa tu nombre:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingresa tu edad:"));
        String email = JOptionPane.showInputDialog(frame, "Ingresa tu email:");
        return new Usuario(nombre, edad, email);
    }

    private void mostrarLista(String titulo, java.util.List<String> lista) {
        String mensaje = String.join("\n", lista);
        JOptionPane.showMessageDialog(frame, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private void configurarRecordatorio() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(dateEditor);
        panel.add(new JLabel("Selecciona la fecha:"));
        panel.add(spinnerFecha);

        JSpinner spinnerHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(timeEditor);
        panel.add(new JLabel("Selecciona la hora:"));
        panel.add(spinnerHora);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Configurar Recordatorio", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Date fechaSeleccionada = (Date) spinnerFecha.getValue();
            Date horaSeleccionada = (Date) spinnerHora.getValue();

            LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime hora = horaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            controladorRecordatorios.agregarRecordatorio("¡Es momento de reciclar!", fechaHora);
            JOptionPane.showMessageDialog(frame, "Recordatorio configurado para: " + fechaHora);
        }
    }

    private void reciclarObjeto() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingresa el objeto reciclado:");
        String tipo = solicitarClasificacion();
        String zona = solicitarZonaReciclaje();

        Residuos residuo = new Residuos(tipo, nombre, "Objeto reciclado ingresado por el usuario");
        String mensaje = controladorReciclaje.registrarReciclaje(residuo.obtenerTipo(), zona);

        // Mostrar el mensaje acumulado
        JOptionPane.showMessageDialog(frame, mensaje, "Reciclaje Completo", JOptionPane.INFORMATION_MESSAGE);
    }

    private String solicitarClasificacion() {
        String[] opciones = {"Orgánico", "Vidrio", "Plástico", "Metal", "Electrónico"};
        return (String) JOptionPane.showInputDialog(frame, "Selecciona la clasificación del objeto:", "Clasificación",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    private String solicitarZonaReciclaje() {
        String[] opciones = {"Zona 1", "Zona 2", "Zona 3", "Zona 4"};
        int opcion = JOptionPane.showOptionDialog(frame, "Selecciona la zona donde reciclaste:",
                "Zona de Reciclaje", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return opciones[opcion];
    }

    private void verPuntaje() {
        int puntos = usuario.getPuntos();
        JOptionPane.showMessageDialog(frame, "Puntaje actual: " + puntos + " puntos", "Puntaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verNivelActual() {
        int nivel = usuario.getNivelActual();
        JOptionPane.showMessageDialog(frame, "Tu nivel actual es: " + nivel, "Nivel Actual", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarDesafios() {
        String desafios = """
            - Recicla todos los tipos de materiales reciclables | Recompensa: 100 puntos
            - Llega a los 100 objetos reciclados | Recompensa: 100 puntos
            - Llega a los 1000 objetos reciclados | Recompensa: 100 puntos
            - Recicla en todas las zonas | Recompensa: 100 puntos
            """;
        JOptionPane.showMessageDialog(frame, desafios, "Desafíos y Recompensas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que deseas salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }
}
