package fridgeSmart.fridgesmart.pantallas.recetas;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.comun.modelos.Receta;
import fridgeSmart.fridgesmart.comun.repositorio.RecetasRepository;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;

public class RecetasViewModel extends AndroidViewModel {
    private LiveData<List<String>> ingredientesUsuario;

    public RecetasViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        ingredientesUsuario = Transformations.map(db.alimentoDao().obtenerTodosLiveData(), alimentos -> {
            List<String> nombres = new ArrayList<>();
            for (AlimentoDb alimento : alimentos) {
                if (!alimento.descartado()) {
                    nombres.add(alimento.nombre.toLowerCase());
                }
            }
            return nombres;
        });
    }

    public LiveData<List<String>> getIngredientesUsuario() {
        return ingredientesUsuario;
    }
}