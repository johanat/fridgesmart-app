package fridgeSmart.fridgesmart.comun;

public class AlimentoPredeterminado {
    public Integer id;
    public int imagenId;
    public String categoria;
    public String subcategoria;
    public String nombre;
    public boolean selecionado;
    public boolean descartado;


    public AlimentoPredeterminado(String categoria, String subcategoria, int imagenId, String nombre, boolean selecionado, boolean descartado) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.nombre = nombre;
        this.imagenId = imagenId;
        this.selecionado = selecionado;
        this.descartado = descartado;
    }
}
