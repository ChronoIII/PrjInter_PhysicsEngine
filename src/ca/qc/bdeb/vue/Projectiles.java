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

    private String nomImg = "animation2.png";
    private Controleur controleur;
    
    private double gravity = 9.8;
    private double masse = 1;
    private double facRebond;
    private Vecteur bound;
    private Vecteur vitesse = new Vecteur();
    private Vecteur position = new Vecteur();


    public Projectiles(double posX, double posY, double vitesseIni, double angle, double facRebond, Controleur controleur) throws SlickException {
	
	this.facRebond = facRebond;
	position.setX(posX);
	position.setY(posY);
	vitesse.setX(vitesseIni * Math.cos(Math.toRadians(angle)));
	vitesse.setY(vitesseIni * Math.sin(Math.toRadians(angle)));

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {

        controleur.enleverProjectiles(this);
    }

    //getter and setter
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
