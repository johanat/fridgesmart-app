package fridgeSmart.fridgesmart.pantallas.recetasdetalle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.recetas.RecetasActivity;

public class RecetaTortitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receta_tortitas);

        ImageView flechaRetroceder = findViewById(R.id.backButton);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(RecetaTortitasActivity.this, RecetasActivity.class);
            finish();
        });

    }
}