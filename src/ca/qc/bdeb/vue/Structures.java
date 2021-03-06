
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.*;
/**
 * 
 * La structure qui permet de faire rebondir les projectiles qui frappent dessus
 */

public class Structures implements Affichable {

    //nom de l'image
    private String nomImg = "structure.png";
    //le controleur
    private Controleur controleur;
    //propriétées
    private Vecteur bound = new Vecteur(100, 100);
    private Vecteur position = new Vecteur();
    
    private boolean estPlacer=false;

  
    public Structures(int posX, int posY, Controleur controleur) throws SlickException {
	position.setX(posX);
	position.setY(posY);
	this.controleur = controleur;

	controleur.setNouvelleItemAffichable(this);
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
     public void setEstPlacer(boolean estPlacer) {
        this.estPlacer = estPlacer;
    }
      public boolean getEstPlacer() {
        return estPlacer;
    }
    
    public Vecteur getPosition() {
	return position;
    }

    public void setPosition(Vecteur position) {
	this.position = position;
    }
    
     

}
