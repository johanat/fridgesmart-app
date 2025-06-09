package fridgeSmart.fridgesmart.comun.repositorio;

import fridgeSmart.fridgesmart.comun.modelos.Receta;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecetasRepository {
    private static RecetasRepository instance;
    private List<Receta> recetasPredeterminadas;

    private RecetasRepository() {
        inicializarRecetas();
    }

    public static synchronized RecetasRepository getInstance() {
        if (instance == null) {
            instance = new RecetasRepository();
        }
        return instance;
    }

    private void inicializarRecetas() {
        recetasPredeterminadas = new ArrayList<>();

        // Receta 1: Tortitas Americanas
        recetasPredeterminadas.add(new Receta(
                1,
                "Tortitas Americanas",
                "Deliciosas tortitas esponjosas para el desayuno",
                "tortitas_americanas",
                "Fácil",
                20,
                Arrays.asList("harina", "azúcar", "polvo de hornear", "sal", "huevo", "leche", "mantequilla", "vainilla"),
                Arrays.asList(
                        "Mezclar ingredientes secos",
                        "Batir ingredientes líquidos",
                        "Combinar y cocinar"
                )
        ));

        // Receta 2: Sopa de Ternera
        recetasPredeterminadas.add(new Receta(
                2,
                "Sopa de Ternera con Patatas y Verduras",
                "Sopa reconfortante para días fríos",
                "sopa_ternera",
                "Fácil",
                50,
                Arrays.asList("ternera", "patatas", "zanahoria", "puerro", "apio", "tomate", "caldo", "aceite"),
                Arrays.asList(
                        "Dorar la carne",
                        "Sofreír verduras",
                        "Cocinar todo junto"
                )
        ));

        // Receta 3: Salmón al Horno con Verduras
        recetasPredeterminadas.add(new Receta(
                3,
                "Salmón al Horno con Verduras",
                "Receta saludable y rica en omega-3",
                "plato_salmon",
                "Fácil",
                30,
                Arrays.asList("salmón", "calabacín", "zanahoria", "cebolla", "limón", "aceite", "sal", "pimienta"),
                Arrays.asList(
                        "Precalentar el horno a 180°C",
                        "Colocar el salmón en una bandeja con las verduras cortadas",
                        "Salpimentar, añadir limón y hornear durante 20 minutos"
                )
        ));

        // Receta 4: Pasta con Salsa de Tomate y Albahaca
        recetasPredeterminadas.add(new Receta(
                4,
                "Pasta con Salsa de Tomate y Albahaca",
                "Un clásico italiano lleno de sabor",
                "spagetti",
                "Fácil",
                25,
                Arrays.asList("pasta", "tomate", "ajo", "cebolla", "albahaca", "aceite", "sal", "queso parmesano"),
                Arrays.asList(
                        "Cocer la pasta según las instrucciones del paquete",
                        "Sofreír ajo y cebolla, añadir tomate y cocinar 10 minutos",
                        "Mezclar con la pasta, añadir albahaca y servir con queso parmesano"
                )
        ));


        // Se pueden añadir más recetas aquí...
    }

    public List<Receta> getRecetasPredeterminadas() {
        return recetasPredeterminadas;
    }

    public Receta getRecetaPorId(int id) {
        for (Receta receta : recetasPredeterminadas) {
            if (receta.id == id) {
                return receta;
            }
        }
        return null;
    }
}