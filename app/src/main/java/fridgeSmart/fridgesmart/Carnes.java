package fridgeSmart.fridgesmart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import fridgeSmart.fridgesmart.Item;
import fridgeSmart.fridgesmart.ItemAdapter;
import java.util.List;

public class Carnes extends GestionAlimentos {

    // Estructuras para guardar los alimentos segun la categoria
    List<String> listaCarnes = new ArrayList<>();
    List<String> listaPollo = new ArrayList<>();
    List<String> listaPescados = new ArrayList<>();
    List<String> listaEmbutidos = new ArrayList<>();

    private EditText editTextNombre, editTextCantidad;
    private Spinner spinnerTipo;
    private Dialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnes);

        ImageView regresar = findViewById(R.id.backButton);

        //Inicializamos los iconos flotantes
        FloatingActionButton iconAgregar = findViewById(R.id.btnAgregar);
        FloatingActionButton iconoEliminar = findViewById(R.id.btnEliminar);
        FloatingActionButton iconoModificar = findViewById(R.id.btnModificar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.carne_animada, "Carne", 5, "carne", "CARNES"));
        itemList.add(new Item(R.drawable.pollo, "Pollo", 3, "pollo", "CARNES"));
        itemList.add(new Item(R.drawable.pescado, "Pescado", 2, "pescado", "CARNES"));
        itemList.add(new Item(R.drawable.salchicha, "Embutidos", 6, "embutidos", "CARNES"));

        regresar.setOnClickListener(view -> {
            Intent retroceder = new Intent(Carnes.this, GestionAlimentos.class);
            finish();
        });

        ItemAdapter adapter = new ItemAdapter(itemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(fridgeSmart.fridgesmart.Item item) {
                showCustomDialog(item);
            }
        });
        recyclerView.setAdapter(adapter);

        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inflar el layout del diálogo
                //LayoutInflater inflater = LayoutInflater.from(Carnes.this);
                View view = LayoutInflater.from(Carnes.this).inflate(R.layout.dialog_agregar_alimento, null);

                //inicializamos
                editTextNombre = view.findViewById(R.id.editTextNombre);
                editTextCantidad = view.findViewById(R.id.editTextCantidad);
                spinnerTipo = view.findViewById(R.id.spinnerTipo);
                Button btnAceptar = view.findViewById(R.id.btnAceptar);
                Button btnCancelar = view.findViewById(R.id.btnCancelar);

               //Opciones para el Spinner
               String[] tipos = {"Carnes", "Pollo", "Pescados", "Embutidos"};
               ArrayAdapter<String> adapter = new ArrayAdapter<>(Carnes.this, android.R.layout.simple_spinner_dropdown_item, tipos);
               spinnerTipo.setAdapter(adapter);

                //Crear el AlertDialog
                dialog = new AlertDialog.Builder(Carnes.this)
                        .setView(view)
                        .setCancelable(false)
                        .create();


                //Acción de Cancelar
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //Acción de Aceptar
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //validar
                        String nombre = editTextNombre.getText().toString().trim();
                        String cantidadStr = editTextCantidad.getText().toString().trim();
                        String categoria = spinnerTipo.getSelectedItem().toString();

                        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
                            Toast.makeText(Carnes.this, "Debe completar todos  los campos", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            try {
                                int cantidad = Integer.parseInt(cantidadStr);
                                if (cantidad <= 0) {
                                    Toast.makeText(Carnes.this, "La cantidad debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                                } else {
                                    String textoAlimento = cantidad + " K   " + nombre;
                                    switch (categoria) {
                                        case "Carnes":
                                            listaCarnes.add(textoAlimento);
                                            break;
                                        case "Pollo":
                                            listaPollo.add(textoAlimento);
                                            break;
                                        case "Pescados":
                                            listaPescados.add(textoAlimento);
                                            break;
                                        case "Embutidos":
                                            listaEmbutidos.add(textoAlimento);
                                            break;
                                    }

                                    Toast.makeText(Carnes.this, "Alimento agregado", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(Carnes.this, "Ingrese un número válido en cantidad", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();
            }
        });

        //Funcionalidad del icono Modificar
        iconoModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflamos el layout del primer dialogo
                View dialogView1 = LayoutInflater.from(Carnes.this).inflate(R.layout.dialog_modificar_selector,null);
                Spinner spinnerSubcategoria = dialogView1.findViewById(R.id.spinnerSubcategoria);
                Spinner spinnerAlimento = dialogView1.findViewById(R.id.spinnerAlimento);

                // Opciones de subcategoría
                String[] subcategorias = {"Carne", "Pollo", "Pescado", "Embutidos"};
                ArrayAdapter<String> subAdapter = new ArrayAdapter<>(Carnes.this, android.R.layout.simple_spinner_item, subcategorias);
                subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSubcategoria.setAdapter(subAdapter);

                // Al seleccionar una subcategoría, cargamos los alimentos
                spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ArrayList<String> lista = obtenerListaPorSubcategoria(subcategorias[position]);
                        ArrayAdapter<String> alimentoAdapter = new ArrayAdapter<>(Carnes.this, android.R.layout.simple_spinner_item, lista);
                        alimentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerAlimento.setAdapter(alimentoAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });
                // Mostrar primer diálogo
                new AlertDialog.Builder(Carnes.this)
                        .setTitle("Seleccionar alimento")
                        .setView(dialogView1)
                        .setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String subcategoriaSeleccionada = spinnerSubcategoria.getSelectedItem().toString();
                                String alimentoSeleccionado = spinnerAlimento.getSelectedItem().toString();

                                // Segundo diálogo: modificar alimento
                                mostrarDialogoModificarAlimento(subcategoriaSeleccionada, alimentoSeleccionado);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });

    }


    private void showCustomDialog(Item item) {
        // Crear el diálogo
        Dialog dialog = new Dialog(this);
        View dialogView;
        LayoutInflater inflater = LayoutInflater.from(this);

        switch (item.getNombre()) {
            case "carne":
                dialogView = inflater.inflate(R.layout.dialog_custom, null);
                LinearLayout layoutCarnes = dialogView.findViewById(R.id.layoutCarnes);
                for (String alimento : listaCarnes) {
                    Button boton = crearBotonAlimento(alimento);
                    layoutCarnes.addView(boton);
                }
                break;
            case "pollo":
                dialogView = inflater.inflate(R.layout.dialog_custom_pollo, null);
                LinearLayout layoutPollo = dialogView.findViewById(R.id.layoutPollo);
                for (String alimento : listaPollo) {
                    Button boton = crearBotonAlimento(alimento);
                    layoutPollo.addView(boton);
                }
                break;
            case "pescado":
                dialogView = inflater.inflate(R.layout.dialog_custom_pescado, null);
                LinearLayout layoutPescado = dialogView.findViewById(R.id.layoutPescados);
                for (String alimento : listaPescados) {
                    Button boton = crearBotonAlimento(alimento);
                    layoutPescado.addView(boton);
                }
                break;
            case "embutidos":
                dialogView = inflater.inflate(R.layout.dialog_custom_embutidos, null);
                LinearLayout layoutEmbutidos = dialogView.findViewById(R.id.layoutEmbutidos);
                for (String alimento : listaEmbutidos) {
                    Button boton = crearBotonAlimento(alimento);
                    layoutEmbutidos.addView(boton);
                }
                break;
            default:
                dialogView = inflater.inflate(R.layout.dialog_custom, null);
                break;
        }

        dialog.setContentView(dialogView);
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

    private Button crearBotonAlimento(String texto) {
        Button boton = new Button(Carnes.this);
        boton.setText(texto);
        //boton.setBackgroundTintList(ContextCompat.getColorStateList(Carnes.this, R.color.blue));
        boton.setBackground(ContextCompat.getDrawable(Carnes.this,R.drawable.button_rounded));
        boton.setTextColor(Color.WHITE);
        //Creamos LayoutParams con margenes
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margen = (int) getResources().getDisplayMetrics().density * 8;
        params.setMargins(0, margen, 0, margen);//margen arriba y abajo
        boton.setLayoutParams(params);

        //Padding interno
        int paddingVertical = (int) (getResources().getDisplayMetrics().density * 12);
        boton.setPadding(0,paddingVertical,0,paddingVertical);


        return boton;
    }

    private ArrayList<String> obtenerListaPorSubcategoria(String subcategoria) {
        switch (subcategoria) {
            case "Carne":
                return (ArrayList<String>) listaCarnes;
            case "Pollo":
                return (ArrayList<String>) listaPollo;
            case "Pescado":
                return (ArrayList<String>) listaPescados;
            case "Embutidos":
                return (ArrayList<String>) listaEmbutidos;
            default:
                return new ArrayList<>();
        }
    }

    private void mostrarDialogoModificarAlimento(String subcategoria, String alimentoOriginal) {
        View dialogView2 = LayoutInflater.from(Carnes.this).inflate(R.layout.dialog_modificar_alimento, null);
        EditText editTextNombre = dialogView2.findViewById(R.id.editTextNuevoNombre);
        EditText editTextCantidad = dialogView2.findViewById(R.id.editTextNuevaCantidad);

        // Rellenar los campos con el alimento actual
        String[] partes = alimentoOriginal.split(" - ");
        if (partes.length == 2) {
            editTextNombre.setText(partes[0]);
            editTextCantidad.setText(partes[1]);
        }

        new AlertDialog.Builder(Carnes.this)
                .setTitle("Modificar alimento")
                .setView(dialogView2)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nuevoNombre = editTextNombre.getText().toString().trim();
                        String nuevaCantidad = editTextCantidad.getText().toString().trim();

                        if (!nuevoNombre.isEmpty() && !nuevaCantidad.isEmpty()) {
                            ArrayList<String> lista = obtenerListaPorSubcategoria(subcategoria);
                            int index = lista.indexOf(alimentoOriginal);
                            if (index != -1) {
                                lista.set(index, nuevaCantidad + " - " + nuevoNombre);
                                Toast.makeText(Carnes.this, "Alimento modificado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


}
