package fridgeSmart.fridgesmart.pantallas.alimentos;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.pantallas.anadiralimento.AnadirAlimentosActivity;

public class AlimentosActivity extends AppCompatActivity {
    private List<Carne> carneList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlimentosAdapter alimentosAdapter;
    private FloatingActionButton btnEliminar;
    private FloatingActionButton btnAgregar;
    private FloatingActionButton btnModificar;

    private String categoriaAlimento;
    private String subcategoriaCarne; // Solo se usará si es categoría carne


    private List<AlimentoDb> alimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alimentos);


        recyclerView = findViewById(R.id.recyclerViewCarne);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnEliminar = findViewById(R.id.btnEliminar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnModificar = findViewById(R.id.btnModificar);


        // Llenamos la lista de carnes (esto puede venir de cualquier fuente de datos)
        Intent intent = getIntent();
        categoriaAlimento = intent.getStringExtra(CATEGORIA);

        //Escuchar botones flotantes
        btnEliminar.setOnClickListener(v -> {
            onBtnEliminarClick();
        });
        btnAgregar.setOnClickListener(v -> {
            onBtnAgregarClick();
        });
        btnModificar.setOnClickListener(v -> {
            onBtnModificarClick();
        });

        if (categoriaAlimento.equals(CATEGORIA_CARNE)) {
            subcategoriaCarne = intent.getStringExtra(SUBCATEGORIA_CARNE);
            ((MyApp) getApplication()).repositorio.getCarnesDeGrupo(subcategoriaCarne).observe(this, alimentos -> {
                //Alimentos es de una categoria especifica (ya está filtrada)
                this.alimentos = alimentos;
                mostrarAlimentos(alimentos);
            });
        } else {
            ((MyApp) getApplication()).repositorio.getAlimentosDeCategoria(categoriaAlimento).observe(this, alimentos -> {
                //Alimentos es de una categoria especifica (ya está filtrada)
                this.alimentos = alimentos;
                mostrarAlimentos(alimentos);
            });
        }
    }

    private void onBtnModificarClick() {
        alimentosAdapter.editarItemSeleccionado();
    }

    private void onBtnAgregarClick() {
        Intent intent = new Intent(this, AnadirAlimentosActivity.class);
        intent.putExtra(CATEGORIA,categoriaAlimento);
        if(CATEGORIA_CARNE.equals(categoriaAlimento) && subcategoriaCarne != null){
            intent.putExtra(SUBCATEGORIA_CARNE,subcategoriaCarne);
        }
        startActivity(intent);
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

    // Método para manejar el botón de eliminar
    private void onBtnEliminarClick() {
        //crear un Dialogo para borrar los elementos seleccionados
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Estás seguro de que deseas eliminar los elementos seleccionados?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Eliminar los elementos seleccionados
                    for (int i = alimentos.size() - 1; i >= 0; i--) {
                        if (alimentos.get(i).selecionado) {
                            ((MyApp) getApplication()).repositorio.borrarAlimento(alimentos.get(i).id);
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void mostrarAlimentos(List<AlimentoDb> alimentoEntities) {
        // Creamos el adaptador y lo asignamos al RecyclerView
        alimentosAdapter = new AlimentosAdapter(alimentoEntities,
                () -> { //Cuando se selecciona un elemento

                    //Ver si hay algun elemento seleccionado en la lista alimentos
                    int itemsSeleccionados = 0;
                    for (AlimentoDb alimento : alimentoEntities) {
                        if (alimento.selecionado) {
                            itemsSeleccionados++;
                        }
                    }

                    //Mostrar u ocultar el boton de modificar
                    if (itemsSeleccionados == 1) {
                        btnModificar.setVisibility(View.VISIBLE);
                    } else {
                        btnModificar.setVisibility(View.GONE);
                    }

                    //Mostar u ocultar el boton de eliminar
                    if (itemsSeleccionados > 0) {
                        btnEliminar.setVisibility(View.VISIBLE);
                    } else {
                        btnEliminar.setVisibility(View.GONE);
                    }
                },
                (alimento) -> { //Accion al guardar el alimento
                    ((MyApp) getApplication()).repositorio.modificarAlimento(alimento.id,
                            alimento.cantidad,
                            alimento.kilos,
                            alimento.fechaCaducidad);
                    alimentosAdapter.dejarDeEditarElementoSeleccionado();
                });
        recyclerView.setAdapter(alimentosAdapter);
    }

}



