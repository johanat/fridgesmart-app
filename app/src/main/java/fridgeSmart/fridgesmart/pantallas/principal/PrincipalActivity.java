package fridgeSmart.fridgesmart.pantallas.principal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.recetas.RecetasActivity;
import fridgeSmart.fridgesmart.pantallas.controltemperatura.ControlTemperaturasActivity;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;
import fridgeSmart.fridgesmart.comun.repositorio.Repositorio;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import fridgeSmart.fridgesmart.notificaciones.CaducidadWorker;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
public class PrincipalActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);


        // Pedimos permiso POST_NOTIFICATIONS si es Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            }
        }/*
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (requestCode == 1001) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show();
                } else {
                    // Permiso denegado
                    Toast.makeText(this, "Las notificaciones estarán desactivadas", Toast.LENGTH_SHORT).show();
                }
            }
        }
*/

/*
        // En PrincipalActivity.onCreate():
        OneTimeWorkRequest notificacionTest =
                new OneTimeWorkRequest.Builder(CaducidadWorker.class).build();
        WorkManager.getInstance(this).enqueue(notificacionTest);*/

        PeriodicWorkRequest notificacionDiaria =
                new PeriodicWorkRequest.Builder(CaducidadWorker.class, 24, TimeUnit.HOURS)
                        .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "NotificacionCaducidad",
                ExistingPeriodicWorkPolicy.KEEP,  // Mantiene el trabajo existente si ya está programado
                notificacionDiaria
        );

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
            Intent intent = new Intent(PrincipalActivity.this, GestionAlimentosActivity.class);
            startActivity(intent);
        });

        iconoControlTemperatura.setOnClickListener(view ->{
            Intent intent = new Intent(PrincipalActivity.this, ControlTemperaturasActivity.class);
            startActivity(intent);
        });

        iconoRecetas.setOnClickListener(view -> {
            Intent intent = new Intent(PrincipalActivity.this, RecetasActivity.class);
            startActivity(intent);
        });
    }
}