package fridgeSmart.fridgesmart.pantallas.alimentos;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import fridgeSmart.fridgesmart.MyApp;
import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.pantallas.anadiralimento.AnadirAlimentosActivity;

public class AlimentosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlimentosAdapter alimentosAdapter;
    private FloatingActionButton btnEliminar;
    private FloatingActionButton btnAgregar;
    private FloatingActionButton btnModificar;

    private String categoriaAlimento;
    private String subcategoriaCarne;
    private EditText etBuscar;
    private ImageView buscador;
    private View filterMenuContainer;
    private boolean isFilterMenuVisible = false;
    private List<AlimentoDb> originalList;
    private List<AlimentoDb> alimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alimentos);

        Log.d("AlimentosActivity", "onCreate - Categoría: " + categoriaAlimento);

        // Inicialización de vistas
        initViews();

        // Configuración inicial
        setupInitialState();

        // Configuración de listeners
        setupListeners();

        // Cargar datos
        loadData();
    }

    private void initViews() {
        filterMenuContainer = findViewById(R.id.filterMenuContainer);
        //filterMenuContainer = getLayoutInflater().inflate(R.layout.filter_menu, null);
        ImageView filterIcon = findViewById(R.id.filterIcon);
        recyclerView = findViewById(R.id.recyclerViewCarne);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnModificar = findViewById(R.id.btnModificar);
        etBuscar = findViewById(R.id.etBuscar);
        buscador = findViewById(R.id.buscador);
        filterMenuContainer.setVisibility(View.VISIBLE);
        filterMenuContainer.bringToFront();
    }

    private void setupInitialState() {
        filterMenuContainer.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alimentos = new ArrayList<>();
        originalList = new ArrayList<>();
    }
    private void setupListeners() {
        // Listener del icono de filtro
        ImageView filterIcon = findViewById(R.id.filterIcon);
        filterIcon.setOnClickListener(v -> toggleFilterMenu());
        // Listener del botón de cerrar
        ImageButton btnCloseFilter = filterMenuContainer.findViewById(R.id.btnCloseFilter);
        btnCloseFilter.setOnClickListener(v -> toggleFilterMenu());
        Button btnDatePicker = filterMenuContainer.findViewById(R.id.btnDatePicker);
        RadioGroup radioGroup = filterMenuContainer.findViewById(R.id.radioGroupExpiration);


        // Listener del buscador
        buscador.setOnClickListener(v -> {
            etBuscar.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(etBuscar, InputMethodManager.SHOW_IMPLICIT);
        });

        // Listener de búsqueda
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                filtrarAlimentos(s.toString());
            }
        });

        // Listeners de botones flotantes
        btnEliminar.setOnClickListener(v -> onBtnEliminarClick());
        btnAgregar.setOnClickListener(v -> onBtnAgregarClick());
        btnModificar.setOnClickListener(v -> onBtnModificarClick());

        // Configurar listeners de filtros
        setupFilterListeners();

        // 1. Configurar el listener del RadioGroup
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.filterSpecificDate) {
                btnDatePicker.setVisibility(View.VISIBLE);
            } else {
                btnDatePicker.setVisibility(View.GONE);
                applyFilters(); // Aplicar filtros inmediatamente
            }
        });

        // 2. Configurar el botón de fecha
        btnDatePicker.setOnClickListener(v -> {
            showDatePicker();
            // Marcar el radio button como seleccionado
            radioGroup.check(R.id.filterSpecificDate);
        });
    }

    private void loadData() {
        Intent intent = getIntent();
        categoriaAlimento = intent.getStringExtra(CATEGORIA);

        if (categoriaAlimento.equals(CATEGORIA_CARNE)) {
            subcategoriaCarne = intent.getStringExtra(SUBCATEGORIA_CARNE);
            ((MyApp) getApplication()).repositorio.getCarnesDeGrupo(subcategoriaCarne)
                    .observe(this, alimentos -> {
                        this.alimentos = alimentos;
                        this.originalList = new ArrayList<>(alimentos);
                        mostrarAlimentos(alimentos);
                    });
        } else {
            ((MyApp) getApplication()).repositorio.getAlimentosDeCategoria(categoriaAlimento)
                    .observe(this, alimentos -> {
                        this.alimentos = alimentos;
                        this.originalList = new ArrayList<>(alimentos);
                        mostrarAlimentos(alimentos);
                    });
        }
    }

    private void toggleFilterMenu() {
        isFilterMenuVisible = !isFilterMenuVisible;

        if (isFilterMenuVisible) {
            filterMenuContainer.setVisibility(View.VISIBLE);
            filterMenuContainer.setAlpha(0f);
            filterMenuContainer.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start();
        } else {
            filterMenuContainer.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction(() -> filterMenuContainer.setVisibility(View.GONE))
                    .start();
        }

        updateFilterIconColor();
    }

    private void updateFilterIconColor() {
        ImageView filterIcon = findViewById(R.id.filterIcon);
        int color = isFilterMenuVisible ?
                ContextCompat.getColor(this, R.color.blue) :
                ContextCompat.getColor(this, android.R.color.black);
        ImageViewCompat.setImageTintList(filterIcon, ColorStateList.valueOf(color));
    }

    private void setupFilterListeners() {
        // Botones del menú de filtros
        Button btnApply = filterMenuContainer.findViewById(R.id.btnApplyFilters);
        Button btnReset = filterMenuContainer.findViewById(R.id.btnResetFilters);
        Button btnDatePicker = filterMenuContainer.findViewById(R.id.btnDatePicker);
        AutoCompleteTextView spinnerQuantity = filterMenuContainer.findViewById(R.id.spinnerQuantity);

        btnApply.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.VISIBLE);

        btnApply.setOnClickListener(v -> applyFilters());
        btnReset.setOnClickListener(v -> resetFilters());
        btnDatePicker.setOnClickListener(v -> showDatePicker());

        // Opciones para el filtro de stock
        String[] stockOptions = {
                "Todos",
                "Stock bajo (<2 unidades)",
                "Stock medio (2-5 unidades)",
                "Stock alto (>5 unidades)"
        };
/*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_filter_options, android.R.layout.simple_dropdown_item_1line);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                stockOptions
        );
        spinnerQuantity.setAdapter(adapter);
    }

    private void applyFilters() {
        List<AlimentoDb> filteredList = new ArrayList<>(originalList);

        // Filtro por fecha
        RadioGroup radioGroup = filterMenuContainer.findViewById(R.id.radioGroupExpiration);
        int dateFilterId = radioGroup.getCheckedRadioButtonId();
        String selectedDate = dateFilterId == R.id.filterSpecificDate ?
                ((Button)filterMenuContainer.findViewById(R.id.btnDatePicker)).getText().toString() : null;

        filteredList = FilterUtils.filterByDate(filteredList, dateFilterId, selectedDate);

        // Filtro por stock
        AutoCompleteTextView spinner = filterMenuContainer.findViewById(R.id.spinnerQuantity);
        if (spinner.getText().toString().contains("Stock bajo")) {
            filteredList.removeIf(alimento -> alimento.cantidad >= 2);
        } else if(spinner.getText().toString().contains("Stock medio")){
            filteredList.removeIf(alimento -> alimento.cantidad < 2 || alimento.cantidad > 5);
        } else if (spinner.getText().toString().contains("Stock alto")) {
            filteredList.removeIf(alimento -> alimento.cantidad <= 5);
        }
        // Filtro por estado
        SwitchMaterial switchDiscarded = filterMenuContainer.findViewById(R.id.switchDiscarded);
        if (switchDiscarded.isChecked()) {
            filteredList.removeIf(alimento -> !alimento.descartado());
        }

        // Ordenación
        ChipGroup chipGroup = filterMenuContainer.findViewById(R.id.chipGroupSort);
        int selectedId = chipGroup.getCheckedChipId();

        if (selectedId == R.id.chipSortName) {
            Collections.sort(filteredList, (a1, a2) -> a1.nombre.compareToIgnoreCase(a2.nombre));
        } else if (selectedId == R.id.chipSortQuantity) {
            Collections.sort(filteredList, (a1, a2) -> Integer.compare(a2.cantidad, a1.cantidad));
        }else if(selectedId == R.id.chipSortExpiry){
            //ordenamos por fecha de caducidad
            Collections.sort(filteredList, (a1, a2) -> DateUtils.compareDates(a1.fechaCaducidad, a2.fechaCaducidad));
        }
        alimentosAdapter.actualizarLista(filteredList);
        toggleFilterMenu();
    }

    private void mostrarAlimentos(List<AlimentoDb> alimentoEntities) {
        alimentosAdapter = new AlimentosAdapter(alimentoEntities,
                () -> {
                    int itemsSeleccionados = 0;
                    for (AlimentoDb alimento : alimentoEntities) {
                        if (alimento.selecionado) itemsSeleccionados++;
                    }
                    btnModificar.setVisibility(itemsSeleccionados == 1 ? View.VISIBLE : View.GONE);
                    btnEliminar.setVisibility(itemsSeleccionados > 0 ? View.VISIBLE : View.GONE);
                },
                alimento -> {
                    ((MyApp) getApplication()).repositorio.modificarAlimento(alimento.id,
                            alimento.cantidad, alimento.kilos, alimento.fechaCaducidad);
                    alimentosAdapter.dejarDeEditarElementoSeleccionado();
                });

        if (!etBuscar.getText().toString().isEmpty()) {
            filtrarAlimentos(etBuscar.getText().toString());
        } else {
            recyclerView.setAdapter(alimentosAdapter);
        }
    }

    private void filtrarAlimentos(String textoBusqueda) {
        if (alimentos == null) return;

        List<AlimentoDb> listaFiltrada;
        if (textoBusqueda.isEmpty()) {
            listaFiltrada = new ArrayList<>(alimentos);
        } else {
            listaFiltrada = new ArrayList<>();
            String busqueda = textoBusqueda.trim().toLowerCase();
            for (AlimentoDb alimento : alimentos) {
                if (alimento.nombre.toLowerCase().contains(busqueda)) {
                    listaFiltrada.add(alimento);
                }
            }
        }

        if (alimentosAdapter != null) {
            alimentosAdapter.actualizarLista(listaFiltrada);
        }
    }

    private void resetFilters() {
        RadioGroup radioGroup = filterMenuContainer.findViewById(R.id.radioGroupExpiration);
        Button btnDatePicker = filterMenuContainer.findViewById(R.id.btnDatePicker);
        AutoCompleteTextView spinnerQuantity = filterMenuContainer.findViewById(R.id.spinnerQuantity);
        SwitchMaterial switchDiscarded = filterMenuContainer.findViewById(R.id.switchDiscarded);
        ChipGroup chipGroup = filterMenuContainer.findViewById(R.id.chipGroupSort);

        radioGroup.check(R.id.filterAllDates);
        btnDatePicker.setText("Seleccionar fecha");
        spinnerQuantity.setText("");
        switchDiscarded.setChecked(false);
        chipGroup.check(R.id.chipSortName);

        alimentosAdapter.actualizarLista(originalList);
    }

    private void showDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateStr = sdf.format(new Date(selection));
            ((Button) filterMenuContainer.findViewById(R.id.btnDatePicker)).setText(dateStr);
            ((RadioButton) filterMenuContainer.findViewById(R.id.filterSpecificDate)).setChecked(true);
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER_TAG");
    }

    private void onBtnEliminarClick() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Estás seguro de que deseas eliminar los elementos seleccionados?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    for (int i = alimentos.size() - 1; i >= 0; i--) {
                        if (alimentos.get(i).selecionado) {
                            ((MyApp) getApplication()).repositorio.borrarAlimento(alimentos.get(i).id);
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    private void onBtnAgregarClick() {
        Intent intent = new Intent(this, AnadirAlimentosActivity.class);
        intent.putExtra(CATEGORIA, categoriaAlimento);
        if (CATEGORIA_CARNE.equals(categoriaAlimento) && subcategoriaCarne != null) {
            intent.putExtra(SUBCATEGORIA_CARNE, subcategoriaCarne);
        }
        // Añade estas flags para manejar correctamente la navegación
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

        // Opcional: animación personalizada
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void onBtnModificarClick() {
        alimentosAdapter.editarItemSeleccionado();
    }


    private List<Carne> getListaDeTipo(List<Carne> carneList, String tipo) {
        if (carneList == null || tipo == null) {
            return new ArrayList<>();
        }
        return carneList.stream()
                .filter(carne -> tipo.equalsIgnoreCase(carne.getTipo()))
                .collect(Collectors.toList());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        loadData(); // Recarga los datos si llega un nuevo intent
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AlimentosActivity", "onResume - Categoría: " + categoriaAlimento);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AlimentosActivity", "onPause");
    }

}



