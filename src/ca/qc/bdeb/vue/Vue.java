
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Vue extends StateBasedGame {

    private Controleur controleur;

    public Vue(String title, Controleur controleur) throws SlickException {
	super(title);

	this.controleur = controleur;

	this.addState(controleur.getEngineScreenMenu());
	this.addState(controleur.getMainMenu());
	this.addState(controleur.getPlayScreenMenu());
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.getState(controleur.getMenu()).init(gc, this);
	this.getState(controleur.getEngineScreen()).init(gc, this);
	this.getState(controleur.getPlayScreen()).init(gc, this);

	this.enterState(controleur.getMenu());
    }
}
