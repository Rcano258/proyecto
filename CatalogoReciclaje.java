import java.util.ArrayList;
import java.util.List;

public class CatalogoReciclaje {
    public List<String> getConsejos() {
        List<String> consejos = new ArrayList<>();
        consejos.add("Separa los residuos correctamente.");
        consejos.add("Limpia los materiales antes de reciclar.");
        consejos.add("Evita el uso de plásticos de un solo uso.");
        consejos.add("Reutiliza los materiales siempre que puedas.");
        consejos.add("Fomenta el reciclaje en tu comunidad.");
        return consejos;
    }

    public List<String> getLugaresReciclaje() {
        List<String> lugares = new ArrayList<>();
        lugares.add("Zona 1");
        lugares.add("Zona 2");
        lugares.add("Zona 3");
        lugares.add("Zona 4");
        return lugares;
    }

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
        beneficios.add("Conservación de recursos naturales.");
        beneficios.add("Reducción de la contaminación.");
        beneficios.add("Ahorro de energía.");
        beneficios.add("Reducción de residuos en vertederos.");
        return beneficios;
    }
}
