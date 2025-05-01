package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import fridgeSmart.fridgesmart.R;

public class PortadaPrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_portada_principal);

        LinearLayout iconoGestionAlimentos = findViewById(R.id.iconGestionAliementos);
        LinearLayout iconoControlTemperatura = findViewById(R.id.icono_temperatura);
        LinearLayout iconoRecetas = findViewById(R.id.icono_recetas);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        ImageView btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(view ->{
            drawerLayout.open();
        });

        iconoGestionAlimentos.setOnClickListener(view->{
            Intent intent = new Intent(PortadaPrincipalActivity.this, GestionAlimentos.class);
            startActivity(intent);
        });

        iconoControlTemperatura.setOnClickListener(view ->{
            Intent intent = new Intent(PortadaPrincipalActivity.this, ControlTemperaturasActivity.class);
            startActivity(intent);
        });

        iconoRecetas.setOnClickListener(view -> {
            Intent intent = new Intent(PortadaPrincipalActivity.this, RecetasActivity.class);
            startActivity(intent);
        });
    }
}