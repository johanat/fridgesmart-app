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

import fridgeSmart.fridgesmart.ItemTipoCarne;
import fridgeSmart.fridgesmart.ItemAdapter;
import fridgeSmart.fridgesmart.R;

public class VerdurasActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verduras);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ItemTipoCarne> itemTipoCarneList = new ArrayList<>();
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.zanahorias, "Zanahorias", 5,"zanahorias","VERDURAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.apio, "Apio", 3,"apio","VERDURAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.tomate, "Tomate", 2,"tomate","VERDURAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.pepino, "Pepino", 4,"pepino","VERDURAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.gisantes, "Guisantes", 2,"guisantes","VERDURAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.pimientos, "Pimientos", 6,"pimientos","VERDURAS"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(VerdurasActivity.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemTipoCarneList, new ItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(ItemTipoCarne item){
                showCustomDialog(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showCustomDialog(ItemTipoCarne itemTipoCarne) {
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
