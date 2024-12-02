package com.conversordemonedas.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    
    private List<String> historial = new ArrayList<>();

    public List<String> getHistorial() {
        return historial;
    }

    public void agregarResultado(String entrada){
        historial.add(entrada);
    }
    
    
    
}
