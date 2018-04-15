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
public class DatoTriangular {
    private final String nombre;
    private int valor;
    private int maxMin;
    public DatoTriangular(String nombre,int valor, int maxMin){
        this.nombre=nombre;
        this.valor=valor;
        this.maxMin=maxMin;
    }
    public String getNombre(){return nombre;}
    public int getValor(){return valor;}
    public int getMaxMin(){return maxMin;}
    public void setValor(int valor){this.valor=valor;}
    public void setMaxMin(int maxMin){this.maxMin=maxMin;}
    public void setDatoTriangular(int valor, int maxMin){
        this.valor=valor;
        this.maxMin=maxMin;
    }
    public Object[] getForTable(){
        return new Object[]{nombre,valor,maxMin};
    }
    
}
