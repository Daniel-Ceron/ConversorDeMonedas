package com.conversordemonedas.Modelos;
import java.util.Map;
public class Conversor {
    
    private final String monedaBase;
    private final Map<String, Double> conversionRates;
    
    public Conversor(String monedaBase, Map<String, Double> conversionRates) {
        
        if (monedaBase == null || conversionRates == null) {

            throw new IllegalArgumentException("La moneda base y las tasas de conversión no pueden ser nulos");

        }

            this.monedaBase = monedaBase;
            this.conversionRates = conversionRates;

    }
    
    public Double convert(String targetCurrency, Double amount) {
            
        if (conversionRates.containsKey(targetCurrency)) {

            return amount * conversionRates.get(targetCurrency);

        } else {

            throw new IllegalArgumentException("Moneda objetivo no encontrada: " + targetCurrency);

        }
    }
    
    public String getMonedaBase() {
        
           return monedaBase;
           
       }

    public Map<String, Double> getConversionRates() {
        
           return conversionRates;
           
    }   

}
