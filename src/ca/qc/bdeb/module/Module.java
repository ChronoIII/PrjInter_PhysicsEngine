/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.module;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.vue.Projectiles;
import ca.qc.bdeb.vue.Structures;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Samuel
 */
public class Module {

//    private Vecteur vect = new Vecteur();
//    private Projectiles proj = new Projectiles();
    private ArrayList<Projectiles> listProjectiles;
    private ArrayList<Structures> listStructures;
//    private boolean impactSurEnnemie = false;
//    private boolean impactSurStructure = false;
    private double gravite = -9.8;
    private Controleur controleur;

    public Module(Controleur controleur) throws SlickException {
	this.controleur = controleur;

	listProjectiles = new ArrayList<Projectiles>();
	listStructures = new ArrayList<Structures>();
    }

//    public void mouvement2D(Projectiles projectile) {
//
//	projectile.setAnglerad(projectile.getAngle() * Math.PI / 180);//conversion en rad
//
//	if (projectile.isHaut()) {
//	    projectile.setVyi(projectile.getV() * Math.sin(projectile.getAnglerad())); //vitesse en y
//	} else {
//	    projectile.setVyi(projectile.getV()* Math.sin(projectile.getAnglerad() - Math.PI)); 
//	}//vitesse en y
//
//	if (projectile.isDroite()) {
//	    projectile.setVxi(projectile.getV() * Math.cos(projectile.getAnglerad()));//vitesse en x
//	} else {
//	    projectile.setVxi(projectile.getV() * Math.cos(projectile.getAnglerad() - Math.PI));//vitesse en x
//	}
//
//	projectile.setY( 500 - (projectile.getYo() + projectile.getVyi() * projectile.getTempsProjectile() + (gravite * Math.pow(projectile.getTempsProjectile(), 2) / 2))); //position de y pour chaque valeur de t
//	projectile.setX(projectile.getXo() + projectile.getVxi() * projectile.getTempsProjectile());//position en x pour chaque valeur de t
//    }
    public void rebond(Projectiles projectile, char a) {
	if (a == 'a') {
	    projectile.getVitesse().setY(-projectile.getVitesse().getY() * projectile.getFacRebond());
	    projectile.getVitesse().setX(projectile.getVitesse().getX() * projectile.getFacRebond());
	    if (projectile.getVitesse().getY() < 1.80 && projectile.getVitesse().getY() > -1.80) {
		projectile.getVitesse().setY(-projectile.getVitesse().getY() * 0.8);
	    }
	    if (projectile.getVitesse().getY() < 0.18 && projectile.getVitesse().getY() > -0.18) {
		projectile.getVitesse().setY(0);
		System.out.println("yo");
	    }
	    System.out.println("VitesseYYY= " + projectile.getVitesse().getY());

	}
	if (a == 'b') {
	    projectile.getVitesse().setX(-projectile.getVitesse().getX());

	    System.out.println("VitesseXXX= " + projectile.getVitesse().getX());
	}
    }

    //setters and getters
    public ArrayList<Projectiles> getListProjectiles() {
	return listProjectiles;
    }

    public void setListProjectiles(ArrayList<Projectiles> listeProjectiles) {
	this.listProjectiles = listeProjectiles;
    }

    public ArrayList<Structures> getListStructures() {
	return listStructures;
    }

    public void setListStructures(ArrayList<Structures> listStructures) {
	this.listStructures = listStructures;
    }
}
