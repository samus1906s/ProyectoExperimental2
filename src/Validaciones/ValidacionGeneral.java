/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import Entidades.Cliente;
import Entidades.Vehiculos;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Eduard Salas Murillo
 */
public class ValidacionGeneral {
    
   public static boolean ClienteRegistrado(String cedula, Map<String,Cliente> clientes) {
        return cedula != null && clientes.containsKey(cedula);
   }

    public static boolean VehiculoRegistrado(String placa, Map<String,Vehiculos> vehiculos) {
        return vehiculos != null && vehiculos.containsKey(placa);
    }

    public static boolean FechaInicioValida(LocalDate fechaInicio) {
        return fechaInicio != null && !fechaInicio.isBefore(LocalDate.now());
    }
    
    public static boolean FechaFinPosterior(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaInicio != null && fechaFin != null && fechaFin.isAfter(fechaInicio);
    }
    
    public static boolean FechasDeRangoValidas(LocalDate fechaInicio1, LocalDate fechaFin1, LocalDate fechaInicio2, LocalDate fechaFin2) {
        return !fechaFin1.isBefore(fechaInicio2) && !fechaInicio1.isAfter(fechaFin2);
    }
    
    
}

