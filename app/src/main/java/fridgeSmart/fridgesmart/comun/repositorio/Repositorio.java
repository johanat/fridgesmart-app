package fridgeSmart.fridgesmart.comun.repositorio;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_FRUTA;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_CERDO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_EMBUTIDO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_PESCADO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_POLLO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_TERNERA;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.AlimentoPredeterminado;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.pantallas.subcategoriacarne.SubcategoriaCarne;

public class Repositorio {
    AppDatabase db;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public List<AlimentoPredeterminado> obtenerTodsLosAlimentos() {
        List<AlimentoPredeterminado> alimentoEntityList = new ArrayList<>();
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.alimento, "Lomo de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.alimento, "Lomo de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.mandarina, "Mandarina",false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.platano, "Plátano",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.manzanas, "Manzanas",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.uvas, "Uvas",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.naranja, "Naranjas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.durazno, "Durazno",  false));
        return alimentoEntityList;
    }

    public Repositorio(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "fridge-smart").build();
    }

    public void guardarAlimento(AlimentoDb alimentoDb) {
        executorService.execute(() -> db.alimentoDao().guardarAlimento(alimentoDb));
    }

    public void borrarAlimento(int id) {
        executorService.execute(() -> db.alimentoDao().borrarAlimento(id));
    }

    public void modificarAlimento(int id, int cantidad, double kilos, String fechaCaducidad) {
        executorService.execute(() -> db.alimentoDao().modificarAlimento(id, cantidad, kilos, fechaCaducidad));
    }

    public LiveData<List<AlimentoDb>> getCarnesDeGrupo(String subcategoria) {
        return db.alimentoDao().getCarnesDeSubcategoria(subcategoria);
    }

    public LiveData<List<AlimentoDb>> getAlimentosDeCategoria(String categoria) {
        return db.alimentoDao().getAlimentosDeCategoria(categoria);
    }


    public List<SubcategoriaCarne> obtenerSubcategoriaCarne() {
        List<SubcategoriaCarne> subcategoriaCarneCategoriaCarnes = new ArrayList<>();
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_TERNERA));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_CERDO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pollo, 3, SUBCATEGORIA_CARNE_POLLO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pescado, 2, SUBCATEGORIA_CARNE_PESCADO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.salchicha, 6, SUBCATEGORIA_CARNE_EMBUTIDO));

        return subcategoriaCarneCategoriaCarnes;
    }
}
