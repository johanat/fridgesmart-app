package fridgeSmart.fridgesmart.comun;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;

public class AlimentoMapper {

    public static AlimentoDb mapToAlimentoDb(AlimentoPredeterminado predeterminado) {
        return new AlimentoDb(
                predeterminado.id != null ? predeterminado.id : 0, // idAlimentoPredeterminado
                predeterminado.imagenId,
                predeterminado.categoria,
                predeterminado.subcategoria,
                predeterminado.nombre,
                0,                 // cantidad
                0.0,               // kilos
                "",                // fechaCaducidad
                predeterminado.selecionado
        );
    }

    public static List<AlimentoDb> mapListToAlimentoDb(List<AlimentoPredeterminado> listaPredeterminados) {
        List<AlimentoDb> resultado = new ArrayList<>();
        for (AlimentoPredeterminado p : listaPredeterminados) {
            resultado.add(mapToAlimentoDb(p));
        }
        return resultado;
    }

}
