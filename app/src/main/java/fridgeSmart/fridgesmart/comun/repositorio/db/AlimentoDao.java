package fridgeSmart.fridgesmart.comun.repositorio.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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

    @Insert
    long insert(AlimentoDb alimento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AlimentoDb> alimentos);

    @Query("SELECT COUNT(*) FROM AlimentoDb")
    int count();

    //Borrar alimento dado su id
    @Query("DELETE FROM AlimentoDb WHERE id = :id")
    void borrarAlimento(int id);

    //Modificar alimento
    @Query("UPDATE AlimentoDb SET cantidad = :cantidad, kilos = :kilos, fechaCaducidad = :fechaCaducidad WHERE id = :id")
    void modificarAlimento(int id, int cantidad, double kilos, String fechaCaducidad);

    //obtener el conteo de alimentos por subcategoria
    @Query("SELECT COUNT(*) FROM AlimentoDb WHERE categoria = :categoria AND subcategoria = :subcategoria")
    LiveData<Integer> contarAlimentosPorSubcategoriaLiveData(String categoria, String subcategoria);

    @Query("SELECT * FROM AlimentoDb")
    List<AlimentoDb> obtenerTodos();

    // Nueva consulta para búsqueda general
    @Query("SELECT * FROM AlimentoDb WHERE nombre LIKE '%' || :query || '%'")
    LiveData<List<AlimentoDb>> buscarAlimentos(String query);


    @Query("SELECT * FROM AlimentoDb")
    LiveData<List<AlimentoDb>> obtenerTodosLiveData();



}
