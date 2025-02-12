package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Verduras extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verduras);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.zanahorias, "Zanahorias", 5));
        itemList.add(new Item(R.drawable.apio, "Apio", 3));
        itemList.add(new Item(R.drawable.tomate, "Tomate", 2));
        itemList.add(new Item(R.drawable.pepino, "Pepino", 4));
        itemList.add(new Item(R.drawable.gisantes, "Guisantes", 2));
        itemList.add(new Item(R.drawable.pimientos, "Pimientos", 6));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(Verduras.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
