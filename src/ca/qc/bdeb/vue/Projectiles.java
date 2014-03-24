/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kururin
 */
public class Projectiles implements Affichable {

    private String nomImg = "bird.png";
    private int vieProj;
    private double x, y;
    private Controleur controleur;

    public Projectiles(int x, int y, Controleur controleur) throws SlickException {
	this.x = x;
	this.y = y;
	this.controleur = controleur;

	

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {
	controleur.enleverProjectile(this);
    }
    
    double v, angle, anglerad, vxi, vyi, yo, xo, y1, y2;
    boolean droite = true;
    boolean haut = true;
    
    public void mouvement2D(double t) {

	anglerad = angle * Math.PI / 180;//conversion en rad

	if (haut) {
	    vyi = v * Math.sin(anglerad);//vitesse en y
	} else {
	    vyi = v * Math.sin(anglerad - Math.PI);
	}//vitesse en y



	if (droite) {
	    vxi = v * Math.cos(anglerad);//vitesse en x
	} else {
	    vxi = v * Math.cos(anglerad - Math.PI);//vitesse en x
	}





	y = yo + vyi * t + (-9.8 * Math.pow(t, 2) / 2); //position de y pour chaque valeur de t
	x = xo + vxi * t;//position en x pour chaque valeur de t
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

    public void setX(int x) {
	this.x = x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }
    
    

    public String getNomImg() {
	return nomImg;
    }
}
