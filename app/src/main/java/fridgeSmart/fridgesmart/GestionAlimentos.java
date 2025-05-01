package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GestionAlimentos extends PortadaPrincipal{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_alimentos);

        LinearLayout iconoCarne = findViewById(R.id.icon_Carnes);
        LinearLayout iconoLeche = findViewById(R.id.iconoLacteos);
        ImageView flechaRetroceder = findViewById(R.id.backButton);
        LinearLayout iconoFrutas = findViewById(R.id.iconoFrutas);
        LinearLayout iconoVerduras = findViewById(R.id.iconoVerduras);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(GestionAlimentos.this, PortadaPrincipal.class);
            finish();
        });
        iconoCarne.setOnClickListener(view -> {
            Intent intent = new Intent(GestionAlimentos.this, Carnes.class);
            startActivity(intent);
        });
        iconoLeche.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentos.this, Lacteos.class);
            startActivity(intent);
        });
        iconoFrutas.setOnClickListener( view ->{
            Intent intent = new Intent(GestionAlimentos.this, Frutas.class);
            startActivity(intent);
        });

        iconoVerduras.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentos.this, Verduras.class);
            startActivity(intent);
        });
    }
}
