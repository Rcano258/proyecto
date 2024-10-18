public class VistaLugares {
    private Lugares lugares = new Lugares();

    public void mostrarLugares() {
        System.out.println("Puntos de reciclaje disponibles:");
        for (String lugar : lugares.getLugaresReciclaje()) {
            System.out.println("- " + lugar);
        }
    }
}
