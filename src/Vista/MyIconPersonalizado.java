/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MyIconPersonalizado implements Icon{
    private final int tamX,tamY;
    private final String dir;
    public MyIconPersonalizado(int x, int y ,String dir){
        this.tamX=x;
        this.tamY=y;
        this.dir=dir;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Image image = new ImageIcon(getClass().getResource(dir)).getImage();
        g.drawImage(image, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return tamX;
    }

    @Override
    public int getIconHeight() {
        return tamY;
    }

}