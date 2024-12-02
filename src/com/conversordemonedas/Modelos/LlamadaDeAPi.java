package com.conversordemonedas.Modelos;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Map;

public class LlamadaDeAPi {
    
    public Moneda realizarConsultaDeMoneda(String monedaRegistro){
        
                URI direccion = URI.create("https://v6.exchangerate-api.com/v6/ab28f9dcad3b960c06343b0e/latest/"+monedaRegistro);
                HttpClient client = HttpClient.newHttpClient();
                
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(direccion)
                        .build();
                HttpResponse <String> response;

            try {
                
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                String baseCode = jsonResponse.get("base_code").getAsString();

                Map<String, Double> conversionRates = new Gson().fromJson(jsonResponse.get("conversion_rates"), Map.class);
                return new Moneda(baseCode, conversionRates);

            } catch (Exception e) {

                throw new RuntimeException("No se realizo el proceso" + e.getMessage());

            }
    }
    
    public double convertirMoneda(double monto, double tasaDeCambio) {
        
            return monto* tasaDeCambio;
            
    }
    
}