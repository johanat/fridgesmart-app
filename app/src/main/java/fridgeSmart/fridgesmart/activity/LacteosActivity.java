package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.modelo.TipoCarne;
import fridgeSmart.fridgesmart.TipoCarneAdapter;
import fridgeSmart.fridgesmart.R;

public class LacteosActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lacteos);
        ImageView regresar = findViewById(R.id.backButton);
        FloatingActionButton btnBorrar = findViewById(R.id.btnEliminar);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TipoCarne> tipoCarneList = new ArrayList<>();
        tipoCarneList.add(new TipoCarne(R.drawable.leche2,4,"leche","LACTEOS"));
        tipoCarneList.add(new TipoCarne(R.drawable.queso,2,"queso","LACTEOS"));
        tipoCarneList.add(new TipoCarne(R.drawable.yogurt,5,"yogurt","LACTEOS"));
        tipoCarneList.add(new TipoCarne(R.drawable.mantequilla,1,"mantequilla","LACTEOS"));
        tipoCarneList.add(new TipoCarne(R.drawable.helado,2,"helados","LACTEOS"));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(LacteosActivity.this, GestionAlimentos.class);
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
