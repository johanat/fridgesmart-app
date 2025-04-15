package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecetasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recetas);

        ConstraintLayout salmon = findViewById(R.id.receta1);
        ConstraintLayout tortitas = findViewById(R.id.receta2);
        ConstraintLayout pasta = findViewById(R.id.receta3);
        ConstraintLayout sopa = findViewById(R.id.receta4);

        ImageView flechaRetroceder = findViewById(R.id.backButton);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(RecetasActivity.this, PortadaPrincipal.class);
            finish();
        });

        salmon.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaSalmon.class);
            startActivity(receta);
        });

        tortitas.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaTortitas.class);
            startActivity(receta);
        });

        pasta.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaPasta.class);
            startActivity(receta);
        });

        sopa.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaSopa.class);
            startActivity(receta);
        });

    }
}