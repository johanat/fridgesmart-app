package fridgeSmart.fridgesmart;

public class Item {
    private int imageResId;
    private String title;
    private int number;

    public Item(int imageResId, String title, int number) {
        this.imageResId = imageResId;
        this.title = title;
        this.number = number;
    }

    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public int getNumber() { return number; }
}
