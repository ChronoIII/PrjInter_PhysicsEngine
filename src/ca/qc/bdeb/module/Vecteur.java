/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.module;

/**
 *
 * @author Kururin
 */
public class Vecteur {
    private Force force = new Force();

    public Force getForce() {
        return force;
    }

    public void setForce(Force force) {
        this.force = force;
    }

    public Vitesse getVit() {
        return vit;
    }

    public void setVit(Vitesse vit) {
        this.vit = vit;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
    private Vitesse vit = new Vitesse();
    private Direction dir = new Direction();
    public Vecteur(){}
}
