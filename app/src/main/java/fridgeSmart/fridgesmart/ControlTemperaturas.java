package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;

public class ControlTemperaturas extends PortadaPrincipal{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.control_temperatura);

        ImageView flechaRetroceder = findViewById(R.id.backButton);

        flechaRetroceder.setOnClickListener(view ->{
            Intent retroceder = new Intent(ControlTemperaturas.this, PortadaPrincipal.class);
            finish();
        });

    }
}
