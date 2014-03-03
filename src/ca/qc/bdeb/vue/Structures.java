/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import org.newdawn.slick.*;

/**
 *
 * @author Kururin
 */
public class Structures {
    
//    comprendre SlickException....
    private Image face = new Image("litte_baby.jpg");
    private int poid;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }
    private int vie;
    private boolean enVie;
    private String imageBois;

    public Structures() throws SlickException {
    }
}
