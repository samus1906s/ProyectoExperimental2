/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entidades;

/**
 *
 * @author je110
 */
public enum EstadoContrato {
    ACTIVO("Activo"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");
    
    private String contratoEstado;

    public String getContratoEstado() {
        return contratoEstado;
    }

    private EstadoContrato(String contratoEstado) {
        this.contratoEstado = contratoEstado;
    }
}
