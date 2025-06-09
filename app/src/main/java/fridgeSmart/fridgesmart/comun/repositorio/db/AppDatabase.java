package fridgeSmart.fridgesmart.comun.repositorio.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AlimentoDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
    public abstract AlimentoDao alimentoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "fridge_smart_db")
                                            .fallbackToDestructiveMigration()  // borra datos con cambios no migrados
                                            .build();
                    Log.d("AppDatabase", "Nueva instancia de BD creada: fridge_smart_db");
                }
            }
        }
        return INSTANCE;
    }
    public static void clearInstance() {
        INSTANCE = null;
        Log.d("AppDatabase", "Instancia de BD limpiada");
    }
}
