/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;



import ca.qc.bdeb.controler.Controleur;
import java.awt.Menu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
public class Vue extends StateBasedGame {

    private Controleur controleur;
    
    public Vue(String title, Controleur controleur) throws SlickException {
	super(title);
	
	this.controleur = controleur;
	
	this.addState(controleur.getMainMenu());
	this.addState(controleur.getEngineScreenMenu());
	
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	
	this.getState(controleur.getEngineScreen()).init(gc, this);
//	this.enterState(controleur.getEngineScreen());
        this.getState(controleur.getMenu()).init(gc, this);
        this.enterState(controleur.getMenu());
       
    }
}
