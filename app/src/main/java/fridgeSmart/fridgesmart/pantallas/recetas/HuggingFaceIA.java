package fridgeSmart.fridgesmart.pantallas.recetas;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class HuggingFaceIA {

    // Cambia este token por el tuyo propio de Hugging Face
    private static final String API_TOKEN = System.getenv("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");

    // URL del modelo en Hugging Face (puedes cambiarlo según el que uses)
    private static final String URL_HUGGINGFACE = "https://api-inference.huggingface.co/models/Alessa04/recetas-ia";

    private static final String MODELO = "gpt2"; // modelo que sí funciona por defecto

    // MediaType JSON para la petición
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public interface RespuestaIAListener {
        void alRecibirRespuesta(String texto);
        void alFallar(String error);
    }

    public static void pedirReceta(String ingredientes, RespuestaIAListener listener) {
        // Construimos el JSON para la petición (según el modelo, puede variar)
        String json = "{\"inputs\":\"Dame una receta con estos ingredientes: " + ingredientes + "\"}";

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(URL_HUGGINGFACE)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.alFallar(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    listener.alFallar("Error en la respuesta: " + response.code());
                    return;
                }

                String respuestaStr = response.body().string();

                // Aquí puedes procesar la respuesta JSON para extraer solo el texto si es necesario
                // Por ejemplo, si la respuesta es un JSON con un array de resultados, puedes parsearlo
                // Para simplificar, devolvemos todo el string tal cual

                listener.alRecibirRespuesta(respuestaStr);
            }
        });
    }
}
