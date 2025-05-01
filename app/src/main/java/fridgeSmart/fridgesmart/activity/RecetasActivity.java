package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import fridgeSmart.fridgesmart.R;

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
            Intent retroceder = new Intent(RecetasActivity.this, PortadaPrincipalActivity.class);
            finish();
        });

        salmon.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaSalmonActivity.class);
            startActivity(receta);
        });

        tortitas.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaTortitasActivity.class);
            startActivity(receta);
        });

        pasta.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaPastaActivity.class);
            startActivity(receta);
        });

        sopa.setOnClickListener(view -> {
            Intent receta = new Intent(RecetasActivity.this, RecetaSopaActivity.class);
            startActivity(receta);
        });

    }
}