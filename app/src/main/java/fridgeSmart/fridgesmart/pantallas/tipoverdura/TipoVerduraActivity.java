package fridgeSmart.fridgesmart.pantallas.tipoverdura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarne;
import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarneAdapter;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;

public class TipoVerduraActivity extends GestionAlimentosActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tipo_verdura);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TipoCarne> tipoCarneList = new ArrayList<>();
        tipoCarneList.add(new TipoCarne(R.drawable.zanahorias,  5,"zanahorias","VERDURAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.apio,  3,"apio","VERDURAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.tomate,  2,"tomate","VERDURAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.pepino,  4,"pepino","VERDURAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.gisantes,  2,"guisantes","VERDURAS"));
        tipoCarneList.add(new TipoCarne(R.drawable.pimientos, 6,"pimientos","VERDURAS"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(TipoVerduraActivity.this, GestionAlimentosActivity.class);
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
