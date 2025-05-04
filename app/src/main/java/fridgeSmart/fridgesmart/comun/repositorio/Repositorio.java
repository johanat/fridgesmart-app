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

public class Repositorio {
    AppDatabase db;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public List<AlimentoPredeterminado> obtenerTodsLosAlimentos() {
        List<AlimentoPredeterminado> alimentoEntityList = new ArrayList<>();
        //lista de carne de Ternera
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Carne Picada de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Filete de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Costilla de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Lomo de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Solomillo de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Higado de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Ubre de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Rabo de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pechuga de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pata de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Pernil de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Entrecot de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Callos de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Hueso de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Tapa de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Tuetano de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Trozos de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Corazon de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_TERNERA, R.drawable.carne_animada, "Riñon de Ternera", false));


        //lista de carne de Cerdo
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Lomo de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Costilla de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Panceta de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Chuleta de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Pernil de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Pata de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Secreto de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Paleta de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Codillo de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Papada de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Solomillo de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Higado de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Corazon de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Lagarto de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.carne_animada, "Oreja de Cerdo", false));

        //lista de carne de Pollo
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Muslo de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pechuga de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pata de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Alitas de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Corazon de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Higado de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Solomillo de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Traseros de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Pechuga de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Contramuslo de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_POLLO, R.drawable.pollo, "Carcasa de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.pollo, "Mollejas de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.pollo, "Troceado de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.pollo, "Pechuga de Pavo", false));


        //lista de carne de Pescado
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Salmón", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Rodajas de Salmon", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Tacos de Salmon", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Atún", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Lomos de Atún", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.pescado, "Filetes de Dorada", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Dorada Entera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_CERDO, R.drawable.pescado, "Filetes de Calamar", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Calamar Entero", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Gambas Peladas Crudas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Gambas Peladas Cocidas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Merluza", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Merluza Entera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Rodajas de Merluza", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Bacalao", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Atún", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Panga", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Caballa Entera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Caballa", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Jurel Entero", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Sardina", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Sardina Entera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Mejillones Cocidos", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Mejillones Crudos", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Almejas Cocidas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Almejas Crudas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Boquerones Enteros", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Filetes de Boquerones", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Bueyes Enteros", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_PESCADO, R.drawable.pescado, "Centollo Enteros", false));

        //lista de carne de Embutido
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichas de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichas de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Mortadela de Pollo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Mortadela de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichón de Ternera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salchichón de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Fuet", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Chorizo Ahumado", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Salami", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Caña de lomo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Jamón serrano", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Jamón Ibérico", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Arroz", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Cebolla", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Morcilla de Burgos", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Longaniza de Cerdo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Longaniza Fresca", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_CARNE, SUBCATEGORIA_CARNE_EMBUTIDO, R.drawable.salchicha, "Chistorra de Cerdo", false));


        //Frutas
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Mandarina",false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Plátano",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Fresa",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Manzanas",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.uvas, "Uvas",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Naranjas", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Durazno",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Kiwi",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Piña",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Mango",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Papaya",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Cereza",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Sandia",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Melon",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Limón",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Coco",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Paraguayas",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Guayaba",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Granadilla",  false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_FRUTA, null, R.drawable.frutas, "Chirimoya",  false));

        //Lacteos
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Entera", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Desnatada", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Semidesnatada", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Yogur", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso Crema", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Mantequilla", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Nata para Montar", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Yogurt Natural", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso de Cabra", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Queso de Azul", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Condensada", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Leche Evaporada", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Suero de Leche", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_LACTEO, null, R.drawable.leche, "Mozzarella", false));


        //Verduras
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Lechuga", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Tomate", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Cebolla", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Zanahoria", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Pimiento", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Papa", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Espinaca", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Brócoli", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Coliflor", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Berenjena", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Calabacín", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Espárrago", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Ajo", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Apio", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Champiñón", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Guisante", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Col", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Remolacha", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Rábano", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Pepino", false));
        alimentoEntityList.add(new AlimentoPredeterminado(CATEGORIA_VERDURA, null, R.drawable.verdura, "Aguacate", false));

        return alimentoEntityList;
    }

    public Repositorio(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "fridge-smart").build();
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


    public List<SubcategoriaCarne> obtenerSubcategoriaCarne() {
        List<SubcategoriaCarne> subcategoriaCarneCategoriaCarnes = new ArrayList<>();
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_TERNERA));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.carne_animada, 5, SUBCATEGORIA_CARNE_CERDO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pollo, 3, SUBCATEGORIA_CARNE_POLLO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.pescado, 2, SUBCATEGORIA_CARNE_PESCADO));
        subcategoriaCarneCategoriaCarnes.add(new SubcategoriaCarne(R.drawable.salchicha, 6, SUBCATEGORIA_CARNE_EMBUTIDO));

        return subcategoriaCarneCategoriaCarnes;
    }
}
