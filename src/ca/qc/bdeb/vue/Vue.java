/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;



import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.vue.EngineScreen;
import ca.qc.bdeb.vue.MainMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
public class Vue extends StateBasedGame {

    final public static int menu = 0;
    final public static int engineScreen = 1;
    Controleur controleur;
    
    public Vue(String title, Controleur controleur) throws SlickException {
	super(title);
	
	this.controleur = controleur;
	
	this.addState(new MainMenu(menu, controleur));
	this.addState(new EngineScreen(engineScreen, controleur, controleur.getModule()));
	
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.getState(menu).init(gc, this);
	this.getState(engineScreen).init(gc, this);
	this.enterState(engineScreen);
    }
}
