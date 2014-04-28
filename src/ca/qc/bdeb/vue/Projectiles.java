/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Kururin
 */
public class Projectiles implements Affichable {

    private String nomImg = "animation2.png";
    private Controleur controleur;
    //-----
    
    //----
    private double gravity = 9.8;
    private double masse = 1;
    private double facRebond;
    private Vecteur vitesse = new Vecteur();
    private Vecteur position = new Vecteur();

//    private double x, y;
//    private double tempsProjectile = 0;
//    double v, angle, anglerad;
//    double vxi, vyi, yo, xo;
//    boolean droite = true;
//    boolean haut = true;
    public Projectiles(double posX, double posY, double vitesseIni, double angle, double facRebond, Controleur controleur) throws SlickException {
//	this.x = x;
//	this.y = y;
//	this.xo = x;
//	this.yo = y;
//	this.controleur = controleur;
//
//	this.v = v;//vitesse initiale 
//	this.angle = angle;//angle initial en degré

        this.facRebond = facRebond;
        position.setX(posX);
        position.setY(posY);
        vitesse.setX(vitesseIni * Math.cos(Math.toRadians(angle)));
        vitesse.setY(vitesseIni * Math.sin(Math.toRadians(angle)));

        controleur.setNouvelleItemAffichable(this);
    }

    public void detruir() {

        controleur.enleverProjectiles(this);
    }

    //getter and setter
    public String getNomImg() {
        return nomImg;
    }
//    }
    //    public double getAngle() {
    //	return angle;
    //    }
    //
    //    public void setAngle(double angle) {
    //	this.angle = angle;
    //    }
    //
    //    public double getAnglerad() {
    //	return anglerad;
    //    }
    //
    //    public void setAnglerad(double anglerad) {
    //	this.anglerad = anglerad;
    //    }
    //
    //    public void setX(double x) {
    //	this.x = x;
    //    }
    //
    //    public void setY(double y) {
    //	this.y = y;
    //    }
    //
    //    public double getX() {
    //	return x;
    //    }
    //
    //    public double getY() {
    //	return y;
    //    }
    //
    //    public double getVxi() {
    //	return vxi;
    //    }
    //
    //    public void setVxi(double vxi) {
    //	this.vxi = vxi;
    //    }
    //
    //    public double getVyi() {
    //	return vyi;
    //    }
    //
    //    public void setVyi(double vyi) {
    //	this.vyi = vyi;
    //    }
    //
    //    public double getYo() {
    //	return yo;
    //    }
    //
    //    public void setYo(double yo) {
    //	this.yo = yo;
    //    }
    //
    //    public double getXo() {
    //	return xo;
    //    }
    //
    //    public void setXo(double xo) {
    //	this.xo = xo;
    //    }
    //
    //    public double getTempsProjectile() {
    //	return tempsProjectile;
    //    }
    //
    //    public void setTempsProjectile(double tempsProjectile) {
    //	this.tempsProjectile = tempsProjectile;
    //    }
    //
    //    public boolean isDroite() {
    //	return droite;
    //    }
    //
    //    public void setDroite(boolean droite) {
    //	this.droite = droite;
    //    }
    //
    //    public boolean isHaut() {
    //	return haut;
    //    }
    //
    //    public void setHaut(boolean haut) {
    //	this.haut = haut;
    //    }
    //
    //    public double getV() {
    //	return v;
    //    }
    //
    //    public void setV(double v) {
    //	this.v = v;
    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public double getFacRebond() {
        return facRebond;
    }

    public void setFacRebond(double facRebond) {
        this.facRebond = facRebond;
    }

    public Vecteur getVitesse() {
        return vitesse;
    }

    public void setVitesse(Vecteur vitesse) {
        this.vitesse = vitesse;
    }

    public Vecteur getPosition() {
        return position;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }
}
