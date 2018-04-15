/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Ariel
 */
public class EmpleadosPorHorario {
    private String horaIni;
    private String horaFin;
    private int cantidad;
    private boolean ocupado[];
    public EmpleadosPorHorario(String horaIni, String horaFin, int cantidad){
        this.horaIni=horaIni;
        this.horaFin=horaFin;
        this.cantidad=cantidad;
        ocupado=new boolean[cantidad];
    }
    public void setAll(String horaIni, String horaFin, int cantidad){
        this.horaIni=horaIni;
        this.horaFin=horaFin;
        this.cantidad=cantidad;
        ocupado=new boolean[cantidad];
    }
    public String getHoraIni(){return horaIni;}
    public String getHoraFin(){return horaFin;}
    public int getCantidad(){return cantidad;}
    public boolean estaOcupado(int pos){return ocupado[pos];}
    public void Ocupar(int pos){ocupado[pos]=true;}
    public void Desocupar(int pos){ocupado[pos]=false;}
}
