package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GestionAlimentos extends PortadaPrincipal{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_alimentos);

        LinearLayout iconoCarne = findViewById(R.id.icon_Carnes);
        LinearLayout iconoLeche = findViewById(R.id.iconoLacteos);
        ImageView flechaRetroceder = findViewById(R.id.backButton);

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

    }
}
