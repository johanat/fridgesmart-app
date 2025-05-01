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

public class FrutasActivity extends GestionAlimentos {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fruta);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<ItemTipoCarne> itemTipoCarneList = new ArrayList<>();
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.mandarina, "Mandarinas", 4,"mandarina","FRUTAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.platano, "Platanos", 5,"platanos","FRUTAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.manzanas, "Manzanas", 0,"manzanas","FRUTAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.uvas, "Uvas", 20,"uvas","FRUTAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.naranja, "Naranjas", 3,"naranjas","FRUTAS"));
        itemTipoCarneList.add(new ItemTipoCarne(R.drawable.durazno, "Durazno", 3,"durazno","FRUTAS"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(FrutasActivity.this, GestionAlimentos.class);
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
