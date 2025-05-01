package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fridgeSmart.fridgesmart.R;

public class GestionAlimentos extends PortadaPrincipalActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_alimentos);

        LinearLayout iconoCarne = findViewById(R.id.icon_Carnes);
        LinearLayout iconoLeche = findViewById(R.id.iconoLacteos);
        ImageView flechaRetroceder = findViewById(R.id.backButton);
        LinearLayout iconoFrutas = findViewById(R.id.iconoFrutas);
        LinearLayout iconoVerduras = findViewById(R.id.iconoVerduras);


        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(GestionAlimentos.this, PortadaPrincipalActivity.class);
            finish();
        });
        iconoCarne.setOnClickListener(view -> {
            Intent intent = new Intent(GestionAlimentos.this, CarnesActivity.class);
            startActivity(intent);
        });
        iconoLeche.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentos.this, LacteosActivity.class);
            startActivity(intent);
        });
        iconoFrutas.setOnClickListener( view ->{
            Intent intent = new Intent(GestionAlimentos.this, FrutasActivity.class);
            startActivity(intent);
        });

        iconoVerduras.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentos.this, VerdurasActivity.class);
            startActivity(intent);
        });
    }
}
