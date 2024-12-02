package com.conversordemonedas.Modelos;

import java.util.Map;

public class Moneda {
    
    private final String baseCode;
    private final Map<String, Double> conversionRates;

        public Moneda(String baseCode, Map<String, Double> conversionRates) {
            
            this.baseCode = baseCode;
            this.conversionRates = conversionRates;
            
        }

        public String getBaseCode() {
            
            return baseCode;
            
        }

        public Map<String, Double> getConversionRates() {
            
            return conversionRates;
            
        }

}
