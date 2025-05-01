package fridgeSmart.fridgesmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class TipoCarneAdapter extends RecyclerView.Adapter<TipoCarneAdapter.CarneViewHolder> {
    private List<CarneItem> carneList;
    public EnCambioSeleccionCarneEscuchador listener;
    public FloatingActionButton btnFlotanteEliminar;

    public TipoCarneAdapter(List<CarneItem> carneList, FloatingActionButton btnFlotanteEliminar, EnCambioSeleccionCarneEscuchador listener) {
        this.carneList = carneList;
        this.listener = listener;
        this.btnFlotanteEliminar = btnFlotanteEliminar;
    }

    @NonNull
    @Override
    public CarneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle, parent, false);
        return new CarneViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarneViewHolder holder, int position) {
        CarneItem carne = carneList.get(position);
        holder.nombre.setText(carne.getNombre());
        holder.kilos.setText(carne.getKilos() + " kg");


        // Desvincular listeners antiguos
        holder.checkBox.setOnCheckedChangeListener(null);

        // Marcar checkbox según estado actual
        holder.checkBox.setChecked(carne.isSelecionado());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            carne.setSelecionado(isChecked);
            if (listener != null) {
                listener.onCambioSeleccionCarne();
                btnFlotanteEliminar.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }

        });

    }

    @Override
    public int getItemCount() {
        return carneList.size();

    }

    public static class CarneViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView nombre, kilos;

        public CarneViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            nombre = itemView.findViewById(R.id.textNombre);
            kilos = itemView.findViewById(R.id.textKilos);
        }
    }

}



