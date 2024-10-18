import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorRecordatorios {
    private List<Recordatorio> recordatorios = new ArrayList<>();

    public void agregarRecordatorio(String mensaje, LocalDateTime fechaHora) {
        Recordatorio nuevoRecordatorio = new Recordatorio(mensaje, fechaHora);
        recordatorios.add(nuevoRecordatorio);
        System.out.println("Recordatorio creado para: " + fechaHora);
    }

    public void mostrarRecordatoriosPendientes(LocalDateTime actual) {
        for (Recordatorio recordatorio : recordatorios) {
            if (recordatorio.esMomentoDeRecordar(actual)) {
                System.out.println("¡Recordatorio!: " + recordatorio.getMensaje());
            }
        }
    }
}
