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

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.Item;
import fridgeSmart.fridgesmart.ItemAdapter;
import fridgeSmart.fridgesmart.R;

public class FrutasActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fruta);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.mandarina, "Mandarinas", 4,"mandarina","FRUTAS"));
        itemList.add(new Item(R.drawable.platano, "Platanos", 5,"platanos","FRUTAS"));
        itemList.add(new Item(R.drawable.manzanas, "Manzanas", 0,"manzanas","FRUTAS"));
        itemList.add(new Item(R.drawable.uvas, "Uvas", 20,"uvas","FRUTAS"));
        itemList.add(new Item(R.drawable.naranja, "Naranjas", 3,"naranjas","FRUTAS"));
        itemList.add(new Item(R.drawable.durazno, "Durazno", 3,"durazno","FRUTAS"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(FrutasActivity.this, GestionAlimentos.class);
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
