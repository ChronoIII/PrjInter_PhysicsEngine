/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.module;

import ca.qc.bdeb.vue.Projectiles;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Module {

    private Vecteur vect = new Vecteur();
    private Projectiles proj = new Projectiles();
    private ArrayList listeProjectiles = new ArrayList();
    private boolean impactSurEnnemie = false;

    public Module() {
    }

    public void trajectoireProjectiles() {
        
//        formules de Zi Long
//        si collision impactSurEnnemie = true
    }

    public void impactEnnemie() {

        if (impactSurEnnemie = true) {
//            alors déplacement de l'ennemie
        }
        
    }
}
