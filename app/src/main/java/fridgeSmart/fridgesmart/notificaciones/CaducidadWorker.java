package fridgeSmart.fridgesmart.notificaciones;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
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

        // Añade este log (las variables SÍ existen aquí):
        Log.d("FechasDebug", "Hoy: " + sdf.format(hoySinHora) + ", Límite: " + sdf.format(fechaLimite));

        StringBuilder alimentosCaducando = new StringBuilder();

        Log.d("AlimentosDB", "Total alimentos: " + alimentos.size());
        for (AlimentoDb alimento : alimentos) {
            Log.d("AlimentoDebug", "Nombre: " + alimento.nombre + ", Fecha: " + alimento.fechaCaducidad);
    /*        Log.d("AlimentosDB",
                    "Nombre: " + alimento.nombre +
                            " | Fecha: " + alimento.fechaCaducidad +
                            " | Caducado?: " + (alimento.fechaCaducidad == null ? "NULL" :
                            new SimpleDateFormat("dd/MM/yyyy").parse(alimento.fechaCaducidad).before(new Date()))
            );*/
            if (alimento.fechaCaducidad != null && !alimento.fechaCaducidad.isEmpty()) {
                Log.d("CaducidadWorker", "Revisando alimento: " + alimento.nombre + ", fechaCaducidad: " + alimento.fechaCaducidad);
                try {
                    Date fechaAlimento = sdf.parse(alimento.fechaCaducidad);
                    if (fechaAlimento != null &&
                            !fechaAlimento.before(hoySinHora) &&
                            !fechaAlimento.after(fechaLimite)) {
                        alimentosCaducando.append("- ").append(alimento.nombre).append(" (Caduca: ").
                                append(alimento.fechaCaducidad).append(")\n");
                    }
                    // Ejemplo de log adicional para verificar fechas
                    Log.d("FechasDebug", "Alimento: " + alimento.nombre + " | Fecha: " + alimento.fechaCaducidad +
                            " | Válida: " + (fechaAlimento != null && !fechaAlimento.before(hoySinHora) && !fechaAlimento.after(fechaLimite)));
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel canal = new NotificationChannel(canalId, "Notificaciones de caducidad",
                    NotificationManager.IMPORTANCE_HIGH);
            canal.setDescription("Alertas de alimentos por caducar");
            notificationManager.createNotificationChannel(canal);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), canalId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(titulo)
                .setContentText("Pulsa para ver más")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mensaje))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 500, 200, 500})
                .setTimeoutAfter(60000); // 60 segundos; // Patrón de vibración

        notificationManager.notify((int) System.currentTimeMillis(), builder.build()); // ID único
        //notificationManager.notify(1, builder.build());
    }


}
