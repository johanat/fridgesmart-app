package fridgeSmart.fridgesmart;

public class CarneItem {
    public String tipo;
    public String nombre;
    public double kilos;
    public boolean selecionado;

    public CarneItem(String tipo, String nombre, double kilos) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.kilos = kilos;
        this.selecionado = false;
    }
    public String getTipo(){  return tipo;}
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
