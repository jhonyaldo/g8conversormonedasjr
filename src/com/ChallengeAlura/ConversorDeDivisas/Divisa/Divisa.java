package com.ChallengeAlura.ConversorDeDivisas.Divisa;

// Este 'record' es la plantilla que define la estructura de los datos que recibimos de la API.
public record Divisa(
        String base_code,
        String target_code,
        double conversion_rate
) {
}