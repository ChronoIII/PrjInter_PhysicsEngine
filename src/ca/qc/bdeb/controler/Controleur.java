/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;

public class Controleur {

    private Module mod = new Module();

    public Controleur() {
    }

    public void mouvementProjectile() {
        mod.trajectoireProjectiles();
    }

    public void mouvementEnnemie() {
        mod.impactEnnemie();
    }
}
