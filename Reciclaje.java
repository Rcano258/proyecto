import java.util.ArrayList;
import java.util.List;

public class Reciclaje {
    public List<String> getMaterialesReciclables() {
        List<String> materiales = new ArrayList<>();
        materiales.add("Papel y cartón");
        materiales.add("Vidrio");
        materiales.add("Plástico");
        materiales.add("Metal");
        materiales.add("Electrónicos");
        return materiales;
    }

    public List<String> getBeneficios() {
        List<String> beneficios = new ArrayList<>();
        beneficios.add("Conservación de recursos naturales");
        beneficios.add("Reducción de la contaminación");
        beneficios.add("Ahorro de energía");
        beneficios.add("Reducción de residuos en vertederos");
        return beneficios;
    }
}
