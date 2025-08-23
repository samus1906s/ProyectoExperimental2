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
import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;
import Validaciones.ValidacionGeneral;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author je110
 */
public class Alquiler {
    
    private String alquilerID;
    private EstadoAlquiler estadoAlquiler;
    private Cliente cliente;
    private Vehiculos vehiculo;
    private Reserva reserva;
    private LocalDate fechaInicial, fechaFinal;
    private double tarifaDiaria;
    private double montoTotal;
    private int dias;

    public String getAlquilerID() {
        return alquilerID;
    }

    public EstadoAlquiler getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public Reserva getReserva() {
        return reserva;
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

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void setFechaInicial(LocalDate fechaInicial) throws FechaInvalidaExcepcion {
        if (fechaInicial == null) {
            throw new FechaInvalidaExcepcion();
        }
        if (fechaFinal != null && fechaInicial.isAfter(fechaFinal)) {
            throw new FechaInvalidaExcepcion();
        }
        this.fechaInicial = fechaInicial;
        recalcularAlquiler();
    }

    public void setFechaFinal(LocalDate fechaFinal) throws FechaInvalidaExcepcion {
        if (fechaFinal == null) {
            throw new FechaInvalidaExcepcion();
        }
        if (fechaInicial != null && fechaFinal.isBefore(fechaInicial)) {
            throw new FechaInvalidaExcepcion();
        }
        this.fechaFinal = fechaFinal;
        recalcularAlquiler();
    }

    public void setTarifaDiaria(double tarifaDiaria) throws TarifaNoValidaExcepcion {
        if (tarifaDiaria <= 0) {
            throw new TarifaNoValidaExcepcion();
        }
        this.tarifaDiaria = tarifaDiaria;
        recalcularAlquiler();
    }

    public Alquiler(String alquilerID, Cliente cliente, Vehiculos vehiculo, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria, Map<String, Cliente> clientes, Map<String, Vehiculos> vehiculos) throws FechaInvalidaExcepcion, ContratoNoValidoExcepcion, TarifaNoValidaExcepcion, ClienteNoRegistradoExcepcion, VehiculoNoRegistradoExcepcion, TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
    
        validarParametrosConstructor(alquilerID, cliente, vehiculo, fechaInicial, fechaFinal, tarifaDiaria, clientes, vehiculos);
    
        this.alquilerID = alquilerID;
        this.estadoAlquiler = EstadoAlquiler.ACTIVO;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.reserva = null;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tarifaDiaria = tarifaDiaria;
    
        calcularDias_Y_Monto();
        inicializarContrato();
    }

    private void calcularDias_Y_Monto() {
        this.dias = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
        if (this.dias == 0) {
            this.dias = 1;
        }
        this.montoTotal = tarifaDiaria * dias;
    }

    private void recalcularAlquiler() {
        if (fechaInicial != null && fechaFinal != null && tarifaDiaria > 0) {
            calcularDias_Y_Monto();
        }
    }

    public boolean contratoVigente() {
        LocalDate fechaActual = LocalDate.now();
        return !fechaActual.isBefore(fechaInicial) && !fechaActual.isAfter(fechaFinal);
    }

    private void inicializarContrato() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        actualizarEstadoVehiculo();
    }

    public void iniciarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        if (this.estadoAlquiler != EstadoAlquiler.ACTIVO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.vehiculo.getEstado() != EstadoVehiculos.DISPONIBLE) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        actualizarEstadoVehiculo();
    }

    public void finalizarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        if (this.estadoAlquiler != EstadoAlquiler.ACTIVO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.vehiculo.getEstado() != EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        this.estadoAlquiler = EstadoAlquiler.FINALIZADO;
        actualizarEstadoVehiculo();
    }

    public void cancelarAlquiler() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        if (this.estadoAlquiler == EstadoAlquiler.FINALIZADO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        if (this.estadoAlquiler == EstadoAlquiler.CANCELADO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        
        this.estadoAlquiler = EstadoAlquiler.CANCELADO;
        actualizarEstadoVehiculo();
    }

    private void actualizarEstadoVehiculo() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        EstadoVehiculos nuevoEstado = verificarEstadoVehiculo();
        this.vehiculo.setEstado(nuevoEstado);
    }

    private EstadoVehiculos verificarEstadoVehiculo() {
        switch (estadoAlquiler) {
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

    private void validarParametrosConstructor(String alquilerID, Cliente cedulaCliente, Vehiculos placaVehiculo, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria, Map<String, Cliente> clientes, Map<String, Vehiculos> vehiculos) throws ContratoNoValidoExcepcion, FechaInvalidaExcepcion, TarifaNoValidaExcepcion, ClienteNoRegistradoExcepcion, VehiculoNoRegistradoExcepcion {

    if (alquilerID == null || alquilerID.trim().isEmpty()) {
        throw new ContratoNoValidoExcepcion();
    }

    if (cliente == null || !ValidacionGeneral.ClienteRegistrado(cedulaCliente, clientes)) {
        throw new ClienteNoRegistradoExcepcion();
    }

    if (vehiculo == null || !ValidacionGeneral.VehiculoRegistrado(placaVehiculo, vehiculos)) {
        throw new VehiculoNoRegistradoExcepcion();
    }

    if (fechaInicial == null || fechaFinal == null) {
        throw new FechaInvalidaExcepcion();
    }
    
    if (!ValidacionGeneral.FechaInicioValida(fechaInicial)) {
        throw new FechaInvalidaExcepcion();
    }
    
    if (!ValidacionGeneral.FechaFinPosterior(fechaInicial, fechaFinal)) {
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
        
        Alquiler alquiler = (Alquiler) obj;
        return alquilerID.equals(alquiler.alquilerID);
    }

    @Override
    public int hashCode() {
        return alquilerID.hashCode();
    }
}
