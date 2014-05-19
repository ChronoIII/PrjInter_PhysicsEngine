/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.SlickException;
/**
 * 
 * Méthode qui contient les paramètres des cibles
 */

public class Cibles implements Affichable {

    //nnomde l'animation
    private String nomImg = "heartsprite.png";
    //controleur
    private Controleur controleur;
    //propriétées
    private Vecteur bound;
    private Vecteur position = new Vecteur();
    
    public Cibles(int posX, int posY, Controleur controleur) throws SlickException {
	position.setX(posX);
	position.setY(posY);
	this.controleur = controleur;

	controleur.setNouvelleItemAffichable(this);
    }
    
    public void detruir() {
	controleur.enleverCible(this);
    }

    //getters and setters
    public String getNomImg() {
	return nomImg;
    }

    public Vecteur getBound() {
	return bound;
    }

    public void setBound(Vecteur bound) {
	this.bound = bound;
    }
    
    public Vecteur getPosition() {
	return position;
    }

    public void setPosition(Vecteur position) {
	this.position = position;
    }
}
