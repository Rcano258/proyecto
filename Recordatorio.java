import java.time.LocalDateTime;

public class Recordatorio {
    private String mensaje;
    private LocalDateTime fechaHora;

    public Recordatorio(String mensaje, LocalDateTime fechaHora) {
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }

    public boolean esMomentoDeRecordar(LocalDateTime actual) {
        return actual.isEqual(fechaHora) || actual.isAfter(fechaHora);
    }

    public String getMensaje() {
        return mensaje;
    }
}
