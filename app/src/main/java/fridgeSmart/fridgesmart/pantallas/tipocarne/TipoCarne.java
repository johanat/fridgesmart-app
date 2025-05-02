package fridgeSmart.fridgesmart.pantallas.tipocarne;

public class TipoCarne {
    private int imageId;
    private int cantidad;
    private String nombre;
    private String tipo;

    public TipoCarne(int imageId, int cantidad, String nombre, String tipo) {
        this.imageId = imageId;
        this.cantidad = cantidad;
        this.nombre=nombre;
        this.tipo = tipo;
    }

    public int getImageId() { return imageId; }
    public int getCantidad() { return cantidad; }
    public String getNombre(){return nombre;}
    public String getTipo(){return tipo;}


}
