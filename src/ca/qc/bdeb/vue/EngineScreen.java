/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

<<<<<<< HEAD
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
=======

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
>>>>>>> 9607e834798b2118887cf595f8986f2e2176e8d5

/**
 *
 * @author Samuel
 */
public class EngineScreen extends BasicGameState {

    int state;
    Image a;
    int x = 0;
    int y = 0;

    public EngineScreen(int state) throws SlickException {
	this.state = state;
	
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
//	a = new Image("little_baby.jpg");
	
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
//	a.draw(x++, y++);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	
    }

    @Override
    public int getID() {
	return state;
    }
 
}
