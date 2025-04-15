package fridgeSmart.fridgesmart;

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
        itemList.add(new Item(R.drawable.leche2,"Leche",4,"leche","LACTEOS"));
        itemList.add(new Item(R.drawable.queso,"Queso",2,"queso","LACTEOS"));
        itemList.add(new Item(R.drawable.yogurt,"Yogurt",5,"yogurt","LACTEOS"));
        itemList.add(new Item(R.drawable.mantequilla,"Mantequilla",1,"mantequilla","LACTEOS"));
        itemList.add(new Item(R.drawable.helado,"Helados",2,"helados","LACTEOS"));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(Lacteos.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemList, new ItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Item item){
                showCustomDialog(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showCustomDialog(Item item) {
        // Crear el diálogo
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        // Oscurecer el fondo
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.dimAmount = 0.5f; // Ajusta la opacidad del fondo oscuro
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(lp);

        // Botón para cerrar el diálogo
        ImageButton btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        // Mostrar el diálogo
        dialog.show();
    }
}
