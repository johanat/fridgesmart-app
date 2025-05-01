package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;

import fridgeSmart.fridgesmart.R;

public class ControlTemperaturasActivity extends PortadaPrincipalActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_control_temperatura);

        ImageView flechaRetroceder = findViewById(R.id.backButton);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(ControlTemperaturasActivity.this, PortadaPrincipalActivity.class);
            finish();
        });

    }
}
