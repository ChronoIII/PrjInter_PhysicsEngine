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

    //Listes
    private ArrayList<Projectiles> listProjectiles;
    private ArrayList<Structures> listStructures;
    //variable de dimantion
    int width = 1200;
    int height = 675;
    //controlller
    private Controleur controleur;
    

    public Module(Controleur controleur) throws SlickException {

	this.controleur = controleur;

	//Initialiser les liste
	listProjectiles = new ArrayList<Projectiles>();
	listStructures = new ArrayList<Structures>();
    }

    //se que fait le projectiles lorsqu'il touche un surface
    public void rebond(Projectiles projectile, char orientation) {
	if (orientation == 'y') {
	    projectile.getVitesse().setY(-projectile.getVitesse().getY() * projectile.getFacRebond());
	    projectile.getVitesse().setX(projectile.getVitesse().getX() * projectile.getFacRebond());
	    if (projectile.getVitesse().getY() < 1.80 && projectile.getVitesse().getY() > -1.80) {
		projectile.getVitesse().setY(-projectile.getVitesse().getY() * 0.8);
	    }
	    if (projectile.getVitesse().getY() < 0.18 && projectile.getVitesse().getY() > -0.18) {
		projectile.getVitesse().setY(0);
	    }
	}
	if (orientation == 'x') {
	    projectile.getVitesse().setX(-projectile.getVitesse().getX());
	    projectile.setReverse(!projectile.isReverse());
	}
    }
    
    

    //setters and getters
    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

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

    public void sauvegarde(int nbreProjectiles) {
    }

    public void charger() {
    }
}
