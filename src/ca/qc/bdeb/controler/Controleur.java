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

    private Module module;
    private MainMenu mainMenu;
    private EngineScreen engineScreenMenu;
    private Affichable nouvelleItemAffichable = null;
    

    public Controleur() throws SlickException {
	module = new Module(this);
	engineScreenMenu = new EngineScreen(engineScreen, this);
	mainMenu = new MainMenu(menu, this);
    }
    
    public void addProjectile(int x, int y) throws SlickException{
	module.getListProjectiles().add(new Projectiles(x, y, this));
    }
    
    public int positionProjectileX(int i){
	return module.getListProjectiles().get(i).getX();
    }
    
    public int positionProjectileY(int i){
	return module.getListProjectiles().get(i).getY();
    }
    
    public ArrayList<Projectiles> listProjectiles() {
	return module.getListProjectiles();
    }
    
    public void afficher(Affichable nouvelleItemAffichable){
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }
    
//    setters and getters
    
    public Module getModule() {
	return module;
    }
    
    public EngineScreen getEngineScreenMenu(){
	return engineScreenMenu;
    }
    
    public MainMenu getMainMenu(){
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
