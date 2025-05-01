package fridgeSmart.fridgesmart;

public class ItemTipoCarne {
    private int imageResId;
    private String title;
    private int number;
    private String nombre;
    private String category;

    public ItemTipoCarne(int imageResId, String title, int number, String nombre, String category) {
        this.imageResId = imageResId;
        this.title = title;
        this.number = number;
        this.nombre=nombre;
        this.category=category;
    }

    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public int getNumber() { return number; }
    public String getNombre(){return nombre;}
    public String getCategory(){return category;}


}
