/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import org.newdawn.slick.SlickException;

public class Controleur {

    private Module module;

    public Controleur() throws SlickException {
	module = new Module(this);
	
    }

    //    public void mouvementProjectile() {
    //        module.trajectoireProjectiles();
    //    }
    //
    //    public void mouvementEnnemie() {
    //        module.trajectoireEnnemie();
    //    }
    //     public void mouvementStructure() {
    //        module.trajectoireStructures();
    //    }
    public Module getModule() {
	return module;
    }
    
    
}
