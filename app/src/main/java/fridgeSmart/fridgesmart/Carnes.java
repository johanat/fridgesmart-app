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

public class Carnes extends GestionAlimentos{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carnes);
        ImageView regresar = findViewById(R.id.backButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.carne_animada,"Carne",5,"carne","CARNES"));
        itemList.add(new Item(R.drawable.pollo,"Pollo",3,"pollo","CARNES"));
        itemList.add(new Item(R.drawable.pescado,"Pescado",2,"pescado","CARNES"));
        itemList.add(new Item(R.drawable.salchicha,"Embutidos",6,"embutidos","CARNES"));

        regresar.setOnClickListener(view ->{
            Intent retroceder = new Intent(Carnes.this, GestionAlimentos.class);
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
        switch (item.getNombre()){
            case "carne":
                dialog.setContentView(R.layout.dialog_custom);
                break;
            case "pollo":
                dialog.setContentView(R.layout.dialog_custom_pollo);
                break;
            case "pescado":
                dialog.setContentView(R.layout.dialog_custom_pescado);
                break;
            case "embutidos":
                dialog.setContentView(R.layout.dialog_custom_embutidos);
                break;
            default:
                dialog.setContentView(R.layout.dialog_custom); // Un layout por defecto
                break;
        }

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
