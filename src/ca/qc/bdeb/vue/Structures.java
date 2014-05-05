/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.*;

/**
 *
 * @author Kururin
 */
public class Structures implements Affichable {
//    comprendre SlickException....
    private String nomImg = "inventory.png";
    private Controleur controleur;
    
    
    private Vecteur bound = new Vecteur(76, 63);
    private Vecteur position = new Vecteur();

    public Structures(int posX, int posY, Controleur controleur) throws SlickException {
	position.setX(posX);
	position.setY(posY);
	this.controleur = controleur;

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {
	controleur.enleverStructure(this);
    }

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
