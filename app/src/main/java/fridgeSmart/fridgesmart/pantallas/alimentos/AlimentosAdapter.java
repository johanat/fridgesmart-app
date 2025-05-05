package fridgeSmart.fridgesmart.pantallas.alimentos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;

public class AlimentosAdapter extends RecyclerView.Adapter<AlimentosAdapter.AlimentoViewHolder> {

    private List<AlimentoDb> alimentoEntities;
    public EnCambioSeleccionAlimentoEscuchador listener;
    private Integer idExpandido = null;

    public interface EnGuardarAlimentoListener {
        void onGuardarAlimento(AlimentoDb alimento);
    }

    private EnGuardarAlimentoListener guardarListener;

    public AlimentosAdapter(List<AlimentoDb> alimentoEntities,
                            EnCambioSeleccionAlimentoEscuchador listener,
                            EnGuardarAlimentoListener guardarListener) {
        this.alimentoEntities = alimentoEntities;
        this.listener = listener;
        this.guardarListener = guardarListener;
    }

    @NonNull
    @Override
    public AlimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alimento, parent, false);
        return new AlimentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoViewHolder holder, int position) {
        AlimentoDb alimento = alimentoEntities.get(position);

        holder.nombre.setText(alimento.nombre);
        holder.kilos.setText(alimento.kilos + " kg");
        holder.imagenAlimento.setImageResource(alimento.imagenId);

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(alimento.selecionado);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            alimento.selecionado = isChecked;
            if (listener != null) {
                listener.onCambioSeleccionCarne();
            }
        });

        if (alimento.id != null && alimento.id.equals(idExpandido)) {
            holder.layoutDetalles.setVisibility(View.VISIBLE);

            // Mostrar datos actuales en los campos editables
            String cantidad = alimento.cantidad > 0 ? String.valueOf(alimento.cantidad) : "";
            String kilos = alimento.kilos > 0 ? String.valueOf(alimento.kilos) : "";
            holder.editCantidad.setText(cantidad);
            holder.editKilos.setText(kilos);
            holder.editCaducidad.setText(alimento.fechaCaducidad != null ? alimento.fechaCaducidad : "");

            holder.btnGuardar.setOnClickListener(v -> {
                // Actualizar objeto alimento con nuevos valores desde los campos
                if(!holder.editCantidad.getText().toString().isEmpty()) {
                    alimento.cantidad = Integer.parseInt(holder.editCantidad.getText().toString());
                } else {
                    alimento.cantidad = 0;
                }

                try {
                    alimento.kilos = Float.parseFloat(holder.editKilos.getText().toString());
                } catch (NumberFormatException e) {
                    alimento.kilos = 0f;
                }
                alimento.fechaCaducidad = holder.editCaducidad.getText().toString();

                if (guardarListener != null) {
                    guardarListener.onGuardarAlimento(alimento);
                }
            });
        } else {
            holder.layoutDetalles.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return alimentoEntities.size();
    }

    public void editarItemSeleccionado() {
        for (AlimentoDb alimento : alimentoEntities) {
            if (alimento.selecionado) {
                idExpandido = alimento.id;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void dejarDeEditarElementoSeleccionado() {
        idExpandido = null;
        for (AlimentoDb alimento : alimentoEntities) {
            alimento.selecionado = false;
        }
        notifyDataSetChanged();
    }

    public static class AlimentoViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView nombre, kilos;
        ImageView imagenAlimento;

        View layoutDetalles;
        EditText editCantidad, editKilos, editCaducidad;
        Button btnGuardar;

        public AlimentoViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            nombre = itemView.findViewById(R.id.textNombre);
            kilos = itemView.findViewById(R.id.textKilos);
            imagenAlimento = itemView.findViewById(R.id.imageView);

            layoutDetalles = itemView.findViewById(R.id.layoutDetalles);
            editCantidad = itemView.findViewById(R.id.editCantidad);
            editKilos = itemView.findViewById(R.id.editKilos);
            editCaducidad = itemView.findViewById(R.id.editCaducidad);
            btnGuardar = itemView.findViewById(R.id.btnGuardar);
        }
    }
}
