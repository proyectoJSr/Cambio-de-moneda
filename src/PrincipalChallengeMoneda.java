import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalChallengeMoneda {
    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner lectura = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("\n****Cambio de monedas internacionale****");

        while (true) {
            System.out.println("Ingrese moneda a convertir: ");
            String MonedaIngresada = lectura.nextLine();

            System.out.println("Pasar moneda a : ");
            String MonedaEn = lectura.nextLine();

            System.out.println("Monto a convertir");
            double resultado = lectura.nextDouble();
            lectura.nextLine();

            String apikey = "f5f0c41cd907c9d28e1f2276";
            String direccion = "https://v6.exchangerate-api.com/v6/" + apikey + "/pair/" + MonedaIngresada + "/" + MonedaEn;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (json.get("result").getAsString().equals("success")) {
                double tasa = json.get("conversion_rate").getAsDouble();
                double devuelve = resultado * tasa;
                System.out.println("Tasa de cambio: %.4f\n"+ tasa);
                System.out.println("Resultado: %.2f %s\n" +resultado + resultado);
            } else {
                System.out.println("error en tasa de cambio" + json.get("error-type"));
            }
            System.out.println("Desea realizar otra operación (s/n):");
            String continuar = lectura.nextLine();
            if (!continuar.equalsIgnoreCase("s")) ;
        }
    }
    }



















