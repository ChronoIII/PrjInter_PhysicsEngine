/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.module.Vecteur;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kururin
 */
public class Projectiles {

    private String imageProj = "Sans titre.png";
    private int vieProj;
    private int x;
    private int y;
    private Vecteur direction;
    private Controleur controleur;
    private Module modele;
    
    public Projectiles(int x, int y, Controleur controleur) throws SlickException {
	 this.x = x;
	 this.y = y;
	 this.controleur = controleur;
	 
	 controleur.addProjectile(imageProj);
    }
     
     //getter and setter
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

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }
    
}
