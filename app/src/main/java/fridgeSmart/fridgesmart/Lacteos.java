package fridgeSmart.fridgesmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Lacteos extends GestionAlimentos{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lacteos);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.leche2,"Leche",4));
        itemList.add(new Item(R.drawable.queso,"Queso",2));
        itemList.add(new Item(R.drawable.yogurt,"Yogurt",5));
        itemList.add(new Item(R.drawable.mantequilla,"Mantequilla",1));
        itemList.add(new Item(R.drawable.helado,"Helados",2));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(Lacteos.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}
