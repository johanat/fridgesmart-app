package fridgeSmart.fridgesmart.repositorio;

import java.util.ArrayList;
import java.util.List;

import fridgeSmart.fridgesmart.pantallas.detalletipocarne.Carne;

public class Repositorio {
    public List<Carne> obtenerCarnes() {
        List<Carne> carneList = new ArrayList<>();
        carneList.add(new Carne("Carne", "Chuleta de Cerdo", 2.5));
        carneList.add(new Carne("Carne", "Filete de Ternera", 3.0));
        carneList.add(new Carne("Carne", "Lomo de Cerdo", 1.0));
        carneList.add(new Carne("Carne", "Higado de ternera", 1.5));
        carneList.add(new Carne("Pollo", "Muslos de pollo", 2.0));
        carneList.add(new Carne("Pollo", "Pechuga de pollo", 1.5));
        carneList.add(new Carne("Pollo", "Calamar", 1.5));
        carneList.add(new Carne("Pescado", "Meluza", 2.5));
        carneList.add(new Carne("Pescado", "Salmón", 3.0));
        carneList.add(new Carne("Embutido", "mortadela", 2.0));
        carneList.add(new Carne("Embutido", "salchichón", 1.5));
        return carneList;
    }
}
