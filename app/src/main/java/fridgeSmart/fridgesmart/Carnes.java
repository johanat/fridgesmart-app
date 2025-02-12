package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Carnes extends GestionAlimentos{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carnes);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.carne_animada,"Carne",5));
        itemList.add(new Item(R.drawable.pollo,"Pollo",3));
        itemList.add(new Item(R.drawable.pescado,"Pescado",2));
        itemList.add(new Item(R.drawable.salchicha,"Embutidos",6));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(Carnes.this, GestionAlimentos.class);
            finish();
        });



       ItemAdapter adapter = new ItemAdapter(itemList);
       recyclerView.setAdapter(adapter);
    }
}
