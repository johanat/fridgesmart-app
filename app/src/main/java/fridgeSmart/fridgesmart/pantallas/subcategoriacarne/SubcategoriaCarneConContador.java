package fridgeSmart.fridgesmart.pantallas.subcategoriacarne;

import androidx.lifecycle.LiveData;

public class SubcategoriaCarneConContador {
    public int imagenResId;
    public String subcategoria;
    public LiveData<Integer> contador;  // Aquí guardamos el LiveData

    public SubcategoriaCarneConContador(int imagenResId, String subcategoria, LiveData<Integer> contador) {
        this.imagenResId = imagenResId;
        this.subcategoria = subcategoria;
        this.contador = contador;
    }
}
