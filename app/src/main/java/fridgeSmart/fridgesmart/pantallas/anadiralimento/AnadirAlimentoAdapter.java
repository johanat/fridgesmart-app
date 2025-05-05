package fridgeSmart.fridgesmart.pantallas.anadiralimento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;

public class AnadirAlimentoAdapter extends RecyclerView.Adapter<AnadirAlimentoAdapter.ViewHolder> {

    private List<AlimentoDb> alimentoEntityList;
    private OnSelectionChangedListener selectionChangedListener;

    public interface OnSelectionChangedListener {
        void onSelectionChanged();
    }

    public AnadirAlimentoAdapter(List<AlimentoDb> alimentoEntityList) {
        this.alimentoEntityList = alimentoEntityList;
    }

    //Extaer el listener fuera
    public void setOnSelectionChangedListener(OnSelectionChangedListener listener) {
        this.selectionChangedListener = listener;
    }

    public List<Integer> getSelectedIds() {
        List<Integer> selected = new ArrayList<>();
        for (AlimentoDb a : alimentoEntityList) {
            if (a.selecionado) {
                selected.add(a.id);
            }
        }
        return selected;
    }

    public List<AlimentoDb> getAlimentosSelecionados() {
        List<AlimentoDb> seleccionados = new ArrayList<>();
        for (AlimentoDb a : alimentoEntityList) {
            if (a.selecionado) {
                seleccionados.add(a);
            }
        }
        return seleccionados;
    }

    @NonNull
    @Override
    public AnadirAlimentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anadir_alimento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnadirAlimentoAdapter.ViewHolder holder, int position) {
        AlimentoDb AlimentoDb = alimentoEntityList.get(position);

        holder.textName.setText(AlimentoDb.nombre);
        holder.imageView.setImageResource(AlimentoDb.imagenId);
        holder.checkBox.setChecked(AlimentoDb.selecionado);

        // Mostrar u ocultar campos adicionales según el estado
        holder.extraFieldsLayout.setVisibility(AlimentoDb.selecionado ? View.VISIBLE : View.GONE);

        // Setear campos si ya tenían datos
        String cantidad = AlimentoDb.cantidad > 0 ? String.valueOf(AlimentoDb.cantidad) : "";
        String kilos = AlimentoDb.kilos > 0 ? String.valueOf(AlimentoDb.kilos) : "";
        holder.editCantidad.setText(cantidad);
        holder.editKilos.setText(kilos);
        holder.editFecha.setText(AlimentoDb.fechaCaducidad);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AlimentoDb.selecionado = isChecked;
            holder.extraFieldsLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            selectionChangedListener.onSelectionChanged();
        });

        // Listeners para actualizar los datos del objeto
        holder.editCantidad.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AlimentoDb.cantidad = s.length() > 0 ? Integer.parseInt(s.toString()) : 0;
            }
        });

        holder.editKilos.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AlimentoDb.kilos = s.length() > 0 ? Double.parseDouble(s.toString()) : 0.0;
            }
        });

        holder.editFecha.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AlimentoDb.fechaCaducidad = s.toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alimentoEntityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView imageView;
        TextView textName;
        LinearLayout extraFieldsLayout;
        EditText editCantidad, editKilos, editFecha;

        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkBox);
            imageView = view.findViewById(R.id.imageView);
            textName = view.findViewById(R.id.textName);
            extraFieldsLayout = view.findViewById(R.id.extraFieldsLayout);
            editCantidad = view.findViewById(R.id.editCantidad);
            editKilos = view.findViewById(R.id.editKilos);
            editFecha = view.findViewById(R.id.editFecha);
        }
    }
}
