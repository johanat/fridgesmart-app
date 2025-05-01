package fridgeSmart.fridgesmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import fridgeSmart.fridgesmart.ItemAdapter;
import fridgeSmart.fridgesmart.modelo.ItemTipoCarne;
import fridgeSmart.fridgesmart.R;

public class CarnesActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carnes);
        ImageView regresar = findViewById(R.id.backButton);


        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ItemTipoCarne> itemTipoCarneCategoriaCarnes = new ArrayList<>();
        itemTipoCarneCategoriaCarnes.add(new ItemTipoCarne(R.drawable.carne_animada, "Carne", 5, "carne", "CARNES"));
        itemTipoCarneCategoriaCarnes.add(new ItemTipoCarne(R.drawable.pollo, "Pollo", 3, "pollo", "CARNES"));
        itemTipoCarneCategoriaCarnes.add(new ItemTipoCarne(R.drawable.pescado, "Pescado", 2, "pescado", "CARNES"));
        itemTipoCarneCategoriaCarnes.add(new ItemTipoCarne(R.drawable.salchicha, "Embutidos", 6, "embutidos", "CARNES"));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(CarnesActivity.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemTipoCarneCategoriaCarnes, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemTipoCarne item) {
                Intent intent = new Intent(CarnesActivity.this, DetalleActivity.class);
                startActivity(intent);
            }
        });
       recyclerView.setAdapter(adapter);
    }
}
