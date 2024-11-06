import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class ReciclajeAppGUI {
    private JFrame frame;
    private Usuario usuario;
    private CatalogoReciclaje catalogo;
    private ControladorRecordatorios controladorRecordatorios;
    private ControladorReciclaje controladorReciclaje;

    public ReciclajeAppGUI() {
        catalogo = new CatalogoReciclaje();
        controladorRecordatorios = new ControladorRecordatorios();
        inicializar();
    }

    private void inicializar() {
        frame = new JFrame("Aplicación de Reciclaje");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(new GridLayout(11, 1));

        // Crear el usuario
        usuario = crearUsuario();
        controladorReciclaje = new ControladorReciclaje(usuario);

        // Agregar botones de las funciones
        JButton btnConsejos = new JButton("Ver Consejos para Reciclar");
        JButton btnLugares = new JButton("Ver Lugares de Reciclaje");
        JButton btnMateriales = new JButton("Ver Materiales Reciclables");
        JButton btnBeneficios = new JButton("Ver Beneficios del Reciclaje");
        JButton btnRecordatorio = new JButton("Configurar Recordatorio");
        JButton btnDesafios = new JButton("Ver Desafíos y Recompensas");
        JButton btnReciclar = new JButton("Reciclar un Objeto");
        JButton btnVerPuntaje = new JButton("Ver Puntaje Actual"); // Nuevo botón
        JButton btnSalir = new JButton("Salir");

        // Configurar acciones de cada botón
        btnConsejos.addActionListener(e -> mostrarLista("Consejos para reciclar", catalogo.getConsejos()));
        btnLugares.addActionListener(e -> mostrarLista("Lugares de reciclaje", catalogo.getLugaresReciclaje()));
        btnMateriales.addActionListener(e -> mostrarLista("Materiales reciclables", catalogo.getMaterialesReciclables()));
        btnBeneficios.addActionListener(e -> mostrarLista("Beneficios del reciclaje", catalogo.getBeneficios()));
        btnRecordatorio.addActionListener(e -> configurarRecordatorio());
        btnDesafios.addActionListener(e -> mostrarDesafios());
        btnReciclar.addActionListener(e -> reciclarObjeto());
        btnVerPuntaje.addActionListener(e -> verPuntaje()); // Acción para ver puntaje
        btnSalir.addActionListener(e -> salir());

        // Agregar botones al frame
        frame.add(new JLabel("¡Bienvenido, " + usuario.getNombre() + "!"));
        frame.add(btnConsejos);
        frame.add(btnLugares);
        frame.add(btnMateriales);
        frame.add(btnBeneficios);
        frame.add(btnRecordatorio);
        frame.add(btnDesafios);
        frame.add(btnReciclar);
        frame.add(btnVerPuntaje); // Agregar el botón de puntaje al frame
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

        // Configurar el selector de fecha
        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd");
        spinnerFecha.setEditor(dateEditor);
        panel.add(new JLabel("Selecciona la fecha:"));
        panel.add(spinnerFecha);

        // Configurar el selector de hora
        JSpinner spinnerHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(timeEditor);
        panel.add(new JLabel("Selecciona la hora:"));
        panel.add(spinnerHora);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Configurar Recordatorio", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Obtener la fecha y la hora seleccionadas
            Date fechaSeleccionada = (Date) spinnerFecha.getValue();
            Date horaSeleccionada = (Date) spinnerHora.getValue();

            LocalDate fecha = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime hora = horaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            controladorRecordatorios.agregarRecordatorio("¡Es momento de reciclar!", fechaHora);
            JOptionPane.showMessageDialog(frame, "Recordatorio configurado para: " + fechaHora);
        }
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

    private void reciclarObjeto() {
        String nombre = JOptionPane.showInputDialog(frame, "Ingresa el objeto reciclado:");
        String tipo = solicitarClasificacion();
        String zona = solicitarZonaReciclaje();

        Residuos residuo = new Residuos(tipo, nombre, "Objeto reciclado ingresado por el usuario");
        controladorReciclaje.registrarReciclaje(residuo.obtenerTipo(), zona);
        JOptionPane.showMessageDialog(frame, "Reciclaste en " + zona + ". ¡Gracias por reciclar!");
    }

    private String solicitarClasificacion() {
        String[] opciones = {"Orgánico", "Vidrio", "Plástico", "Metal", "Electrónico"};
        return (String) JOptionPane.showInputDialog(frame, "Selecciona la clasificación del objeto:", "Clasificación",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    private String solicitarZonaReciclaje() {
        String[] opciones = {"Zona 1", "Zona 2", "Zona 3", "Zona 4"};
        return (String) JOptionPane.showInputDialog(frame, "Selecciona la zona donde reciclaste:", "Zona de Reciclaje",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    private void verPuntaje() {
        int puntos = usuario.getPuntos();
        JOptionPane.showMessageDialog(frame, "Puntaje actual: " + puntos + " puntos", "Puntaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que deseas salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReciclajeAppGUI::new);
    }
}
