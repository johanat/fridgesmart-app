package fridgeSmart.fridgesmart.pantallas.tipofruta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;
import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarne;
import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarneAdapter;
import fridgeSmart.fridgesmart.R;

public class TipoFrutaActivity extends GestionAlimentosActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tipo_fruta);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TipoCarne> tipoCarneList = new ArrayList<>();
        tipoCarneList.add(new TipoCarne(R.drawable.mandarina, 4,"mandarina","FRUTAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.platano, 5,"platanos","FRUTAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.manzanas,  0,"manzanas","FRUTAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.uvas, 20,"uvas","FRUTAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.naranja, 3,"naranjas","FRUTAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.durazno, 3,"durazno","FRUTAS"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(TipoFrutaActivity.this, GestionAlimentosActivity.class);
            finish();
        });

        TipoCarneAdapter adapter = new TipoCarneAdapter(tipoCarneList, new TipoCarneAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(TipoCarne item){
            }
        });
        recyclerView.setAdapter(adapter);
    }


}
