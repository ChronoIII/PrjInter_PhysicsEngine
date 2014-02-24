/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
//C'est la que tu dois faire de test d'affichage amé ^^
public class MainMenu extends BasicGameState {

    int state;
    Animation a;
    static int width = 640;
    static int height = 480;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "game test";
    static int fpslimit = 40;

    public MainMenu(int state) {
	this.state = state;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	g.fillRect(100, 100, 100, 100);
	g.fillRect(200, 200, 200, 200);
    }

    @Override
    public int getID() {
	return state;
    }
}
