/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestiones;

import Entidades.Alquiler;
import Entidades.EstadoAlquiler;
import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;
import Interfaces.Listas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author je110
 */
public class GestorAlquileresHashMap implements Listas <Alquiler> {
    HashMap<String, Alquiler> alquileres;
    
    public GestorAlquileresHashMap() {
        this.alquileres = new HashMap<>();
    }
    
    public HashMap<String, Alquiler> getAlquileres() {
        return alquileres;
    }
    
    @Override
    public boolean agregar(Alquiler alquiler) {
        if (alquiler != null && alquiler.getAlquilerID() != null) {
            if (!alquileres.containsKey(alquiler.getAlquilerID())) {
                alquileres.put(alquiler.getAlquilerID(), alquiler);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean eliminar(Alquiler alquiler) {
        if (alquiler != null && alquiler.getAlquilerID() != null) {
            Alquiler eliminado = alquileres.remove(alquiler.getAlquilerID());
            return eliminado != null;
        }
        return false;
    }
    
    @Override
    public Alquiler buscar(Object id) {
        if (id != null) {
            String alquilerId = String.valueOf(id);
            return alquileres.get(alquilerId);
        }
        return null;
    }
    
    public boolean cancelarAlquiler(String alquilerID) {
        Alquiler alquiler = buscar(alquilerID);
        if (alquiler != null) {
            try {
                alquiler.cancelarAlquiler();
                return true; 
            } catch (TransicionEstadoNoPermitidoExcepcion | EstadoInvalidoExcepcion e) {
                return false; 
            }
        }  
        return false; 
    }
    
    public boolean finalizarAlquiler(String alquilerID) {
        Alquiler alquiler = buscar(alquilerID);
        if (alquiler != null) {
            try {
                alquiler.finalizarAlquiler();
                return true;
            } catch (TransicionEstadoNoPermitidoExcepcion | EstadoInvalidoExcepcion e) {
                return false;
            }
        }
        return false;
    }
    
    public boolean existeAlquilerActivoEnRango(String placaVehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Alquiler alquiler : alquileres.values()) {
            if (alquiler.getVehiculo() != null && 
                alquiler.getVehiculo().getPlaca().equals(placaVehiculo) &&
                alquiler.getEstadoAlquiler() == EstadoAlquiler.ACTIVO) {
                
                if (!(fechaFin.isBefore(alquiler.getFechaInicial()) || 
                      fechaInicio.isAfter(alquiler.getFechaFinal()))) {
                    return true; 
                }
            }
        }
        return false;
    }
    
    public ArrayList<Alquiler> obtenerContratosVigentes() {
        ArrayList<Alquiler> vigentes = new ArrayList<>();
        
        for (Alquiler alquiler : alquileres.values()) {
            if (alquiler != null && alquiler.contratoVigente()) {
                vigentes.add(alquiler);
            }
        }
        
        return vigentes;
    }
    
}
