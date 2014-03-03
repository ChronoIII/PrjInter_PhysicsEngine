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
public class Catapulte {
     private Image face = new Image("litte_baby.jpg");
    private String imageCata;
    private int energie;

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
    private int angle;
    private boolean estTirer;
    public Catapulte()throws SlickException{}
}
