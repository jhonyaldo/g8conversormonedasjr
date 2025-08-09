package com.ChallengeAlura.ConversorDeDivisas.ConsultaApi;

import com.ChallengeAlura.ConversorDeDivisas.Divisa.Divisa;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    public double obtenerTasaDeCambio(String monedaBase, String monedaDestino) {
        // ▼▼▼▼▼ ¡RECUERDA REEMPLAZAR ESTA CLAVE CON TU PROPIA API KEY! ▼▼▼▼▼
        String apiKey = "TU_API_KEY_AQUI";
        // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + monedaBase + "/" + monedaDestino);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            Gson gson = new Gson();
            Divisa divisa = gson.fromJson(json, Divisa.class);

            return divisa.conversion_rate();
        } catch (IOException | InterruptedException e) {
            // Si hay un error, lanzamos una excepción para que la clase Principal la maneje.
            throw new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }
}
