package fridgeSmart.fridgesmart.pantallas.anadiralimento;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;

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
        AlimentoDb alimento  = alimentoEntityList.get(position);

        holder.textName.setText(alimento.nombre);
        holder.imageView.setImageResource(alimento.imagenId);
        holder.checkBox.setChecked(alimento.selecionado);

        // Mostrar u ocultar campos adicionales según el estado
        holder.extraFieldsLayout.setVisibility(alimento.selecionado ? View.VISIBLE : View.GONE);

        // Configurar campos según categoría
        if (CATEGORIA_CARNE.equalsIgnoreCase(alimento.categoria)) {
            holder.layoutCarne.setVisibility(View.VISIBLE);
            holder.layoutOtros.setVisibility(View.GONE);

            // Setear valores existentes
            String kilos = alimento.kilos > 0 ? String.valueOf(alimento.kilos) : "";
            holder.editKilos.setText(kilos);
            holder.editFecha.setText(alimento.fechaCaducidad != null ? alimento.fechaCaducidad : "");
        } else {
            holder.layoutCarne.setVisibility(View.GONE);
            holder.layoutOtros.setVisibility(View.VISIBLE);

            // Setear valores existentes
            String cantidad = alimento.cantidad > 0 ? String.valueOf(alimento.cantidad) : "";
            holder.editCantidad.setText(cantidad);
            holder.editFechaOtros.setText(alimento.fechaCaducidad != null ? alimento.fechaCaducidad : "");
        }

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            alimento.selecionado = isChecked;
            holder.extraFieldsLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (selectionChangedListener != null) {
                selectionChangedListener.onSelectionChanged();
            }
        });

        // Listeners para actualizar los datos
        holder.editKilos.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    alimento.kilos = s.length() > 0 ? Double.parseDouble(s.toString()) : 0.0;
                } catch (NumberFormatException e) {
                    alimento.kilos = 0.0;
                }
                if (selectionChangedListener != null) {
                    selectionChangedListener.onSelectionChanged();
                }
            }
        });

        holder.editCantidad.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    alimento.cantidad = s.length() > 0 ? Integer.parseInt(s.toString()) : 0;
                } catch (NumberFormatException e) {
                    alimento.cantidad = 0;
                }
                if (selectionChangedListener != null) {
                    selectionChangedListener.onSelectionChanged();
                }
            }
        });

        holder.editFecha.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alimento.fechaCaducidad = s.toString();
            }
        });

        holder.editFechaOtros.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alimento.fechaCaducidad = s.toString();
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
        LinearLayout layoutCarne;
        LinearLayout layoutOtros;
        EditText editCantidad, editKilos, editFecha, editFechaOtros;

        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkBox);
            imageView = view.findViewById(R.id.imageView);
            textName = view.findViewById(R.id.textName);
            extraFieldsLayout = view.findViewById(R.id.extraFieldsLayout);
            layoutCarne = view.findViewById(R.id.layoutCarne);
            layoutOtros = view.findViewById(R.id.layoutOtros);
            editCantidad = view.findViewById(R.id.editCantidad);
            editKilos = view.findViewById(R.id.editKilos);
            editFecha = view.findViewById(R.id.editFecha);
            editFechaOtros = view.findViewById(R.id.editFechaOtros);
        }
    }
}
