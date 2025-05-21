package fridgeSmart.fridgesmart.comun.repositorio;

import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_CARNE;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_FRUTA;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_LACTEO;
import static fridgeSmart.fridgesmart.comun.Constantes.CATEGORIA_VERDURA;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_CERDO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_EMBUTIDO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_PESCADO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_POLLO;
import static fridgeSmart.fridgesmart.comun.Constantes.SUBCATEGORIA_CARNE_TERNERA;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.AlimentoPredeterminado;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.pantallas.subcategoriacarne.SubcategoriaCarne;
import fridgeSmart.fridgesmart.pantallas.subcategoriacarne.SubcategoriaCarneConContador;

public class Repositorio {
    AppDatabase db;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public List<AlimentoPredeterminado> obtenerTodsLosAlimentos() {
        List<AlimentoPredeterminado> alimentoEntityList = new ArrayList<>();
        //lista de carne de Ternera
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Carne Picada de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Filete de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Costilla de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Lomo de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Solomillo de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Higado de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Ubre de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Rabo de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pechuga de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pata de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pernil de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Entrecot de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Callos de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Hueso de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Tapa de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Tuetano de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Trozos de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Corazon de Ternera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Riñon de Ternera", false,false));


        //lista de carne de Cerdo
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Lomo de Cerdo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Costilla de Cerdo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Panceta de Cerdo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Chuleta de Cerdo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Pernil de Cerdo", false,    false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Pata de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Secreto de Cerdo", false,   false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Paleta de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Codillo de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Papada de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Solomillo de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Higado de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Corazon de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Lagarto de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Oreja de Cerdo", false, false));

        //lista de carne de Pollo
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Muslo de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pechuga de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pata de Pollo", false,  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Alitas de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Corazon de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Higado de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Solomillo de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Traseros de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pechuga de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Contramuslo de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Carcasa de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Mollejas de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Troceado de Pollo", false, false));


        //lista de carne de Pescado
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Salmón", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Rodajas de Salmon", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Tacos de Salmon", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Atún", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Lomos de Atún", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Dorada", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Dorada Entera", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Calamar", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Calamar Entero", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Gambas Peladas Crudas", false,  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Gambas Peladas Cocidas", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Merluza", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Merluza Entera", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Rodajas de Merluza", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Bacalao", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Atún", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Panga", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Caballa Entera", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Caballa", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Jurel Entero", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Sardina", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Sardina Entera", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Mejillones Cocidos", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Mejillones Crudos", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Almejas Cocidas", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Almejas Crudas", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Boquerones Enteros", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Boquerones", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Bueyes Enteros", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Centollo Enteros", false, false));

        //lista de carne de Embutido
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichas de Pollo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichas de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Mortadela de Pollo", false,  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Mortadela de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichón de Ternera", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichón de Cerdo", false, false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Fuet", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Chorizo Ahumado", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salami", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Caña de lomo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Jamón serrano", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Jamón Ibérico", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Arroz", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Cebolla", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Burgos", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Longaniza de Cerdo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Longaniza Fresca", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Chistorra de Cerdo", false,false));


        //Frutas
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Mandarina",false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Plátano",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Fresa",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Manzanas",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.uvas, "Uvas",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Naranjas", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Durazno",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Kiwi",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Piña",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Mango",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Papaya",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Cereza",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Sandia",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Melon",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Limón",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Coco",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Paraguayas",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Guayaba",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Granadilla",  false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Chirimoya",  false,false));

        //Lacteos
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Entera", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Desnatada", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Semidesnatada", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Yogur", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso Crema", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Mantequilla", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Nata para Montar", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Yogurt Natural", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso de Cabra", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso de Azul", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Condensada", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Evaporada", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Suero de Leche", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Mozzarella", false,false));


        //Verduras
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Lechuga", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Tomate", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Cebolla", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Zanahoria", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Pimiento", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Papa", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Espinaca", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Brócoli", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Coliflor", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Berenjena", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Calabacín", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Espárrago", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Ajo", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Apio", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Champiñón", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Guisante", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Col", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Remolacha", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Rábano", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Pepino", false,false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Aguacate", false,false));

        return alimentoEntityList;
    }

    public Repositorio(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "fridge-smart")
                .fallbackToDestructiveMigration()  // 👈 Añades esto aquí, luego hay q cambiarlo, es de prueba
                .build();
    }

    public void guardarAlimento(AlimentoDb alimentoDb) {
        executorService.execute(() -> db.alimentoDao().guardarAlimento(alimentoDb));
    }

    public void borrarAlimento(int id) {
        executorService.execute(() -> db.alimentoDao().borrarAlimento(id));
    }

    public void modificarAlimento(int id, int cantidad, double kilos, String fechaCaducidad) {
        executorService.execute(() -> db.alimentoDao().modificarAlimento(id, cantidad, kilos, fechaCaducidad));
    }

    public LiveData<List<AlimentoDb>> getCarnesDeGrupo(String subcategoria) {
        return db.alimentoDao().getCarnesDeSubcategoria(subcategoria);
    }

    public LiveData<List<AlimentoDb>> getAlimentosDeCategoria(String categoria) {
        return db.alimentoDao().getAlimentosDeCategoria(categoria);
    }

/*
    public List<SubcategoriaCarne> obtenerSubcategoriaCarne() {
        List<SubcategoriaCarne> subcategoriaCarneCategoriaCarnes = new ArrayList<>();
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_TERNERA));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_CERDO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pollo, 3, SUBCATEGORIA_CARNE_POLLO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pescado, 2, SUBCATEGORIA_CARNE_PESCADO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.salchicha, 6, SUBCATEGORIA_CARNE_EMBUTIDO));

        return subcategoriaCarneCategoriaCarnes;
    }*/

    public List<SubcategoriaCarneConContador> obtenerSubcategoriaCarneConContador() {
        List<SubcategoriaCarneConContador> lista = new ArrayList<>();

        lista.add(new SubcategoriaCarneConContador(
                R.drawable.carne_animada,
                SUBCATEGORIA_CARNE_TERNERA,
                contarAlimentosPorSubcategoria(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA)
        ));
        lista.add(new SubcategoriaCarneConContador(
                R.drawable.carne_animada,
                SUBCATEGORIA_CARNE_CERDO,
                contarAlimentosPorSubcategoria(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO)
        ));
        lista.add(new SubcategoriaCarneConContador(
                R.drawable.pollo,
                SUBCATEGORIA_CARNE_POLLO,
                contarAlimentosPorSubcategoria(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO)
        ));
        lista.add(new SubcategoriaCarneConContador(
                R.drawable.pescado,
                SUBCATEGORIA_CARNE_PESCADO,
                contarAlimentosPorSubcategoria(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO)
        ));
        lista.add(new SubcategoriaCarneConContador(
                R.drawable.salchicha,
                SUBCATEGORIA_CARNE_EMBUTIDO,
                contarAlimentosPorSubcategoria(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO)
        ));

        return lista;
    }


    public LiveData<Integer> contarAlimentosPorSubcategoria(String categoria, String subcategoria) {
        return db.alimentoDao().contarAlimentosPorSubcategoriaLiveData(categoria, subcategoria);
    }


}
