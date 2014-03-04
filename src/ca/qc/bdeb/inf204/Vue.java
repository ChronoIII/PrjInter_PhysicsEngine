/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf204;



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
    
    public Vue(String title) throws SlickException {
	super(title);
	this.addState(new MainMenu(menu));
	this.addState(new EngineScreen(engineScreen));
	
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.getState(menu).init(gc, this);
	this.getState(engineScreen).init(gc, this);
	this.enterState(menu);
    }
}
