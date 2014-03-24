/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.module;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.vue.Projectiles;
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
    private int nbProjectiles = 0;
	    
    private boolean impactSurEnnemie = false;
    private boolean impactSurStructure = false;
    
    private Controleur controleur;
    

    public Module(Controleur controleur) throws SlickException {
	this.controleur = controleur;
	
	listProjectiles = new ArrayList<Projectiles>();
	
    }

    //setters and getters
    public ArrayList<Projectiles> getListProjectiles() {
	return listProjectiles;
    }

    public void setListProjectiles(ArrayList<Projectiles> listeProjectiles) {
	this.listProjectiles = listeProjectiles;
    }

    public int getNbProjectiles() {
	return nbProjectiles;
    }

    public void setNbProjectiles(int nbProjectiles) {
	this.nbProjectiles = nbProjectiles;
    }
    
}
