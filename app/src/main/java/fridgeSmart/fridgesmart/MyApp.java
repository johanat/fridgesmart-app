package fridgeSmart.fridgesmart;

import android.app.Application;

import fridgeSmart.fridgesmart.comun.repositorio.Repositorio;
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
        repositorio = new Repositorio(this);

        // Esto va dentro de onCreate
        PeriodicWorkRequest notificacionDiaria =
                new PeriodicWorkRequest.Builder(CaducidadWorker.class, 24, TimeUnit.HOURS)
                        .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("NotificaciónCaducidad", ExistingPeriodicWorkPolicy.KEEP,notificacionDiaria);
    }
}
