package fridgeSmart.fridgesmart.pantallas.subcategoriacarne;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.alimentos.AlimentosActivity;
import fridgeSmart.fridgesmart.pantallas.alimentos.Carne;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;

public class SubcategoriaCarneActivity extends GestionAlimentosActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Carne> listaFiltrado = (List<Carne>) getIntent().getSerializableExtra("listaCarne");

        setContentView(R.layout.activity_subcategoria_carne);

        ImageView regresar = findViewById(R.id.backButton);
        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Recibe la lista de carne desde la otra activity
        List<Carne> listaRecibida = (List<Carne>) getIntent().getSerializableExtra("listaCarne");


        List<SubcategoriaCarneConContador> subcategoriaCarneCategoriaCarnes = ((MyApp) getApplication()).repositorio.obtenerSubcategoriaCarneConContador();

        regresar.setOnClickListener(view ->{
            finish();
        });

        SubcategoriaCarneAdapter adapter = new SubcategoriaCarneAdapter(
                subcategoriaCarneCategoriaCarnes,
                this,
                new SubcategoriaCarneAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(SubcategoriaCarneConContador subcategoriaCarne) {
                        Intent intent = new Intent(SubcategoriaCarneActivity.this, AlimentosActivity.class);
                        intent.putExtra(CATEGORIA, CATEGORIA_CARNE);
                        intent.putExtra(SUBCATEGORIA_CARNE,subcategoriaCarne.subcategoria);
                        startActivity(intent);
                }
        });
       recyclerView.setAdapter(adapter);
    }
}
