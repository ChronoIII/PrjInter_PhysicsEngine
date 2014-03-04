/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.module.Vecteur;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kururin
 */
public class Projectiles {

    private Image face = new Image("litte_baby.jpg");
    private String imageProj;
    private int vieProj;
    private int x;
    private int y;
    private Vecteur direction;
    
     public Projectiles(int x, int y) throws SlickException {
	 this.x = x;
	 this.y = y;
    }

    public int getVieProj() {
        return vieProj;
    }

    public void setVieProj(int vieProj) {
        this.vieProj = vieProj;
    }

    public boolean isEnVie() {
        return enVie;
    }

    public void setEnVie(boolean enVie) {
        this.enVie = enVie;
    }
    boolean enVie;
    
    public Image getImage(){
	return face;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }
    
}
