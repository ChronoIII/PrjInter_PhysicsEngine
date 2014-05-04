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
import ca.qc.bdeb.vue.Structures;
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

//	v = 75;//vitesse initiale 
//	angle = 60;//angle initial en degré
    }

    //Projectiles
    public void addProjectile(int x, int y, double v, double angle, double facRebond) throws SlickException {
	module.getListProjectiles().add(new Projectiles((x + 120), (y + 475), v, angle, facRebond, this));
    }

    public double positionProjectileX(int i) {
	return module.getListProjectiles().get(i).getPosition().getX();
    }

    public double positionProjectileY(int i) {
	return module.getListProjectiles().get(i).getPosition().getY();
    }

    public ArrayList<Projectiles> listProjectiles() {
	return module.getListProjectiles();
    }

    public void enleverProjectiles(Projectiles projectile) {
	module.getListProjectiles().remove(projectile);
	engineScreenMenu.getListAnimationProjectiles().remove(0);
    }
    
    public void rebondProjectiles(){
	for (int i = 0; i < module.getListProjectiles().size(); i++) {

	    if (module.getListProjectiles().get(i).getPosition().getX() > (1200 - 67)) {
		module.getListProjectiles().get(i).getPosition().setX(1200 - 67);
		module.rebond(module.getListProjectiles().get(i), 'b');

	    }
	    if (module.getListProjectiles().get(i).getPosition().getX() < 0) {
		module.getListProjectiles().get(i).getPosition().setX(0);
		module.rebond(module.getListProjectiles().get(i), 'b');

	    }
	    if (module.getListProjectiles().get(i).getPosition().getY() > (675 - 142)) {
		module.getListProjectiles().get(i).getPosition().setY((675 - 142));
		module.rebond(module.getListProjectiles().get(i), 'a');

	    }
	    if (module.getListProjectiles().get(i).getPosition().getY() < 75) {
		module.getListProjectiles().get(i).getPosition().setY(75);
		module.rebond(module.getListProjectiles().get(i), 'a');

	    }
	}
    }
    
    public void bougerProjectiles() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {
//	    module.mouvement2D(listProjectiles().get(i));
	    module.getListProjectiles().get(i).getPosition().setX(module.getListProjectiles().get(i).getPosition().getX() + module.getListProjectiles().get(i).getVitesse().getX());
	    module.getListProjectiles().get(i).getPosition().setY(module.getListProjectiles().get(i).getPosition().getY() - module.getListProjectiles().get(i).getVitesse().getY());
	    module.getListProjectiles().get(i).getVitesse().setY(module.getListProjectiles().get(i).getVitesse().getY() - (module.getListProjectiles().get(i).getGravity() / 100));
	}
    }
    //fin

    //Structures
    public void addStructure(int x, int y) throws SlickException {
	module.getListStructures().add(new Structures(x, y, this));
    }

    public double positionStructureX(int i) {
	return module.getListStructures().get(i).getPosition().getX();
    }

    public double positionStructureY(int i) {
	return module.getListStructures().get(i).getPosition().getY();
    }

    public ArrayList<Structures> listStructures() {
	return module.getListStructures();
    }

    public void enleverStructure(Structures structure) {
	module.getListStructures().remove(structure);
	engineScreenMenu.getListImagesStructures().remove(0);
    }
    //fin

    public void afficher(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }

//    public void avancerTemps() {
//	for (int i = 0; i < module.getListProjectiles().size(); i++) {
//	    listProjectiles().get(i).setTempsProjectile(listProjectiles().get(i).getTempsProjectile() + 0.1);
//	}
//    }

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
}
