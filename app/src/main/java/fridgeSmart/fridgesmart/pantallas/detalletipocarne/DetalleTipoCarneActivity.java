package fridgeSmart.fridgesmart.pantallas.detalletipocarne;

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

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.principal.PrincipalActivity;

public class DetalleTipoCarneActivity extends AppCompatActivity {
    private List<Carne> carneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DetalleTipoCarneAdapter detalleTipoCarneAdapter;
    private LinearLayout linearLayout;
    private FloatingActionButton btnEliminar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_tipo_carne);

        recyclerView = findViewById(R.id.recyclerViewCarne);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnEliminar = findViewById(R.id.btnEliminar);

        // Llenamos la lista de carnes (esto puede venir de cualquier fuente de datos)
        Intent intent = getIntent();
        String tipoCarne = intent.getStringExtra("tipoCarne");
        Log.d("DetalleActivity", "Tipo de carne recibido: " + tipoCarne);

        List<Carne> carneList = PrincipalActivity.repositorio.obtenerCarnes();
        List<Carne> listaFiltrada = getListaDeTipo(carneList, tipoCarne);

        // Creamos el adaptador y lo asignamos al RecyclerView
        detalleTipoCarneAdapter = new DetalleTipoCarneAdapter(listaFiltrada, btnEliminar, () -> {
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

    private List<Carne> getListaDeTipo(List<Carne> carneList, String tipo) {
        List<Carne> listaFiltrada = new ArrayList<>();
        for (Carne carne : carneList) {
            if (carne.getTipo().equalsIgnoreCase(tipo)) {
                listaFiltrada.add(carne);
            }
        }
        return listaFiltrada;
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



