/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;

/**
 *
 * @author je110
 */
public class Alquiler {
    private int alquilerID;
    private Contrato contrato;
    private EstadoAlquiler estadoalquiler;

    public int getAlquilerID() {
        return alquilerID;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public EstadoAlquiler getEstadoalquiler() {
        return estadoalquiler;
    }

    public void setEstadoalquiler(EstadoAlquiler estadoalquiler) {
        this.estadoalquiler = estadoalquiler;
    }

    public Alquiler(int alquilerID, Contrato contrato) throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        this.alquilerID = alquilerID;
        this.contrato = contrato;
        this.estadoalquiler = EstadoAlquiler.ACTIVO;
        inicializarContrato();
    }
    
    private void inicializarContrato() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion{
        actualizarEstadoVehiculo();
    }
    
    public void iniciarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        if (this.estadoalquiler != EstadoAlquiler.ACTIVO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.contrato.getVehiculo().getEstado() != EstadoVehiculos.DISPONIBLE) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        actualizarEstadoVehiculo();
    }
    
    public void finalizarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        
        if (this.estadoalquiler != EstadoAlquiler.ACTIVO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.contrato.getVehiculo().getEstado() != EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        this.estadoalquiler = EstadoAlquiler.FINALIZADO;
        actualizarEstadoVehiculo();
    }
    
    public void cancelarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        if (this.estadoalquiler == EstadoAlquiler.FINALIZADO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.estadoalquiler == EstadoAlquiler.CANCELADO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        
        this.estadoalquiler = EstadoAlquiler.CANCELADO;
        actualizarEstadoVehiculo();
    }
    
    private void actualizarEstadoVehiculo() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        EstadoVehiculos nuevoEstado = verificarEstadoVehiculo();
        this.contrato.getVehiculo().setEstado(nuevoEstado);
    }
    
    private EstadoVehiculos verificarEstadoVehiculo(){
        switch (estadoalquiler) {
                
        case ACTIVO:
            return EstadoVehiculos.EN_ALQUILER;
                
        case FINALIZADO:
            return EstadoVehiculos.DISPONIBLE;
                
        case CANCELADO:
            return EstadoVehiculos.DISPONIBLE;
                
        default:
            return EstadoVehiculos.DISPONIBLE;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Alquiler alquiler = (Alquiler) obj;
        return alquilerID == alquiler.alquilerID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(alquilerID);
    }
    
}
