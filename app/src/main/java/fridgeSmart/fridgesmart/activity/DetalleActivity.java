package fridgeSmart.fridgesmart.activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import fridgeSmart.fridgesmart.CarneAdapter;
import fridgeSmart.fridgesmart.CarneItem;
import fridgeSmart.fridgesmart.R;

public class DetalleActivity extends AppCompatActivity {
    private List<CarneItem> carneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarneAdapter carneAdapter;
    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalle_carne_activity);

        linearLayout = findViewById(R.id.layoutBotones);
        recyclerView = findViewById(R.id.recyclerViewCarne);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Llenamos la lista de carnes (esto puede venir de cualquier fuente de datos)
        llenarListaDeCarnes();

        // Creamos el adaptador y lo asignamos al RecyclerView
        carneAdapter = new CarneAdapter(carneList, () -> {
            boolean algunoSeleccionado = false;
            for (CarneItem carne : carneList) {
                if (carne.isSelecionado()) {
                    algunoSeleccionado = true;
                    break;
                }
            }
            linearLayout.setVisibility(algunoSeleccionado ? LinearLayout.VISIBLE : LinearLayout.GONE);
        });
        recyclerView.setAdapter(carneAdapter);
    }

    // Método para llenar la lista de carnes
    private void llenarListaDeCarnes() {
        // como creo una lista de carne la misma que sera usada en el adapter y en el holder

        carneList.add(new CarneItem("Chuleta de Cerdo", 2.5));
        carneList.add(new CarneItem("Filete de Ternera", 3.0));
        carneList.add(new CarneItem("Lomo de Cerdo", 1.0));
        carneList.add(new CarneItem("Higado de ternera", 1.5));
        carneList.add(new CarneItem("Muslos de pollo", 2.0));
        carneList.add(new CarneItem("Pechuga de pollo", 1.5));
        carneList.add(new CarneItem("Calamar", 1.5));
        carneList.add(new CarneItem("Meluza", 2.5));
    }
}

