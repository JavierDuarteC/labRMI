/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

import java.util.Date;

/**
 *
 * @author ALAPTOP
 */
public class Estacion {
    private String nombre;
    private Date ultimoReporte;
    private float tiempoSiguiente;

    public Estacion(String nombre, float tiempoSiguiente) {
        this.nombre = nombre;
        this.tiempoSiguiente = tiempoSiguiente;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public Date getUltimoReporte(){
        return this.ultimoReporte;
    }
    
    public void setUltimoReporte(Date timestamp){
        this.ultimoReporte = timestamp;
    }

    public float getTiempoSiguiente() {
        return tiempoSiguiente;
    }
}
