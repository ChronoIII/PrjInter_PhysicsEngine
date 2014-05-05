/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Kururin
 */
public class Projectiles implements Affichable {

    private String nomImg = "spiritesheet.png";
    private Controleur controleur;
    
    
    private double gravity = 9.8;
    private double masse = 1;
    private double facRebond;
    private Vecteur bound;
    private Vecteur vitesse = new Vecteur();
    private Vecteur position = new Vecteur();
    private double angle;


    public Projectiles(double posX, double posY, double vitesseIni, double angle, double facRebond, Controleur controleur) throws SlickException {
	
	this.angle = angle;
	this.facRebond = facRebond;
	position.setX(posX);
	position.setY(posY);
	vitesse.setX(vitesseIni * Math.cos(Math.toRadians(angle)));
	vitesse.setY(vitesseIni * Math.sin(Math.toRadians(angle)));
	bound = new Vecteur(68, 54);

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {

        controleur.enleverProjectiles(this);
    }

    //getter and setter
    public double getAngle() {
	return angle;
    }

    public void setAngle(double angle) {
	this.angle = angle;
    }
    
    
    public Vecteur getBound() {
	return bound;
    }

    public void setBound(Vecteur bound) {
	this.bound = bound;
    }
    
    public String getNomImg() {
        return nomImg;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public double getFacRebond() {
        return facRebond;
    }

    public void setFacRebond(double facRebond) {
        this.facRebond = facRebond;
    }

    public Vecteur getVitesse() {
        return vitesse;
    }

    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }

    public Vecteur getPosition() {
        return position;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }
}
