package fridgeSmart.fridgesmart.comun.modelos;

import java.io.Serializable;
import java.util.List;

public class Receta implements Serializable {
    public int id;
    public String nombre;
    public String descripcion;
    public String imagen;
    public String dificultad;
    public int tiempoPreparacion;
    public List<String> ingredientes;
    public List<String> pasosPreparacion;

    // Constructor
    public Receta(int id, String nombre, String descripcion, String imagen,
                  String dificultad, int tiempoPreparacion,
                  List<String> ingredientes, List<String> pasosPreparacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ingredientes = ingredientes;
        this.pasosPreparacion = pasosPreparacion;
    }
}