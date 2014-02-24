/*
 * Samuel
 * 2014-02-19
 */
package ca.qc.bdeb.inf204;

import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.EngineScreen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame {

    final public static int menu = 0;
    final public static int engineScreen = 1;
    
    static int width = 800;
    static int height = 600;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "game test";
    static int fpslimit = 60;

    public Main(String title) {
	super(title);
	this.addState(new MainMenu(menu));
	this.addState(new EngineScreen(engineScreen));
    }

    public static void main(String[] args) throws SlickException {
	AppGameContainer app = new AppGameContainer(new Main(title));
	app.setDisplayMode(width, height, fullscreen);
	app.setSmoothDeltas(true);
	app.setTargetFrameRate(fpslimit);
	app.setShowFPS(showFPS);
	app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
	this.getState(menu).init(gc, this);
	this.getState(engineScreen).init(gc, this);
	this.enterState(menu);
    }
}
