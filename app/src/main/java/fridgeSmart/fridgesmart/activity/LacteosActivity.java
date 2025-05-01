package fridgeSmart.fridgesmart.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.modelo.ItemTipoCarne;
import fridgeSmart.fridgesmart.ItemAdapter;
import fridgeSmart.fridgesmart.R;

public class LacteosActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lacteos);
        ImageView regresar = findViewById(R.id.backButton);
        FloatingActionButton btnBorrar = findViewById(R.id.btnEliminar);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ItemTipoCarne> itemTipoCarneList = new ArrayList<>();
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.leche2,"Leche",4,"leche","LACTEOS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.queso,"Queso",2,"queso","LACTEOS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.yogurt,"Yogurt",5,"yogurt","LACTEOS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.mantequilla,"Mantequilla",1,"mantequilla","LACTEOS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.helado,"Helados",2,"helados","LACTEOS"));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(LacteosActivity.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemTipoCarneList, new ItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(ItemTipoCarne item){

            }
        });
        recyclerView.setAdapter(adapter);
    }
}
