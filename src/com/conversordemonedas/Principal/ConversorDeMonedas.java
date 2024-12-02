package com.conversordemonedas.Principal;

import com.conversordemonedas.Modelos.Moneda;
import com.conversordemonedas.Modelos.LlamadaDeAPi;
import com.conversordemonedas.Modelos.Historial;
import java.util.Scanner;

public class ConversorDeMonedas {

    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
            
        LlamadaDeAPi consulta = new LlamadaDeAPi();
        Historial historial = new Historial();
        
        System.out.println("""
                           *************************************************************************
                                                            ¡Bienvenido!

                                       Por favor, ingresa la moneda que deseas convertir.
                                       Puedes usar códigos como: 
                                       1. 'COP' para pesos colombia1nos 
                                       2. 'USD' para dólares estadounidenses
                                       3. 'EUR' para euros
                                       etc.

                           *************************************************************************
                                        """);
        
        boolean continuar = true;  // Variable para controlar el ciclo

        while (continuar) {  // El ciclo se repite mientras "continuar" sea verdadero

            try {
                System.out.print("Entrada: ");
                String ingresoDeMoneda = teclado.nextLine();
                
                String cambioDeMayusculaIngresoDeMoneda = ingresoDeMoneda.toUpperCase();
    
                Moneda conversor = consulta.realizarConsultaDeMoneda(cambioDeMayusculaIngresoDeMoneda);
                System.out.println("Has seleccionado: " + cambioDeMayusculaIngresoDeMoneda);
    
                System.out.println("Ingrese la cantidad a convertir: ");
                Long monto = teclado.nextLong();
    
                teclado.nextLine();  // Consumir el salto de línea
    
                System.out.println("Ingrese a que moneda desea convertir el valor ingresado anteriormente");
                String cambioDeMonedaValor = teclado.nextLine();
                
                String monedaDestino = cambioDeMonedaValor.toUpperCase();
    
                if (conversor.getConversionRates() != null && conversor.getConversionRates().containsKey(monedaDestino)) {
                    
                    double tasaDeCambio = conversor.getConversionRates().get(monedaDestino);
                    double resultado = consulta.convertirMoneda(monto, tasaDeCambio);
                    
                    System.out.println("El resultado de la conversión de " + monto + " " + ingresoDeMoneda 
                                                + " a " + cambioDeMonedaValor + " es igual a: " + resultado 
                                                + " " + cambioDeMonedaValor);
                    
                    // Agregar al historial
                    String entradaHistorial = "Convertido " + monto + " " + ingresoDeMoneda + " a " 
                                              + resultado + " " + cambioDeMonedaValor;
                    historial.agregarResultado(entradaHistorial);
                    
                    System.out.println("""
                                            ¿Desea ver el historial de conversion?
                                       SI
                                       NO
                                       """);
                    String opcionVisualizarHistorial = teclado.nextLine();
                    if (opcionVisualizarHistorial.equalsIgnoreCase("si")) {
                        System.out.println("HISTORIAL:");
                        System.out.println(historial.getHistorial());
                    }
                    
                } else {
                    System.out.println("La moneda de destino no es válida o no está disponible.");
                }
    
            } catch (Exception e) {
                System.out.println("Ha surgido un error!");
            }
            
            // Preguntar si el usuario desea realizar otra conversión o salir
            System.out.println("""
                                            ¿Desea realizar otra conversión?
                               SI
                               NO
                               """);
            String continuarOpcion = teclado.nextLine();
            
            if (continuarOpcion.equalsIgnoreCase("no")) {
                
                continuar = false;  // Si el usuario escribe "no", el programa finaliza
                System.out.println("Gracias por usar el conversor de monedas. ¡Adiós!");
                
            }
            
        }
    }
}
