package fridgeSmart.fridgesmart.pantallas.tipocarne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.pantallas.detalletipocarne.Carne;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.detalletipocarne.DetalleTipoCarneActivity;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;

public class TipoCarneActivity extends GestionAlimentosActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Carne> listaFiltrado = (List<Carne>) getIntent().getSerializableExtra("listaCarne");

        setContentView(R.layout.activity_tipo_carne);
        ImageView regresar = findViewById(R.id.backButton);


        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Recibe la lista de carne desde la otra activity
        List<Carne> listaRecibida = (List<Carne>) getIntent().getSerializableExtra("listaCarne");


        List<TipoCarne> tipoCarneCategoriaCarnes = new ArrayList<>();
        tipoCarneCategoriaCarnes.add(new TipoCarne(R.drawable.carne_animada,  5, "carne", "CARNES"));
        tipoCarneCategoriaCarnes.add(new TipoCarne(R.drawable.pollo,  3, "pollo", "POLLO"));
        tipoCarneCategoriaCarnes.add(new TipoCarne(R.drawable.pescado, 2, "pescado", "PESCADO"));
        tipoCarneCategoriaCarnes.add(new TipoCarne(R.drawable.salchicha,  6, "embutidos", "EMBUTIDOS"));

        regresar.setOnClickListener(view ->{
            finish();
        });

        TipoCarneAdapter adapter = new TipoCarneAdapter(tipoCarneCategoriaCarnes, new TipoCarneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TipoCarne tipoCarne) {
                Intent intent = new Intent(TipoCarneActivity.this, DetalleTipoCarneActivity.class);
                intent.putExtra("tipoCarne", tipoCarne.getTipo());
                startActivity(intent);
            }
        });
       recyclerView.setAdapter(adapter);
    }
}
