public class VistaConsejos {
    private Consejos consejos = new Consejos();

    public void mostrarConsejos() {
        System.out.println("Consejos para reciclar:");
        for (String consejo : consejos.getConsejos()) {
            System.out.println("- " + consejo);
        }
    }
}
