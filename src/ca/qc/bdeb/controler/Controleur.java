/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.vue.Affichable;
import ca.qc.bdeb.vue.EngineScreen;
import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.Projectiles;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

public class Controleur {
    
    private int menu = 0;
    private int engineScreen = 1;
    //engineScreen = 1???
    private Module module;
    private MainMenu mainMenu;
    private EngineScreen engineScreenMenu;
    private Affichable nouvelleItemAffichable = null;
    
    public Controleur() throws SlickException {
	module = new Module(this);
	engineScreenMenu = new EngineScreen(engineScreen, this);
	mainMenu = new MainMenu(menu, this);
	
	v = 75;//vitesse initiale 
	angle = 60;//angle initial en degré
    }
    
    public void addProjectile(int x, int y) throws SlickException {
	module.getListProjectiles().add(new Projectiles(x, y, this));
    }
    
    public double positionProjectileX(int i) {
	return module.getListProjectiles().get(i).getX();
    }
    
    public double positionProjectileY(int i) {
	return module.getListProjectiles().get(i).getY();
    }
    
    public ArrayList<Projectiles> listProjectiles() {
	return module.getListProjectiles();
    }
    
    public void afficher(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }
    
    public void enleverProjectile(Projectiles projectile) {
	module.getListProjectiles().remove(projectile);
	engineScreenMenu.getListImagesProjectiles().remove(0);
    }
    
    public void bougerProjectile() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {
	    listProjectiles().get(i).mouvement2D();
	    listProjectiles().get(i).setX((int) Math.round(getXf()));
	    listProjectiles().get(i).setY((int) Math.round(540 - getYf()));
	}
    }
    
    public void avancerTemps() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {
	    listProjectiles().get(i).setTempsProjectile(listProjectiles().get(i).getTempsProjectile() + 0.1);
	}
    }
    double v, angle, anglerad, vxi, vyi, yo, xo, y1, y2, yf, xf;
    boolean droite = true;
    boolean haut = true;
    
    public void mouvement2D(double t) {
	
	anglerad = angle * Math.PI / 180;//conversion en rad

	if (haut) {
	    vyi = v * Math.sin(anglerad);//vitesse en y
	} else {
	    vyi = v * Math.sin(anglerad - Math.PI);
	}//vitesse en y

	
	
	if (droite) {
	    vxi = v * Math.cos(anglerad);//vitesse en x
	} else {
	    vxi = v * Math.cos(anglerad - Math.PI);//vitesse en x
	}
	
	
	
	
	
	yf = yo + vyi * t + (-9.8 * Math.pow(t, 2) / 2); //position de y pour chaque valeur de t
	xf = xo + vxi * t;//position en x pour chaque valeur de t
    }

//    setters and getters
    public Module getModule() {
	return module;
    }
    
    public EngineScreen getEngineScreenMenu() {
	return engineScreenMenu;
    }
    
    public MainMenu getMainMenu() {
	return mainMenu;
    }
    
    public int getMenu() {
	return menu;
    }
    
    public int getEngineScreen() {
	return engineScreen;
    }
    
    public Affichable getNouvelleItemAffichable() {
	return nouvelleItemAffichable;
    }
    
    public void setNouvelleItemAffichable(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }
    
    public boolean isHaut() {
	return haut;
    }
    
    public void setHaut(boolean haut) {
	this.haut = haut;
    }
    
    public boolean isDroite() {
	return droite;
    }
    
    public void setDroite(boolean droite) {
	this.droite = droite;
    }
    
    public double getYf() {
	return yf;
    }
    
    public void setYf(double yf) {
	this.yf = yf;
    }
    
    public double getV() {
	return v;
    }
    
    public void setV(double v) {
	this.v = v;
    }
    
    public double getAngle() {
	return angle;
    }
    
    public void setAngle(double angle) {
	this.angle = angle;
    }
    
    public double getVxi() {
	return vxi;
    }
    
    public void setVxi(double vxi) {
	this.vxi = vxi;
    }
    
    public double getVyi() {
	return vyi;
    }
    
    public void setVyi(double vyi) {
	this.vyi = vyi;
    }
    
    public double getYo() {
	return yo;
    }
    
    public void setYo(double yo) {
	this.yo = yo;
    }
    
    public double getXo() {
	return xo;
    }
    
    public void setXo(double xo) {
	this.xo = xo;
    }
    
    public double getY1() {
	return y1;
    }
    
    public void setY1(double y1) {
	this.y1 = y1;
    }
    
    public double getY2() {
	return y2;
    }
    
    public void setY2(double y2) {
	this.y2 = y2;
    }
    
    public double getXf() {
	return xf;
    }
    
    public void setXf(double xf) {
	this.xf = xf;
    }
}
