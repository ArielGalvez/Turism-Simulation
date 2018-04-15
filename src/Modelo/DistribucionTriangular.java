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
public class DistribucionTriangular {
    
    /* a<b<c*/
    public int distribucionTrianguar(int a, int b, int c){
        int x;
        float R1,R2;
        R1=(float)Math.random();//System.out.print(R1);
        R2=(float)Math.random();
        if(R2<=(0.5))//(b-a)/(c-a))
            x=(int)(a+(b-a)*Math.sqrt(R1));
        else
            x=(int)(c-(c-b)*Math.sqrt(R1));//1-R1
        return x;
    }
    public int generar(int valor, int maxMin){
        return distribucionTrianguar(valor-maxMin, valor, valor+maxMin);
    }
    public void main(String args[]){
        for (int i = 1; i <= 20; i++) {
            int dt=generar(100, 50);//distribucionTrianguar(60, 120, 180);
            System.out.println(" "+i+".- "+dt);
            //System.out.println((Math.sqrt(25)));
        }
    }
}
