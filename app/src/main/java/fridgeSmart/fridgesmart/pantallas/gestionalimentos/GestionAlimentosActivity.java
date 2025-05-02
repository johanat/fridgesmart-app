package fridgeSmart.fridgesmart.pantallas.gestionalimentos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.tipolacteos.TipoLacteoActivity;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;
import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarneActivity;
import fridgeSmart.fridgesmart.pantallas.tipoverdura.TipoVerduraActivity;
import fridgeSmart.fridgesmart.pantallas.tipofruta.TipoFrutaActivity;

public class GestionAlimentosActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_alimentos);

        LinearLayout iconoCarne = findViewById(R.id.icon_Carnes);
        LinearLayout iconoLeche = findViewById(R.id.iconoLacteos);
        ImageView flechaRetroceder = findViewById(R.id.backButton);
        LinearLayout iconoFrutas = findViewById(R.id.iconoFrutas);
        LinearLayout iconoVerduras = findViewById(R.id.iconoVerduras);


        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(GestionAlimentosActivity.this, PrincipalActivity.class);
            finish();
        });
        iconoCarne.setOnClickListener(view -> {
            Intent intent = new Intent(GestionAlimentosActivity.this, TipoCarneActivity.class);
            startActivity(intent);
        });
        iconoLeche.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentosActivity.this, TipoLacteoActivity.class);
            startActivity(intent);
        });
        iconoFrutas.setOnClickListener( view ->{
            Intent intent = new Intent(GestionAlimentosActivity.this, TipoFrutaActivity.class);
            startActivity(intent);
        });

        iconoVerduras.setOnClickListener(view ->{
            Intent intent = new Intent(GestionAlimentosActivity.this, TipoVerduraActivity.class);
            startActivity(intent);
        });
    }
}
