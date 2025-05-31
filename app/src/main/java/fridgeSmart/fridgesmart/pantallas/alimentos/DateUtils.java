package fridgeSmart.fridgesmart.pantallas.alimentos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public static boolean esProximoACaducar(String fecha) {
        if (fecha == null || fecha.isEmpty()) return false;

        try {
            Date fechaCad = dateFormat.parse(fecha);
            Calendar hoy = Calendar.getInstance();
            Calendar finSemana = Calendar.getInstance();
            finSemana.add(Calendar.DAY_OF_YEAR, 7);

            return fechaCad != null &&
                    fechaCad.after(hoy.getTime()) &&
                    fechaCad.before(finSemana.getTime());
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean estaCaducado(String fecha) {
        if (fecha == null || fecha.isEmpty()) return false;

        try {
            Date fechaCad = dateFormat.parse(fecha);
            return fechaCad != null && fechaCad.before(Calendar.getInstance().getTime());
        } catch (ParseException e) {
            return false;
        }
    }

    // En DateUtils.java
    public static int compareDates(String date1, String date2) {
        try {
            Date d1 = dateFormat.parse(date1);
            Date d2 = dateFormat.parse(date2);
            return d1.compareTo(d2);
        } catch (ParseException e) {
            return 0;
        }
    }

}
