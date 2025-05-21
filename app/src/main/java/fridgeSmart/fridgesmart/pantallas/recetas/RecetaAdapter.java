package fridgeSmart.fridgesmart.pantallas.recetas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fridgeSmart.fridgesmart.R;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private List<RecetaSugerida> listaRecetas;

    public RecetaAdapter(List<RecetaSugerida> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        RecetaSugerida receta = listaRecetas.get(position);
        holder.txtNombre.setText(receta.nombre);

        if (receta.ingredientesFaltantes.isEmpty()) {
            holder.txtFaltantes.setText("Tienes todos los ingredientes necesarios");
        } else {
            holder.txtFaltantes.setText("Te falta: " + String.join(", ", receta.ingredientesFaltantes));
        }
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtFaltantes;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreReceta);
            txtFaltantes = itemView.findViewById(R.id.txtIngredientesFaltantes);
        }
    }
}
