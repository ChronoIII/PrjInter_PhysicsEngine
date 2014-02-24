/*
 * Samuel
 * 2014-02-19
 */

package ca.qc.bdeb.inf204;

import ca.qc.bdeb.views.EngineScreen;
import ca.qc.bdeb.views.MainMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
    
    Animation a;
    
    final public static int menu = 0;
    final public static int engineScreen = 1;

    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "game test";
    static int fpslimit = 40;
   
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
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
