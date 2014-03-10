/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.vue.EngineScreen;
import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.Projectiles;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Controleur {
    
    private int menu = 0;
    private int engineScreen = 1;

    private Module module;
    private MainMenu mainMenu;
    private EngineScreen engineScreenMenu;
    

    public Controleur() throws SlickException {
	mainMenu = new MainMenu(menu, this);
	engineScreenMenu = new EngineScreen(engineScreen, this);
	module = new Module(this);
    }
    
    public void addProjectile(String imgName) throws SlickException{
	engineScreenMenu.getListImageProjectiles().add(new Image(imgName));
    }
    
    public int positionProjectileX(int i){
	return module.getListProjectiles().get(i).getX();
    }
    
    public int positionProjectileY(int i){
	return module.getListProjectiles().get(i).getY();
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
}
