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
import Excepciones.ReservaExcepciones.VehiculoNoDisponible;
import java.util.Map;
/**
 *
 * @author Eduard Salas Murillo
 */
public class Reserva {
    private int idReserva;
    private Cliente cliente;
    private TipoVehiculo tipoVehiculo;
    private Vehiculos vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoAlquiler estado;

    public int getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }
    
    public void setVehiculo(Vehiculos nuevoVehiculo) {
    this.vehiculo = nuevoVehiculo;
   }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public EstadoAlquiler getEstado() {
        return estado;
    }

    public Reserva(int idReserva, Cliente cliente, TipoVehiculo tipoVehiculo, Vehiculos vehiculo, LocalDate fechaInicio, LocalDate fechaFin, EstadoAlquiler estado, Map<String, Cliente> clientes, Map<Integer, Reserva> reservas, Map<String, Vehiculos> vehiculos) throws VehiculoNoDisponible {
    
        if (cliente == null || !ValidacionGeneral.ClienteRegistrado(cliente.getCedula(), clientes)) {
         //throw new Exception("Cliente no registrado");
        }
    
    if (vehiculo == null || !ValidacionGeneral.VehiculoRegistrado(vehiculo.getPlaca(), vehiculos)) {
        //throw new Exception("Vehículo no registrado");
    }
    
    if (fechaInicio == null || !ValidacionGeneral.FechaInicioValida(fechaInicio)) {
        //throw new Exception("Fecha de inicio inválida");
    }
    
    if (fechaFin == null || !ValidacionGeneral.FechaFinPosterior(fechaInicio, fechaFin)) {
        //throw new Exception("Fecha de fin inválida");
    }
    
    if (!ValidarReservas.DuracionValida(fechaInicio, fechaFin)) {
        //throw new DuracionReservaE(");
    }
    
    if (!ValidarReservas.VehiculoDisponible(vehiculo, fechaInicio, fechaFin, reservas)){
        throw new VehiculoNoDisponible();
    }
    
    this.idReserva = idReserva;
    this.cliente = cliente;
    this.tipoVehiculo = tipoVehiculo;
    this.vehiculo = vehiculo;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.estado = estado;
}
}
