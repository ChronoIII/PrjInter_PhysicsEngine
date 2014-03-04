/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
public class EngineScreen extends BasicGameState {

    int state;
    Module modele;
    Controleur controleur;
    int a;

    public EngineScreen(int state, Controleur controleur, Module modele) throws SlickException {
	this.state = state;
	this.controleur = controleur;
	this.modele = modele;
	
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//	a = new Image("little_baby.jpg");
	modele.ajouterProjectiles(100, 100);
	
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	
//	a.draw(x++, y++);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	for(Projectiles i : modele.getListeProjectiles()){
	    i.getImage().draw(i.getX(), i.getY());
	}
    }

    @Override
    public int getID() {
	return state;
    }
 
}
