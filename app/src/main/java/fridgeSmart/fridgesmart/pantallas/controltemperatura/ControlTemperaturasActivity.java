package fridgeSmart.fridgesmart.pantallas.controltemperatura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;

public class ControlTemperaturasActivity extends PrincipalActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_control_temperatura);

        ImageView flechaRetroceder = findViewById(R.id.backButton);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(ControlTemperaturasActivity.this, PrincipalActivity.class);
            finish();
        });

    }
}
