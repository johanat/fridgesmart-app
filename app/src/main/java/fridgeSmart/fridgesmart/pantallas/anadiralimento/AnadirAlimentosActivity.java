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

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.AlimentoMapper;
import fridgeSmart.fridgesmart.comun.AlimentoPredeterminado;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.notificaciones.CaducidadWorker;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE;
public class AnadirAlimentosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_alimentos);

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
                Log.d("VALIDACION", "alimento: " + alimento.nombre + ", cantidad: " + alimento.cantidad + ", kilos: " + alimento.kilos + ", fecha: " + alimento.fechaCaducidad);
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
            List<AlimentoDb> alimentosSeleccionados = adapter.getAlimentosSelecionados();
            //Guardar cada elemento en la base de datos
            for (AlimentoDb alimento : alimentosSeleccionados) {
                //Aliment a guardar tiene que ser igual a alimento pero con el campo "select" en false
                ((MyApp) getApplication()).repositorio.guardarAlimento(quitarSeleccion(alimento));
            }
            // Lanzar el worker para la notificación inmediata
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(CaducidadWorker.class).build();
            WorkManager.getInstance(this).enqueue(workRequest);
            finish();
        });
    }

    private AlimentoDb quitarSeleccion(AlimentoDb alimento) {
        // Crear una nueva instancia de AlimentoDb con el campo "select" en false
        return new AlimentoDb(alimento.idAlimentoPredeterminado, alimento.imagenId, alimento.categoria, alimento.subcategoria, alimento.nombre, alimento.cantidad, alimento.kilos, alimento.fechaCaducidad, false,
                false);
    }
}