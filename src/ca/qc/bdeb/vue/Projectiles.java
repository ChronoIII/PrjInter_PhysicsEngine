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
    private double tempsProjectile = 0;

    public Projectiles(int x, int y, Controleur controleur) throws SlickException {
	this.x = x;
	this.y = y;
	this.controleur = controleur;

	v = 75;//vitesse initiale 
	angle = 60;//angle initial en degré

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {
	controleur.enleverProjectile(this);
    }
    double v, angle, anglerad, vxi, vyi, yo, xo, y1, y2;
    boolean droite = true;
    boolean haut = true;

    public void mouvement2D() {

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

	y = 390 - (yo + vyi * tempsProjectile + (-9.8 * Math.pow(tempsProjectile, 2) / 2)); //position de y pour chaque valeur de t
	x = (xo + vxi * tempsProjectile);//position en x pour chaque valeur de t
    }

    //getter and setter
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

    public double getVxi() {
	return vxi;
    }

    public void setVxi(double vxi) {
	this.vxi = vxi;
    }

    public double getVyi() {
	return vyi;
    }

    public void setVyi(double vyi) {
	this.vyi = vyi;
    }

    public double getYo() {
	return yo;
    }

    public void setYo(double yo) {
	this.yo = yo;
    }

    public double getXo() {
	return xo;
    }

    public void setXo(double xo) {
	this.xo = xo;
    }

    public double getY1() {
	return y1;
    }

    public void setY1(double y1) {
	this.y1 = y1;
    }

    public double getY2() {
	return y2;
    }

    public void setY2(double y2) {
	this.y2 = y2;
    }

    public double getTempsProjectile() {
	return tempsProjectile;
    }

    public void setTempsProjectile(double tempsProjectile) {
	this.tempsProjectile = tempsProjectile;
    }

    public boolean isDroite() {
	return droite;
    }

    public void setDroite(boolean droite) {
	this.droite = droite;
    }

    public boolean isHaut() {
	return haut;
    }

    public void setHaut(boolean haut) {
	this.haut = haut;
    }

    public double getV() {
	return v;
    }

    public void setV(double v) {
	this.v = v;
    }

    public String getNomImg() {
	return nomImg;
    }
}
