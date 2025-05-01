package fridgeSmart.fridgesmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fridgeSmart.fridgesmart.modelo.TipoCarne;

public class TipoCarneAdapter extends RecyclerView.Adapter<TipoCarneAdapter.ViewHolder> {
    private List<TipoCarne> tipoCarneList;
    private OnItemClickListener listener;//Interfaz para manejar clicks

    public TipoCarneAdapter(List<TipoCarne> tipoCarneList, OnItemClickListener listener) {
        this.tipoCarneList = tipoCarneList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tipo_carne, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipoCarne tipoCarne = tipoCarneList.get(position);
        holder.imageView.setImageResource(tipoCarne.getImageId());
        holder.textViewTitle.setText(tipoCarne.getNombre());
        holder.textViewNumber.setText(String.valueOf(tipoCarne.getCantidad()));

        // Verificar si el item pertenece a la categoría de "Carnes"
        holder.itemView.setOnClickListener(v -> listener.onItemClick(tipoCarne));
    }

    @Override
    public int getItemCount() {
        return tipoCarneList.size();
    }

    //Interfaz para manejar los clicks en la flecha
    public interface OnItemClickListener{
        void onItemClick(TipoCarne tipoCarne);
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
