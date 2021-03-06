
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.SlickException;

/**
 * 
 * La méthode pour les petits oiseaux
 */
public class Projectiles implements Affichable {

    //nnomde l'animation
    private String nomImg = "spiritesheet.png";
    private String nomImgReverse = "spiritesheetreverse.png";
    //controleur
    private Controleur controleur;
    //propriétées
    private double gravity = 20;
    private double facRebond;
    private Vecteur bound;
    private Vecteur vitesse = new Vecteur();
    private Vecteur position = new Vecteur();
    private boolean reverse = false;
    private float temps;

    public Projectiles(double posX, double posY, double vitesseIni, double angle, double facRebond, Controleur controleur) throws SlickException {

	this.facRebond = facRebond;
	position.setX(posX);
	position.setY(posY);
	vitesse.setX(vitesseIni * Math.cos(Math.toRadians(angle)));
	vitesse.setY(vitesseIni * Math.sin(Math.toRadians(angle)));
	bound = new Vecteur(68, 54);

	controleur.setNouvelleItemAffichable(this);
    }

    //getter and setter
    public float getTemps() {
	return temps;
    }

    public void setTemps(float temps) {
	this.temps = temps;
    }
    
    public boolean isReverse() {
	return reverse;
    }

    public void setReverse(boolean reverse) {
	this.reverse = reverse;
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

    public String getNomImgReverse() {
	return nomImgReverse;
    }

    public double getGravity() {
	return gravity;
    }

    public void setGravity(double gravity) {
	this.gravity = gravity;
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
