package fridgeSmart.fridgesmart.pantallas.tipolacteos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarne;
import fridgeSmart.fridgesmart.pantallas.tipocarne.TipoCarneAdapter;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;

public class TipoLacteoActivity extends GestionAlimentosActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tipo_lacteo);
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
            Intent retroceder = new Intent(TipoLacteoActivity.this, GestionAlimentosActivity.class);
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
