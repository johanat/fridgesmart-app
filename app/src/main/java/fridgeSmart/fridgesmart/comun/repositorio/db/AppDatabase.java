package fridgeSmart.fridgesmart.comun.repositorio.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AlimentoDb.class}, version = 11)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlimentoDao alimentoDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "fridge_smart_db"
                            )
                            .fallbackToDestructiveMigration()  // opcional, solo si quieres borrar datos con cambios no migrados
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
