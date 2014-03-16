/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kururin
 */
public class Ennemie implements Affichable{
 private String nomImg = "Sans titre.png";
    private String imageEnnemie;
    private int vieEnnemie;
    private int positionEnnemie;

    public int getVieEnnemie() {
        return vieEnnemie;
    }

    public void setVieEnnemie(int vieEnnemie) {
        this.vieEnnemie = vieEnnemie;
    }

    public int getPositionEnnemie() {
        return positionEnnemie;
    }

    public void setPositionEnnemie(int positionEnnemie) {
        this.positionEnnemie = positionEnnemie;
    }
    private boolean estVivant;

    public Ennemie( Controleur controleur) throws SlickException {
	controleur.setNouvelleItemAffichable(this);
    }

    public String getNomImg() {
	return nomImg;
    }
}
