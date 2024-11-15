import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecordatorios {
    private List<Recordatorio> recordatorios = new ArrayList<>();

    public void agregarRecordatorio(String mensaje, LocalDateTime fechaHora) {
        recordatorios.add(new Recordatorio(mensaje, fechaHora));
    }

    public void mostrarRecordatoriosPendientes(LocalDateTime actual) {
        for (Recordatorio recordatorio : recordatorios) {
            if (recordatorio.esHora(actual)) {
                System.out.println("Recordatorio: " + recordatorio.getMensaje());
            }
        }
    }

    private class Recordatorio {
        private String mensaje;
        private LocalDateTime fechaHora;

        public Recordatorio(String mensaje, LocalDateTime fechaHora) {
            this.mensaje = mensaje;
            this.fechaHora = fechaHora;
        }

        public boolean esHora(LocalDateTime actual) {
            return fechaHora.isEqual(actual) || fechaHora.isBefore(actual);
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
