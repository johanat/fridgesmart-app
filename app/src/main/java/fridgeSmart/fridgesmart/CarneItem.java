package fridgeSmart.fridgesmart;

public class CarneItem {
    public String nombre;
    public double kilos;
    public boolean selecionado;

    public CarneItem( String nombre, double kilos) {
        this.nombre = nombre;
        this.kilos = kilos;
        this.selecionado = false;
    }

    public String getNombre() {
        return nombre;
    }
    public double getKilos() {
        return kilos;
    }
    public boolean isSelecionado() {
        return selecionado;
    }
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
