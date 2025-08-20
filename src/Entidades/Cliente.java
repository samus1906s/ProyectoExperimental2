/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author samue
 */
public class Cliente {
    private String licenciaconductor;

    public String getLicenciaconductor() {
        return licenciaconductor;
    }

    public void setLicenciaconductor(String licenciaconductor) {
        if(validarLicencias(licenciaconductor))
            this.licenciaconductor = licenciaconductor;
    }

    public Cliente(String licenciaconductor) {
        if(validarLicencias(licenciaconductor)) 
        this.licenciaconductor = licenciaconductor;
    }
    
    public static boolean validarLicencias(String licencias){
        return licencias != null && !licencias.trim().isEmpty();
    }
}
