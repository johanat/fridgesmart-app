package fridgeSmart.fridgesmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<ItemTipoCarne> itemTipoCarneList;
    private OnItemClickListener listener;//Interfaz para manejar clicks

    public ItemAdapter(List<ItemTipoCarne> itemTipoCarneList, OnItemClickListener listener) {
        this.itemTipoCarneList = itemTipoCarneList;
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
        ItemTipoCarne itemTipoCarne = itemTipoCarneList.get(position);
        holder.imageView.setImageResource(itemTipoCarne.getImageResId());
        holder.textViewTitle.setText(itemTipoCarne.getTitle());
        holder.textViewNumber.setText(String.valueOf(itemTipoCarne.getNumber()));

        // Verificar si el item pertenece a la categoría de "Carnes"
        if("CARNES".equalsIgnoreCase(itemTipoCarne.getCategory())){
            holder.arrowButton.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> listener.onItemClick(itemTipoCarne));
        }else{
            holder.arrowButton.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return itemTipoCarneList.size();
    }

    //Interfaz para manejar los clicks en la flecha
    public interface OnItemClickListener{
        void onItemClick(ItemTipoCarne itemTipoCarne);
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
