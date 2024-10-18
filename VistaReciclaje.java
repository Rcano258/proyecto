public class VistaReciclaje {
    private Reciclaje reciclaje = new Reciclaje();

    public void mostrarMateriales() {
        System.out.println("Materiales reciclables:");
        for (String material : reciclaje.getMaterialesReciclables()) {
            System.out.println("- " + material);
        }
    }

    public void mostrarBeneficios() {
        System.out.println("Beneficios del reciclaje:");
        for (String beneficio : reciclaje.getBeneficios()) {
            System.out.println("- " + beneficio);
        }
    }
}
