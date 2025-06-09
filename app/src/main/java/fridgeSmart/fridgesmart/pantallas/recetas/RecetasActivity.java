package fridgeSmart.fridgesmart.pantallas.recetas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.modelos.Receta;
import fridgeSmart.fridgesmart.comun.repositorio.RecetasRepository;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDao;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;

public class RecetasActivity extends AppCompatActivity {

    private RecetasViewModel viewModel;
    private AlimentoDao alimentoDao;
    private List<String> ingredientesUsuario = new ArrayList<>();

    // Diccionario de sinónimos
    private final Map<String, HashSet<String>> sinonimos = new HashMap<>();

    private final int[] layoutIds = {
            R.id.receta1,
            R.id.receta2,
            R.id.receta3,
            R.id.receta4
    };

    private final int[] nombreRecetaIds = {
            R.id.nombreReceta1,
            R.id.nombreReceta2,
            R.id.nombreReceta3,
            R.id.nombreReceta4
    };

    private final int[] descripcionRecetaIds = {
            R.id.descripcionReceta1,
            R.id.descripcionReceta2,
            R.id.descripcionReceta3,
            R.id.descripcionReceta4
    };

    // Para las imágenes usa directamente los IDs definidos en el layout para cada imagen
    private final int[] imagenRecetaIds = {
            R.id.imageView11,
            R.id.imageView9,
            R.id.imageView6,
            R.id.imageView7
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recetas);

        // 1. Hacer visible el botón
        ImageView backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(v -> finish());
        } else {
            Log.e("DEBUG", "¡El botón backButton no se encontró en el layout!");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(RecetasViewModel.class);
        AppDatabase db = AppDatabase.getInstance(this);
        alimentoDao = db.alimentoDao();

        configurarObservadores();

    }

    @Override
    protected void onStart() {
        super.onStart();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            int count = alimentoDao.count();
            Log.d("DB_STATUS", "Total alimentos en BD: " + count);

            if (count == 0) {
                Log.e("DB_ERROR", "La base de datos está vacía");
            }
        });
    }

    private void configurarObservadores() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<AlimentoDb> lista = alimentoDao.obtenerTodos();
            Log.d("DEBUG", "Alimentos en BD: " + lista.size());
            for (AlimentoDb a : lista) {
                Log.d("DEBUG", "Alimento: " + a.nombre);
            }
        });

        alimentoDao.obtenerTodosLiveData().observe(this, alimentos -> {
            if (alimentos != null) {
                ingredientesUsuario.clear();
                for (AlimentoDb alimento : alimentos) {
                    if (!alimento.descartado()) {
                        ingredientesUsuario.add(alimento.nombre.toLowerCase().trim());
                    }
                }
                Log.d("DEBUG", "Ingredientes cargados: " + ingredientesUsuario.toString());
                mostrarRecetas();  // Solo se llama aquí, con datos ya listos
            } else {
                Log.e("ERROR", "No se encontraron alimentos en la BD");
            }
        });

    }

    private void mostrarRecetas() {
        List<Receta> recetas = RecetasRepository.getInstance().getRecetasPredeterminadas();
        int numRecetasMostrar = Math.min(recetas.size(), 4);

        recetas.sort((r1, r2) -> Double.compare(
                calcularPorcentajeCoincidencia(r2, ingredientesUsuario),
                calcularPorcentajeCoincidencia(r1, ingredientesUsuario)
        ));

        for (int i = 0; i < 4; i++) {
            if (i < numRecetasMostrar) {
                configurarReceta(i, recetas.get(i));
            } else {
                // Oculta recetas no usadas
                ConstraintLayout recetaView = findViewById(layoutIds[i]);
                if (recetaView != null) recetaView.setVisibility(View.GONE);
            }
        }
    }

    private void configurarReceta(int index, Receta receta) {
        ConstraintLayout recetaView = findViewById(layoutIds[index]);
        if (recetaView == null) return;

        // Mostrar la vista si estaba oculta
        recetaView.setVisibility(View.VISIBLE);

        TextView nombreReceta = recetaView.findViewById(nombreRecetaIds[index]);
        if (nombreReceta != null) {
            nombreReceta.setText(receta.nombre);
        }

        List<String> faltantes = getIngredientesFaltantes(receta.ingredientes);



        TextView descripcion = recetaView.findViewById(descripcionRecetaIds[index]);
        if (descripcion != null) {
            if (faltantes.isEmpty()) {
                descripcion.setText("Tienes todos los ingredientes");
                descripcion.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            } else {
                descripcion.setText("Faltan: " + String.join(", ", faltantes));
                descripcion.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            }
        }

        ImageView imagen = recetaView.findViewById(imagenRecetaIds[index]);
        if (imagen != null) {
            int drawableId = getResources().getIdentifier(receta.imagen, "drawable", getPackageName());
            imagen.setImageResource(drawableId != 0 ? drawableId : R.drawable.ic_food_placeholder);
        }

        recetaView.setOnClickListener(v -> {
            startActivity(new Intent(this, DetalleRecetaActivity.class)
                    .putExtra("receta", receta));
        });
    }

    private List<String> getIngredientesFaltantes(List<String> ingredientesReceta) {
        List<String> faltantes = new ArrayList<>();
        for (String ingrediente : ingredientesReceta) {
            boolean encontrado = false;
            for (String userIng : ingredientesUsuario) {
                if (contieneIngrediente(userIng, ingrediente)) {
                    Log.d("COMPARACION", "Usuario: " + userIng + " vs Receta: " + ingrediente);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                Log.d("COMPARACION", "Usuario: " + ingrediente + " faltantes");
                faltantes.add(ingrediente);
            }
        }
        return faltantes;
    }

    private boolean contieneIngrediente(String ingredienteUsuario, String ingredienteReceta) {

        String userIng = ingredienteUsuario.toLowerCase().trim();
        String recetaIng = ingredienteReceta.toLowerCase().trim();
        Log.d("COMPARACION", "Usuario: " + userIng + " vs Receta: " + recetaIng);

        // Igual exacto
        if (userIng.equals(recetaIng)){
            Log.d("COMPARACION", "Coincidencia exacta encontrada");
            return true;
        }

        if (userIng.contains(recetaIng)){
            return true;
        }

        return false;
    }



    private double calcularPorcentajeCoincidencia(Receta receta, List<String> ingredientesUsuario) {
        if (receta.ingredientes == null || receta.ingredientes.isEmpty()) {
            return 0;
        }

        int coincidencias = 0;
        for (String ingrediente : receta.ingredientes) {
            for (String userIng : ingredientesUsuario) {
                if (contieneIngrediente(userIng, ingrediente)) {
                    coincidencias++;
                    break;
                }
            }
        }
        return (double) coincidencias / receta.ingredientes.size();
    }

}
