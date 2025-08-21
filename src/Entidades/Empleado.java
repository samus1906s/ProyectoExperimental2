/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author samue
 */
public class Empleado extends Persona {
    private TipoPuesto trabajo;

    public TipoPuesto getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(TipoPuesto trabajo) {
        if(trabajo != null)
        this.trabajo = trabajo;
    }

    public Empleado(String cedula, String nombre, LocalDate fechaNacimiento, String telefono, String correo) {
        super(cedula, nombre, fechaNacimiento, telefono, correo);
        if(trabajo != null){
         this.trabajo = trabajo;
        }else
        this.trabajo = TipoPuesto.GERENTE;
    }
    
    
}
