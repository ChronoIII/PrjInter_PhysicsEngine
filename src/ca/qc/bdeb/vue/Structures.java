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
    private String nomImg = "Sans titre.png";
    private double x, y;
    private Controleur controleur;

    public Structures(int x, int y, Controleur controleur) throws SlickException {
	this.x = x;
	this.y = y;
	this.controleur = controleur;

	controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {
	controleur.enleverStructure(this);
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
   
    public void setX(double x) {
	this.x = x;
    }
}
