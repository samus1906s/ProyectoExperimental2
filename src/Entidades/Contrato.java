/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Excepciones.ContratoExcepciones.ClienteNoRegistradoExcepcion;
import Excepciones.ContratoExcepciones.ContratoNoValidoExcepcion;
import Excepciones.ContratoExcepciones.FechaInvalidaExcepcion;
import Excepciones.ContratoExcepciones.TarifaNoValidaExcepcion;
import Excepciones.ContratoExcepciones.VehiculoNoRegistradoExcepcion;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author je110
 */
public class Contrato {
    private String contratoID;
    private Cliente cliente;
    private Vehiculos vehiculo;
    private LocalDate fechaInicial, fechaFinal;
    private double tarifaDiaria;
    private double montoTotal;
    private int dias;

    public String getContratoID() {
        return contratoID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public int getDias() {
        return dias;
    }

    public void setFechaInicial(LocalDate fechaInicial) throws FechaInvalidaExcepcion {
        if (fechaInicial == null){
            throw new FechaInvalidaExcepcion();
        }
        if (fechaFinal != null && fechaInicial.isAfter(fechaFinal)) {
            throw new FechaInvalidaExcepcion();
        }
        this.fechaInicial = fechaInicial;
        recalcularContrato();
    }

    public void setFechaFinal(LocalDate fechaFinal) throws FechaInvalidaExcepcion {
        if (fechaFinal == null) {
            throw new FechaInvalidaExcepcion();
        }
        if (fechaInicial != null && fechaFinal.isBefore(fechaInicial)) {
            throw new FechaInvalidaExcepcion();
        }
        this.fechaFinal = fechaFinal;
        recalcularContrato();
    }

    public void setTarifaDiaria(double tarifaDiaria) throws TarifaNoValidaExcepcion { 
        if (tarifaDiaria <= 0){
            throw new TarifaNoValidaExcepcion();
        }
        this.tarifaDiaria = tarifaDiaria;
        recalcularContrato();
    }

    public Contrato(String contratoID, Cliente cliente, Vehiculos vehiculo, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria) throws FechaInvalidaExcepcion, ContratoNoValidoExcepcion, TarifaNoValidaExcepcion, ClienteNoRegistradoExcepcion, VehiculoNoRegistradoExcepcion {
        ValidarParametrosConstructor(contratoID, cliente, vehiculo, fechaInicial, fechaFinal, tarifaDiaria);
        this.contratoID = contratoID;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tarifaDiaria = tarifaDiaria;
         calcularDias_Y_Monto();
    }
    
    private void calcularDias_Y_Monto(){
        this.dias = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        if (this.dias == 0){
            this.dias = 1;
        }
        this.montoTotal = tarifaDiaria * dias;
    }
    
    private void recalcularContrato() {
        if (fechaInicial != null && fechaFinal != null && tarifaDiaria > 0) {
            calcularDias_Y_Monto();
        }
    }
    
    public boolean contratoVigente() {
        LocalDate fechaActual = LocalDate.now();
        return !fechaActual.isBefore(fechaInicial) && !fechaActual.isAfter(fechaFinal);
    }
    
    private void ValidarParametrosConstructor(String contratoID, Cliente cliente, Vehiculos vehiculo, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria) throws ContratoNoValidoExcepcion, FechaInvalidaExcepcion, TarifaNoValidaExcepcion, ClienteNoRegistradoExcepcion, VehiculoNoRegistradoExcepcion{
        
        if (contratoID == null || contratoID.trim().isEmpty()) {
            throw new ContratoNoValidoExcepcion();
        }
        
        if (cliente == null) {
            throw new ClienteNoRegistradoExcepcion();
        }
        
        if (vehiculo == null) {
            throw new VehiculoNoRegistradoExcepcion();
        }
        
        if (fechaInicial == null || fechaFinal == null){
            throw new FechaInvalidaExcepcion();
        }
        
        if (fechaFinal.isBefore(fechaInicial)){
            throw new FechaInvalidaExcepcion();
        }
        
        if (tarifaDiaria <= 0) {
            throw new TarifaNoValidaExcepcion();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Contrato contrato = (Contrato) obj;
        return contratoID.equals(contrato.contratoID);
    }

    @Override
    public int hashCode() {
        return contratoID.hashCode();
    }
}
