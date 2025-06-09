package fridgeSmart.fridgesmart.notificaciones;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;
import fridgeSmart.fridgesmart.comun.repositorio.db.AppDatabase;
import fridgeSmart.fridgesmart.pantallas.gestionalimentos.GestionAlimentosActivity;

public class CaducidadWorker extends Worker {

    public CaducidadWorker(@NonNull Context context, @NonNull WorkerParameters params){
        super(context,params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("CaducidadWorker", "Worker ejecutándose");

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        List<AlimentoDb> alimentos = db.alimentoDao().obtenerTodos();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Obtener fecha actual sin hora
        Calendar calHoy = Calendar.getInstance();
        calHoy.set(Calendar.HOUR_OF_DAY, 0);
        calHoy.set(Calendar.MINUTE, 0);
        calHoy.set(Calendar.SECOND, 0);
        calHoy.set(Calendar.MILLISECOND, 0);
        Date hoySinHora = calHoy.getTime();

        // Obtener fecha límite (7 días después) sin hora
        Calendar calLimite = (Calendar) calHoy.clone();
        calLimite.add(Calendar.DAY_OF_YEAR, 7);
        Date fechaLimite = calLimite.getTime();

        // Añadimos un log para ver las variables:
        Log.d("FechasDebug", "Hoy: " + sdf.format(hoySinHora) + ", Límite: " + sdf.format(fechaLimite));

        StringBuilder alimentosCaducando = new StringBuilder();

        //añadimos log para ver el total de alimentos
        Log.d("AlimentosDB", "Total alimentos: " + alimentos.size());

        for (AlimentoDb alimento : alimentos) {
            //añadimos log para ver fecha de caducidad por cada alimento
            Log.d("AlimentoDebug", "Nombre: " + alimento.nombre + ", Fecha: " + alimento.fechaCaducidad);

            if (alimento.fechaCaducidad != null && !alimento.fechaCaducidad.isEmpty()) {

                try {
                    Date fechaAlimento = sdf.parse(alimento.fechaCaducidad);
                    if (fechaAlimento != null &&
                            !fechaAlimento.before(hoySinHora) &&
                            !fechaAlimento.after(fechaLimite)) {
                        alimentosCaducando.append("- ").append(alimento.nombre).append(" (Caduca: ").
                                append(alimento.fechaCaducidad).append(")\n");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("CaducidadWorker", "Error parseando fecha: " + alimento.fechaCaducidad, e);
                }

            }
        }


        if (alimentosCaducando.length() > 0) {
            Log.d("CaducidadWorker", "Alimentos caducando: " + alimentosCaducando.toString());
            mostrarNotificacion("Alimentos por caducar", alimentosCaducando.toString());
        }else {
            Log.d("CaducidadWorker", "No hay alimentos por caducar");
        }

        return Result.success();
    }

    private void mostrarNotificacion(String titulo, String mensaje) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().
                getSystemService(Context.NOTIFICATION_SERVICE);
        String canalId = "canal_caducidad";

        // Creamos canal para Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(canalId, "Notificaciones de caducidad",
                    NotificationManager.IMPORTANCE_HIGH); // Alta prioridad para que se muestre como "importante"
            canal.setDescription("Alertas de alimentos por caducar");
            canal.enableVibration(true); // Vibración activada
            notificationManager.createNotificationChannel(canal);
        }

        // Creamos un Intent para abrir GestionAlimentosActivity
        Intent intent = new Intent(getApplicationContext(), GestionAlimentosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Construimos la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), canalId)
                .setSmallIcon(R.drawable.logo_nevera)
                .setContentTitle(titulo)
                .setContentText("Pulsa para ver más")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setOngoing(false)
                .setVibrate(new long[]{0, 500, 200, 500})
                .setContentIntent(pendingIntent);
        // Lanzamos la notificación
        notificationManager.notify((int) System.currentTimeMillis(), builder.build()); // ID único

    }


}
