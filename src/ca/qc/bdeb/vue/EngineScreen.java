/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import java.util.ArrayList;
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
    private ArrayList<Image> listImageProjectiles;
    private Image catapule;
//    Image a;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
	this.modele = modele;
	listImageProjectiles = new ArrayList<Image>();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//	a = new Image("Sans Titre.png");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	for (int i = 0; i < listImageProjectiles.size(); i++) {
	    listImageProjectiles.get(i).draw(controleur.positionProjectileX(i), controleur.positionProjectileY(i));
	}
//	a.draw(100, 100);
    }

    @Override
    public int getID() {
	return state;
    }
    
    public ArrayList<Image> getListImageProjectiles(){
	return listImageProjectiles;
    }
 
}
