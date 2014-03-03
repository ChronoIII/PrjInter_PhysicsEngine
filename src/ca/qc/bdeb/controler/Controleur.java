/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import org.newdawn.slick.SlickException;

public class Controleur {

    private Module mod = new Module();

    public Controleur() throws SlickException {
    }

    public void mouvementProjectile() {
        mod.trajectoireProjectiles();
    }

    public void mouvementEnnemie() {
        mod.trajectoireEnnemie();
    }
     public void mouvementStructure() {
        mod.trajectoireStructures();
    }
}
