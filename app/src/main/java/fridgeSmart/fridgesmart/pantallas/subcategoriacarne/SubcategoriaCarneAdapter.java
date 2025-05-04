package fridgeSmart.fridgesmart.pantallas.subcategoriacarne;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fridgeSmart.fridgesmart.R;

public class SubcategoriaCarneAdapter extends RecyclerView.Adapter<SubcategoriaCarneAdapter.ViewHolder> {
    private List<SubcategoriaCarne> subcategoriaCarneList;
    private OnItemClickListener listener;//Interfaz para manejar clicks

    public SubcategoriaCarneAdapter(List<SubcategoriaCarne> subcategoriaCarneList, OnItemClickListener listener) {
        this.subcategoriaCarneList = subcategoriaCarneList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subcategoria_carne, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubcategoriaCarne subcategoriaCarne = subcategoriaCarneList.get(position);
        holder.imageView.setImageResource(subcategoriaCarne.getImageId());
        holder.textViewTitle.setText(subcategoriaCarne.getNombre());
        holder.textViewNumber.setText(String.valueOf(subcategoriaCarne.getCantidad()));

        // Verificar si el item pertenece a la categoría de "Carnes"
        holder.itemView.setOnClickListener(v -> listener.onItemClick(subcategoriaCarne));
    }

    @Override
    public int getItemCount() {
        return subcategoriaCarneList.size();
    }

    //Interfaz para manejar los clicks en la flecha
    public interface OnItemClickListener{
        void onItemClick(SubcategoriaCarne subcategoriaCarne);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,arrowButton;
        TextView textViewTitle, textViewNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textView);
            textViewNumber = itemView.findViewById(R.id.numeroView);
            arrowButton = itemView.findViewById(R.id.flechaInformacion);
        }
    }
}
