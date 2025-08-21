/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author je110
 */
public abstract class ValidarPersona {
    
    public static int calculateAge(LocalDate date){
        return Period.between(date, LocalDate.now()).getYears();
    }
    
    public static boolean isLegalAge(LocalDate date){
        return calculateAge(date) >= 18;
    }
  
    public static boolean validarEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    public static boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}"); 
    }
    
    public static boolean validarCedula(String cedula) {
        return cedula.matches("\\d{9}"); 
    }
}

