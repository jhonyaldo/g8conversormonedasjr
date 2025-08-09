package com.ChallengeAlura.ConversorDeDivisas.Principal;

import com.ChallengeAlura.ConversorDeDivisas.ConsultaApi.ConsultaApi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaApi consultarApi = new ConsultaApi();

        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("1) Dólar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Sol Peruano");
            System.out.println("6) Sol Peruano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("*************************************************");

            try {
                int opcion = teclado.nextInt();

                if (opcion == 7) {
                    System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                    break;
                }

                String monedaBase = "";
                String monedaDestino = "";

                switch (opcion) {
                    case 1:
                        monedaBase = "USD";
                        monedaDestino = "ARS";
                        break;
                    case 2:
                        monedaBase = "ARS";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaBase = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaBase = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 5:
                        monedaBase = "USD";
                        monedaDestino = "PEN";
                        break;
                    case 6:
                        monedaBase = "PEN";
                        monedaDestino = "USD";
                        break;
                    default:
                        System.out.println("Opción no válida, por favor intente de nuevo.");
                        continue;
                }

                System.out.println("Ingrese el valor que desea convertir:");
                double cantidad = teclado.nextDouble();

                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser un número positivo.");
                    continue;
                }

                double tasa = consultarApi.obtenerTasaDeCambio(monedaBase, monedaDestino);
                double resultado = cantidad * tasa;

                System.out.printf("\n---> El valor de %.2f [%s] corresponde a %.2f [%s] <--- \n",
                        cantidad, monedaBase, resultado, monedaDestino);

            } catch (InputMismatchException e) {
                System.out.println("\nError: Por favor, ingrese solo números.");
                teclado.next();
            } catch (RuntimeException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
        teclado.close();
    }
}