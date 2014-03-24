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
public class Structures implements Affichable {

//    comprendre SlickException....
    private String nomImg = "Sans titre.png";
    private int x;
    private int y;
    private int poid;
    private int vie;
    private boolean enVie;

    public Structures() throws SlickException {
    }

    public int getVie() {
	return vie;
    }

    public void setVie(int vie) {
	this.vie = vie;
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public String getNomImg() {
	return nomImg;
    }
}
