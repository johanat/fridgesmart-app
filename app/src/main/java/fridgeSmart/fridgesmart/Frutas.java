package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Frutas extends GestionAlimentos{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fruta);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.mandarina, "Mandarinas", 4));
        itemList.add(new Item(R.drawable.platano, "Platanos", 5));
        itemList.add(new Item(R.drawable.manzanas, "Manzanas", 0));
        itemList.add(new Item(R.drawable.uvas, "Uvas", 20));
        itemList.add(new Item(R.drawable.naranja, "Naranjas", 3));
        itemList.add(new Item(R.drawable.durazno, "Durazno", 3));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(Frutas.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
