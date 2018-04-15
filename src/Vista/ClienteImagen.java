/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.Image;
import java.awt.Toolkit;
/**
 *
 * @author Ariel
 */
public class ClienteImagen {
    Toolkit tk;
    //Point os;
    Image abajo, derecha, arriba, izquierda;
    public ClienteImagen(Toolkit tk, String nombre){
        this.tk=tk;
        this.abajo=tk.getImage(getClass().getResource("/Images/clientes/"+nombre+"-abajo.gif"));
        this.derecha=tk.getImage(getClass().getResource("/Images/clientes/"+nombre+"-derecha.gif"));
        this.arriba=tk.getImage(getClass().getResource("/Images/clientes/"+nombre+"-arriba.gif"));
        this.izquierda=tk.getImage(getClass().getResource("/Images/clientes/"+nombre+"-izquierda.gif"));
    }
    public Image getAbajo(){return abajo;}
    public Image getDerecha(){return derecha;}
    public Image getArriba(){return arriba;}
    public Image getIzquierda(){return izquierda;}
}
