/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestiones;

import Entidades.Alquiler;
import Interfaces.Listas;
import java.util.HashMap;

/**
 *
 * @author je110
 */
public class GestorAlquileresHashMap implements Listas <Alquiler> {
    HashMap<String,Alquiler> alquileres;

    public GestorAlquileresHashMap(HashMap<String, Alquiler> alquileres) {
        this.alquileres = new HashMap<>();
    }

    public HashMap<String, Alquiler> getAlquileres() {
        return alquileres;
    }
    
    @Override
    public boolean agregar(Alquiler t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Alquiler t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Alquiler buscar(Object id) {
        String contratoId = String.valueOf(id);
        return alquileres.get(contratoId);
    }
    
}
