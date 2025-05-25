package fridgeSmart.fridgesmart.pantallas.recetas;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.pantallas.recetas.RecetaAdapter;
import fridgeSmart.fridgesmart.pantallas.recetas.RecetaSugerida;

public class RecetasActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private RecetaAdapter adapter;
    private List<RecetaSugerida> listaRecetas = new ArrayList<>();
    private AppDatabase db;
    private Button btnPedirReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recetas2);

        recyclerRecetas = findViewById(R.id.recyclerRecetas);
        btnPedirReceta = findViewById(R.id.btnPedirReceta);
        recyclerRecetas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecetaAdapter(listaRecetas);
        recyclerRecetas.setAdapter(adapter);

        db = AppDatabase.getInstance(this);

        btnPedirReceta.setOnClickListener(v -> {
            // Obtenemos alimentos de la base de datos (por ejemplo categoría "Carnes")
            db.alimentoDao().getAlimentosDeCategoria("Carnes").observe(this, alimentos -> {
                List<String> nombres = new ArrayList<>();
                for (AlimentoDb a : alimentos) {
                    if (!a.descartado()) {
                        nombres.add(a.nombre);
                    }
                }
                String ingredientes = TextUtils.join(", ", nombres);

                HuggingFaceIA.pedirReceta(ingredientes, new HuggingFaceIA.RespuestaIAListener() {
                    @Override
                    public void alRecibirRespuesta(String texto) {
                        runOnUiThread(() -> {
                            // Aquí parseas el texto para generar las recetas sugeridas (puedes personalizarlo)
                            listaRecetas.clear();
                            listaRecetas.add(new RecetaSugerida(texto, new ArrayList<>()));
                            adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void alFallar(String error) {
                        runOnUiThread(() -> {
                            Toast.makeText(RecetasActivity.this, "Error IA: " + error, Toast.LENGTH_SHORT).show();
                        });
                    }
                });
            });
        });
    }
}
