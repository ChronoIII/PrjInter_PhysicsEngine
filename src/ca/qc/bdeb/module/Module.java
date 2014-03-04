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
    private ArrayList<Projectiles> listeProjectiles = new ArrayList<Projectiles>();
    private boolean impactSurEnnemie = false;
    private boolean impactSurStructure = false;
    private Controleur controleur;

    public Module(Controleur controleur) throws SlickException {
	this.controleur = controleur;
	
    }
    
    public void ajouterProjectiles(int x, int y) throws SlickException{
	listeProjectiles.add(new Projectiles(x, y));
    }

    //    public void trajectoireProjectiles() {
    ////        formules de Zi Long
    ////        si collision
    //		impactSurEnnemie = true
    //    }
    //
    //    public void trajectoireEnnemie() {
    //
    //        if (impactSurEnnemie == true) {
    ////            alors déplacement de l'ennemie
    ////            enleve la vie + perd de la vie pour chaque collision
    //        }
    //
    //    }
    //
    //    public void trajectoireStructures() {
    //        if (impactSurStructure == true) {
    //
    ////            très similaire à Ennemie
    //        }
    //
    //    }
    //setters and getters
    public ArrayList<Projectiles> getListeProjectiles() {
	return listeProjectiles;
    }

    public void setListeProjectiles(ArrayList<Projectiles> listeProjectiles) {
	this.listeProjectiles = listeProjectiles;
    }
}
