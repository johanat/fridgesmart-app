package fridgeSmart.fridgesmart;

import android.app.Application;

import fridgeSmart.fridgesmart.comun.repositorio.Repositorio;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.notificaciones.CaducidadWorker;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MyApp extends Application {
    public Repositorio repositorio;

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.clearInstance();
        repositorio = new Repositorio(this);

        PeriodicWorkRequest notificacionDiaria =
                new PeriodicWorkRequest.Builder(CaducidadWorker.class, 24, TimeUnit.HOURS)
                        .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("NotificaciónCaducidad", ExistingPeriodicWorkPolicy.KEEP,notificacionDiaria);
    }
}
