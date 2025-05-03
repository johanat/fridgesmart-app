package fridgeSmart.fridgesmart.pantallas.subcategoriacarne;

public class SubcategoriaCarne {
    private int imageId;
    private int cantidad;
    private String nombre;

    public SubcategoriaCarne(int imageId, int cantidad, String nombre) {
        this.imageId = imageId;
        this.cantidad = cantidad;
        this.nombre=nombre;
    }

    public int getImageId() { return imageId; }
    public int getCantidad() { return cantidad; }
    public String getNombre(){return nombre;}

}
