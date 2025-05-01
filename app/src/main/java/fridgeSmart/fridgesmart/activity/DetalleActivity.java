package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.modelo.Carne;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.DetalleTipoCarneAdapter;

public class DetalleActivity extends AppCompatActivity {
    private List<Carne> carneList = new ArrayList<>();
    private List<Carne> listaSelecccionados = new ArrayList<>();
    private RecyclerView recyclerView;
    private DetalleTipoCarneAdapter detalleTipoCarneAdapter;
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
        Intent intent = getIntent();
        String tipoCarne = intent.getStringExtra("tipoCarne");
        Log.d("DetalleActivity", "Tipo de carne recibido: " + tipoCarne);


        // Creamos el adaptador y lo asignamos al RecyclerView
        detalleTipoCarneAdapter = new DetalleTipoCarneAdapter(carneList, btnEliminar, () -> {
            boolean algunoSeleccionado = false;
            for (Carne carne : carneList) {
                if (carne.isSelecionado()) {
                    algunoSeleccionado = true;
                    break;
                }
            }
        });
        btnEliminar.setOnClickListener(v -> {
            onBtnEliminarClick();
        });
        recyclerView.setAdapter(detalleTipoCarneAdapter);


    }

    // Método para llenar la lista de carnes
    private void llenarListaDeCarnes() {
        // como creo una lista de carne la misma que sera usada en el adapter y en el holder

        carneList.add(new Carne("Carne", "Chuleta de Cerdo", 2.5));
        carneList.add(new Carne("Carne", "Filete de Ternera", 3.0));
        carneList.add(new Carne("Carne", "Lomo de Cerdo", 1.0));
        carneList.add(new Carne("Carne", "Higado de ternera", 1.5));
        carneList.add(new Carne("Pollo", "Muslos de pollo", 2.0));
        carneList.add(new Carne("Pollo", "Pechuga de pollo", 1.5));
        carneList.add(new Carne("Pollo", "Calamar", 1.5));
        carneList.add(new Carne("Pescado", "Meluza", 2.5));
        carneList.add(new Carne("Pescado", "Salmón", 3.0));

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
                    detalleTipoCarneAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

}



