/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import Validaciones.ValidacionGeneral;
import Validaciones.ValidarReservas;
import java.time.LocalDate;
import Entidades.Cliente;
import Entidades.Vehiculos;
import java.util.Map;
/**
 *
 * @author Eduard Salas Murillo
 */
public class Reserva {
    private int idReserva;
    private Cliente cedulaCliente;
    private TipoVehiculo tipoVehiculo;
    private Vehiculos placaVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoContrato estado;

    public int getIdReserva() {
        return idReserva;
    }

    public Cliente getCedulaCliente() {
        return cedulaCliente;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public Vehiculos getPlacaVehiculo() {
        return placaVehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    public Reserva(int idReserva, Cliente cedulaCliente, TipoVehiculo tipoVehiculo, Vehiculos placaVehiculo, LocalDate fechaInicio,LocalDate fechaFin, EstadoContrato estado, Map<String, Cliente> clientes, Map<Integer, Reserva> reservas, Map<String, Vehiculos> vehiculos) {
        this.idReserva = idReserva;
        if(ValidacionGeneral.ClienteRegistrado(cedulaCliente, clientes)) 
        this.cedulaCliente = cedulaCliente ;
        if(ValidacionGeneral.VehiculoRegistrado(placaVehiculo, vehiculos))
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placaVehiculo;
        if (ValidacionGeneral.FechaInicioValida(fechaInicio))
        this.fechaInicio = fechaInicio;
        if(ValidacionGeneral.FechaFinPosterior(fechaInicio, fechaFin))
        this.fechaFin = fechaFin;
        if(ValidarReservas.DuracionValida(fechaInicio, fechaFin))
        this.fechaFin =fechaFin;
        if(ValidarReservas.VehiculoDisponible(placaVehiculo, fechaInicio, fechaFin,reservas ))
        this.estado = estado;       
    }
}
