/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
//C'est la que tu dois faire de test d'affichage Amé ^^
public class MainMenu extends BasicGameState {

    int state;
    private Controleur con;

    public MainMenu(int state) throws SlickException {
	this.state = state;
	con = new Controleur();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image img = new Image ("little_baby.jpg");
	g.fillRect(100, 100, 100, 100);
	g.fillRect(200, 200, 200, 200);
        
         img.draw(100,100);
    }

    @Override
    public int getID() {
	return state;
    }
}
