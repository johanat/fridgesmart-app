package fridgeSmart.fridgesmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private OnItemClickListener listener;//Interfaz para manejar clicks

    public ItemAdapter(List<Item> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewNumber.setText(String.valueOf(item.getNumber()));

        // Verificar si el item pertenece a la categoría de "Carnes"
        if("CARNES".equalsIgnoreCase(item.getCategory())){
            holder.arrowButton.setVisibility(View.VISIBLE);
            //Detectar clic en la flecha
            holder.arrowButton.setOnClickListener(v -> listener.onItemClick(item));
        }else{
            holder.arrowButton.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    //Interfaz para manejar los clicks en la flecha
    public interface OnItemClickListener{
        void onItemClick(Item item);
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
