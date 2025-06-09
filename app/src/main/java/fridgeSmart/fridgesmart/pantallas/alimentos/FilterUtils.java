package fridgeSmart.fridgesmart.pantallas.alimentos;

import java.util.ArrayList;
import java.util.List;


import fridgeSmart.fridgesmart.R;
import fridgeSmart.fridgesmart.comun.repositorio.db.AlimentoDb;

public class FilterUtils {

    public static List<AlimentoDb> filterByDate(List<AlimentoDb> list, int selectedFilter, String selectedDate) {
        List<AlimentoDb> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (AlimentoDb item : list) {
            if (selectedFilter == R.id.filterExpiringSoon) {
                if (DateUtils.esProximoACaducar(item.fechaCaducidad)) {
                    result.add(item);
                }
            } else if (selectedFilter == R.id.filterExpired) {
                if (DateUtils.estaCaducado(item.fechaCaducidad)) {
                    result.add(item);
                }
            } else if (selectedFilter == R.id.filterSpecificDate) {
                if (selectedDate != null && !selectedDate.isEmpty() &&
                        selectedDate.equals(item.fechaCaducidad)) {
                    result.add(item);
                }
            } else { // Todos los casos no cubiertos (incluye R.id.filterAllDates)
                result.add(item);
            }
        }
        return result;
    }

    public static List<AlimentoDb> filterByStock(List<AlimentoDb> list, String stockFilter) {
        List<AlimentoDb> result = new ArrayList<>();

        if (list == null || stockFilter == null) {
            return result;
        }

        for (AlimentoDb item : list) {
            int cantidad = item.cantidad;

            switch (stockFilter) {
                case "Stock bajo (<2 unidades)":
                    if (cantidad < 2) result.add(item);
                    break;
                case "Stock medio (2-5 unidades)":
                    if (cantidad >= 2 && cantidad <= 5) result.add(item);
                    break;
                case "Stock alto (>5 unidades)":
                    if (cantidad > 5) result.add(item);
                    break;
                default: // "Todos" o cualquier otro caso
                    result.add(item);
            }
        }
        return result;
    }

}
