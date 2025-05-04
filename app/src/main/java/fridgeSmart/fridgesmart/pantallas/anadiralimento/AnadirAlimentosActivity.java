package fridgeSmart.fridgesmart.pantallas.anadiralimento;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.AlimentoMapper;
import fridgeSmart.fridgesmart.comun.AlimentoPredeterminado;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;

public class AnadirAlimentosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_alimentos);

        //Aquí se mapea la lista de alimentos predeterminados a la lista de AlimentoDb
        List<AlimentoPredeterminado> alimentosPredeterminados = ((MyApp) getApplication()).repositorio.obtenerTodsLosAlimentos();
        List<AlimentoDb> alimentosDb = AlimentoMapper.mapListToAlimentoDb(alimentosPredeterminados);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAlimentos);
        Button btnAdd = findViewById(R.id.btnAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AnadirAlimentoAdapter adapter = new AnadirAlimentoAdapter(alimentosDb);
        adapter.setOnSelectionChangedListener(() -> {
            boolean anySelected = !adapter.getSelectedIds().isEmpty();
            btnAdd.setEnabled(anySelected);
        });

        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            List<AlimentoDb> alimentosSeleccionados = adapter.getAlimentosSelecionados();
            //Guardar cada elemento en la base de datos
            for (AlimentoDb alimento : alimentosSeleccionados) {
                //Aliment a guardar tiene que ser igual a alimento pero con el campo "select" en false
                ((MyApp) getApplication()).repositorio.guardarAlimento(quitarSeleccion(alimento));
            }
            finish();
        });
    }

    private AlimentoDb quitarSeleccion(AlimentoDb alimento) {
        // Crear una nueva instancia de AlimentoDb con el campo "select" en false
        return new AlimentoDb(alimento.idAlimentoPredeterminado, alimento.imagenId, alimento.categoria, alimento.subcategoria, alimento.nombre, alimento.cantidad, alimento.kilos, alimento.fechaCaducidad, false);
    }
}