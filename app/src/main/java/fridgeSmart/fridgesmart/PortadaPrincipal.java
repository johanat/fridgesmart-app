package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PortadaPrincipal extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView iconoGestionAlimentos = findViewById(R.id.iconGestionAliementos);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        ImageView btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(view ->{
            drawerLayout.open();
        });

        iconoGestionAlimentos.setOnClickListener(view->{
            Intent intent = new Intent(PortadaPrincipal.this, GestionAlimentos.class);
            startActivity(intent);
        });
    }
}