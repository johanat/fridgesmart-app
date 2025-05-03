package fridgeSmart.fridgesmart;

import android.app.Application;

import fridgeSmart.fridgesmart.comun.repositorio.Repositorio;

public class MyApp extends Application {
    public Repositorio repositorio;

    @Override
    public void onCreate() {
        super.onCreate();
        repositorio = new Repositorio(this);
    }
}
