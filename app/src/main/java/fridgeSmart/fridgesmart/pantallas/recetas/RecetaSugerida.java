package fridgeSmart.fridgesmart.pantallas.recetas;
import java.util.List;

public class RecetaSugerida {
    public String nombre;
    public List<String> ingredientesFaltantes;

    public RecetaSugerida(String nombre, List<String> faltantes) {
        this.nombre = nombre;
        this.ingredientesFaltantes = faltantes;
    }
}
