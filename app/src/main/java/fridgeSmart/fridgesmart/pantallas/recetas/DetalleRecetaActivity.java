package fridgeSmart.fridgesmart.pantallas.recetas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.modelos.Receta;

public class DetalleRecetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        Receta receta = (Receta) getIntent().getSerializableExtra("receta");
        if (receta == null) {
            finish();
            return;
        }

        // Configurar vistas
        ImageView imagen = findViewById(R.id.imagenReceta);
        TextView titulo = findViewById(R.id.tituloReceta);
        TextView ingredientes = findViewById(R.id.ingredientesReceta);
        TextView preparacion = findViewById(R.id.preparacionReceta);

        // Configurar imagen
        int imagenId = getResources().getIdentifier(receta.imagen, "drawable", getPackageName());
        imagen.setImageResource(imagenId);

        titulo.setText(receta.nombre);

        // Formatear ingredientes
        StringBuilder ingredientesText = new StringBuilder();
        for (String ing : receta.ingredientes) {
            ingredientesText.append("- ").append(ing).append("\n");
        }
        ingredientes.setText(ingredientesText.toString());

        // Formatear preparación
        StringBuilder preparacionText = new StringBuilder();
        for (int i = 0; i < receta.pasosPreparacion.size(); i++) {
            preparacionText.append(i+1).append(". ").append(receta.pasosPreparacion.get(i)).append("\n\n");
        }
        preparacion.setText(preparacionText.toString());

        // Configurar botón de retroceso
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }
}