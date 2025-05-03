package fridgeSmart.fridgesmart.comun.repositorio.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {AlimentoDb.class}, version = 1)
public  abstract class AppDatabase extends RoomDatabase {
    public abstract AlimentoDao alimentoDao();
}
