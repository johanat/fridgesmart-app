package fridgeSmart.fridgesmart.comun.repositorio.db;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlimentoDb {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public int idAlimentoPredeterminado;
    public int imagenId;
    public String categoria;
    public String subcategoria;
    public String nombre;
    public int cantidad;
    public double kilos;
    public String fechaCaducidad;
    public boolean selecionado;
    public boolean descartado;

    public AlimentoDb(int idAlimentoPredeterminado, int imagenId, String categoria, String subcategoria, String nombre, int cantidad, double kilos, String fechaCaducidad, boolean selecionado, boolean descartado) {
        this.idAlimentoPredeterminado = idAlimentoPredeterminado;
        this.imagenId = imagenId;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.kilos = kilos;
        this.fechaCaducidad = fechaCaducidad;
        this.selecionado = selecionado;
        this.descartado = descartado;
    }
    public boolean descartado() {
        return CATEGORIA_CARNE.equalsIgnoreCase(this.categoria) ?
                kilos <= 0 : cantidad <= 0;
    }
}
