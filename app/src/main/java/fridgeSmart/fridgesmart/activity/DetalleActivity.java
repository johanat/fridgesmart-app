package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import fridgeSmart.fridgesmart.CarneItem;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.TipoCarneAdapter;

public class DetalleActivity extends AppCompatActivity {
    private List<CarneItem> carneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TipoCarneAdapter tipoCarneAdapter;
    private LinearLayout linearLayout;
    private FloatingActionButton btnEliminar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detalle_carne_activity);


        recyclerView = findViewById(R.id.recyclerViewCarne);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnEliminar = findViewById(R.id.btnEliminar);


        // Llenamos la lista de carnes (esto puede venir de cualquier fuente de datos)
        llenarListaDeCarnes();





        // Creamos el adaptador y lo asignamos al RecyclerView
        tipoCarneAdapter = new TipoCarneAdapter(carneList, btnEliminar, () -> {
            boolean algunoSeleccionado = false;
            for (CarneItem carne : carneList) {
                if (carne.isSelecionado()) {
                    algunoSeleccionado = true;
                    break;
                }
            }
        });
        btnEliminar.setOnClickListener(v -> {
            onBtnEliminarClick();
        });
        recyclerView.setAdapter(tipoCarneAdapter);


    }

    // Método para llenar la lista de carnes
    private void llenarListaDeCarnes() {
        // como creo una lista de carne la misma que sera usada en el adapter y en el holder

        carneList.add(new CarneItem("Carne", "Chuleta de Cerdo", 2.5));
        carneList.add(new CarneItem("Carne", "Filete de Ternera", 3.0));
        carneList.add(new CarneItem("Carne", "Lomo de Cerdo", 1.0));
        carneList.add(new CarneItem("Carne", "Higado de ternera", 1.5));
        carneList.add(new CarneItem("Pollo", "Muslos de pollo", 2.0));
        carneList.add(new CarneItem("Pollo", "Pechuga de pollo", 1.5));
        carneList.add(new CarneItem("Pollo", "Calamar", 1.5));
        carneList.add(new CarneItem("Pescado", "Meluza", 2.5));
        carneList.add(new CarneItem("Pescado", "Salmón", 3.0));
    }

    // Método para manejar el botón de aceptar
    private void onBtnEliminarClick() {
        //crear un Dialogo para borrar los elementos seleccionados
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Estás seguro de que deseas eliminar los elementos seleccionados?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Eliminar los elementos seleccionados
                    for (int i = carneList.size() - 1; i >= 0; i--) {
                        if (carneList.get(i).isSelecionado()) {
                            carneList.remove(i);
                        }
                    }
                    tipoCarneAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }
}



