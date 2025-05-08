package fridgeSmart.fridgesmart.comun.repositorio.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlimentoDao {

    //Obtener lista de carnes dado la subcategoria
    @Query("SELECT * FROM AlimentoDb where subcategoria =:subcategoria ORDER BY (CASE WHEN kilos = 0.0 AND cantidad = 0 THEN 1 ELSE 0 END), id")
    LiveData<List<AlimentoDb>> getCarnesDeSubcategoria(String subcategoria);

    //Obtener lista de alimentos dado la categoria, los que tiene kilo y cantidad  a 0 ponlos ultimos
    @Query("SELECT * FROM AlimentoDb where categoria =:categoria ORDER BY (CASE WHEN kilos = 0.0 AND cantidad = 0 THEN 1 ELSE 0 END), id")
    LiveData<List<AlimentoDb>> getAlimentosDeCategoria(String categoria);

    //Guardar alimentDb
    @Insert
    void guardarAlimento(AlimentoDb alimentoDb);

    //Borrar alimento dado su id
    @Query("DELETE FROM AlimentoDb WHERE id = :id")
    void borrarAlimento(int id);

    //Modificar alimento
    @Query("UPDATE AlimentoDb SET cantidad = :cantidad, kilos = :kilos, fechaCaducidad = :fechaCaducidad WHERE id = :id")
    void modificarAlimento(int id, int cantidad, double kilos, String fechaCaducidad);
}
