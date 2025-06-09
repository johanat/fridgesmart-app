package fridgeSmart.fridgesmart.pantallas.anadiralimento;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.AlimentoMapper;
import fridgeSmart.fridgesmart.comun.AlimentoPredeterminado;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.notificaciones.CaducidadWorker;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE;
public class AnadirAlimentosActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_alimentos);

        executorService = Executors.newSingleThreadExecutor();
        db = AppDatabase.getInstance(this);

        //obtenemos los extras
        String categoriaSeleccionada = getIntent().getStringExtra(CATEGORIA);
        String subcategoriaSeleccionada = getIntent().getStringExtra(SUBCATEGORIA_CARNE);


        //Aquí se mapea la lista de alimentos predeterminados a la lista de AlimentoDb
        List<AlimentoPredeterminado> alimentosPredeterminados = ((MyApp) getApplication()).repositorio.obtenerTodsLosAlimentos();
        List<AlimentoDb> alimentosDb = AlimentoMapper.mapListToAlimentoDb(alimentosPredeterminados);

        // Filtrar por categoría y subcategoría
        List<AlimentoDb> alimentosFiltrados = new ArrayList<>();

        for (AlimentoDb alimento : alimentosDb) {
            if (subcategoriaSeleccionada != null && !subcategoriaSeleccionada.isEmpty()) {
                // Caso: hay subcategoría (solo sucede con carnes)
                if (categoriaSeleccionada != null && categoriaSeleccionada.equals(alimento.categoria) &&
                        subcategoriaSeleccionada.equals(alimento.subcategoria)) {
                    alimentosFiltrados.add(alimento);
                }
            } else {
                // Caso: solo categoría (frutas, verduras, lácteos)
                if (categoriaSeleccionada != null && categoriaSeleccionada.equals(alimento.categoria)) {
                    alimentosFiltrados.add(alimento);
                }
            }
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerViewAlimentos);
        Button btnAdd = findViewById(R.id.btnAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AnadirAlimentoAdapter adapter = new AnadirAlimentoAdapter(alimentosFiltrados);

        adapter.setOnSelectionChangedListener(() -> {
            List<AlimentoDb> seleccionados = adapter.getAlimentosSelecionados();
            boolean camposValidos = true;

            for (AlimentoDb alimento : seleccionados) {
                if (CATEGORIA_CARNE.equals(alimento.categoria)) {
                    // Validación para carnes (solo kilos)
                    if (alimento.kilos <= 0.0) {
                        camposValidos = false;
                        break;
                    }
                } else {
                    // Validación para otras categorías (solo cantidad)
                    if (alimento.cantidad <= 0) {
                        camposValidos = false;
                        break;
                    }
                }
            }

            // El botón solo se habilita si hay seleccionados y todos los campos son válidos
            btnAdd.setEnabled(!seleccionados.isEmpty() && camposValidos);
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            List<AlimentoDb> seleccionados = adapter.getAlimentosSelecionados();

            if (seleccionados.isEmpty()) {
                Toast.makeText(this, "Selecciona al menos un alimento", Toast.LENGTH_SHORT).show();
                return;
            }

            executorService.execute(() -> {
                try {
                    List<AlimentoDb> paraGuardar = new ArrayList<>();
                    for (AlimentoDb alimento : seleccionados) {
                        paraGuardar.add(quitarSeleccion(alimento));
                    }
                    db.alimentoDao().insertAll(paraGuardar);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Alimentos guardados", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK); // Indica que la operación fue exitosa
                        finish();
                    });

                } catch (Exception e) {
                    Log.e("DB_ERROR", "Error al guardar: " + e.getMessage());
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_CANCELED);
                    });
                }
            });
        });
    }

    private AlimentoDb quitarSeleccion(AlimentoDb alimento) {
        // Normalizar el nombre antes de guardar
        String nombreNormalizado = alimento.nombre.toLowerCase().trim();
        return new AlimentoDb(
                alimento.idAlimentoPredeterminado,
                alimento.imagenId,
                alimento.categoria,
                alimento.subcategoria,
                nombreNormalizado,  // <-- Nombre normalizado
                alimento.cantidad,
                alimento.kilos,
                alimento.fechaCaducidad,
                false,
                false
        );
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // mos el executor cuando la actividad se destruya
        executorService.shutdown();
    }
}