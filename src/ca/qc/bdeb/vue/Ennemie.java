/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Kururin
 */
public class Ennemie {
 private Image face = new Image("litte_baby.jpg");
    private String imageEnnemie;
    private int vieEnnemie;
    private int positionEnnemie;

    public int getVieEnnemie() {
        return vieEnnemie;
    }

    public void setVieEnnemie(int vieEnnemie) {
        this.vieEnnemie = vieEnnemie;
    }

    public int getPositionEnnemie() {
        return positionEnnemie;
    }

    public void setPositionEnnemie(int positionEnnemie) {
        this.positionEnnemie = positionEnnemie;
    }
    private boolean estVivant;

    public Ennemie() throws SlickException {
    }
}
