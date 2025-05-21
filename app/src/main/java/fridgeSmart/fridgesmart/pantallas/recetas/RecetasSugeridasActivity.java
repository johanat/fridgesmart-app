package fridgeSmart.fridgesmart.pantallas.recetas;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.pantallas.recetas.HuggingFaceIA;

public class RecetasSugeridasActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private RecetaAdapter adapter;
    private List<RecetaSugerida> listaRecetas = new ArrayList<>();
    private AppDatabase db;
    private Button btnPedirReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Solo si usas esa librería o configúralo como necesites
        setContentView(R.layout.activity_recetas2);

        recyclerRecetas = findViewById(R.id.recyclerRecetas);
        btnPedirReceta = findViewById(R.id.btnPedirReceta);

        recyclerRecetas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecetaAdapter(listaRecetas);
        recyclerRecetas.setAdapter(adapter);

        db = AppDatabase.getInstance(this);

        btnPedirReceta.setOnClickListener(v -> {
            // Obtenemos los alimentos de la base de datos (ejemplo: categoría "Carnes")
            db.alimentoDao().getAlimentosDeCategoria("Carnes").observe(this, alimentos -> {
                List<String> nombres = new ArrayList<>();
                for (AlimentoDb a : alimentos) {
                    if (!a.descartado()) {
                        nombres.add(a.nombre);
                    }
                }
                String ingredientes = TextUtils.join(", ", nombres);

                // Llamada a la IA para pedir receta con esos ingredientes
                HuggingFaceIA.pedirReceta(ingredientes, new HuggingFaceIA.RespuestaIAListener() {
                    @Override
                    public void alRecibirRespuesta(String texto) {
                        runOnUiThread(() -> {
                            // Aquí podrías parsear la respuesta para extraer varias recetas, pero simplificamos
                            listaRecetas.clear();
                            listaRecetas.add(new RecetaSugerida(texto, new ArrayList<>()));
                            adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void alFallar(String error) {
                        runOnUiThread(() -> {
                            Toast.makeText(RecetasSugeridasActivity.this, "Error IA: " + error, Toast.LENGTH_SHORT).show();
                        });
                    }
                });
            });
        });
    }

    // Clase para modelar receta sugerida
    public static class RecetaSugerida {
        public String nombreReceta;
        public List<String> ingredientesFaltantes;

        public RecetaSugerida(String nombreReceta, List<String> ingredientesFaltantes) {
            this.nombreReceta = nombreReceta;
            this.ingredientesFaltantes = ingredientesFaltantes;
        }
    }

    // Adapter RecyclerView para mostrar recetas
    public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

        private final List<RecetaSugerida> lista;

        public RecetaAdapter(List<RecetaSugerida> lista) {
            this.lista = lista;
        }

        @Override
        public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            android.view.View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_2, parent, false);
            return new RecetaViewHolder(view);
        }
/*
        @NonNull
        @Override
        public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }
*/
        @Override
        public void onBindViewHolder(RecetaViewHolder holder, int position) {
            RecetaSugerida receta = lista.get(position);
            holder.text1.setText(receta.nombreReceta);
            if (receta.ingredientesFaltantes != null && !receta.ingredientesFaltantes.isEmpty()) {
                holder.text2.setText("Faltan: " + TextUtils.join(", ", receta.ingredientesFaltantes));
            } else {
                holder.text2.setText("Todos los ingredientes disponibles");
            }
        }

        @Override
        public int getItemCount() {
            return lista.size();
        }

        class RecetaViewHolder extends RecyclerView.ViewHolder {
            android.widget.TextView text1, text2;

            public RecetaViewHolder(android.view.View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
                text2 = itemView.findViewById(android.R.id.text2);
            }
        }
    }
}
