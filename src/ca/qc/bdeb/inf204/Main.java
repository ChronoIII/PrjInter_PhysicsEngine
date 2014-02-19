/*
 * Samuel
 * 2014-02-19
 */

package ca.qc.bdeb.inf204;

import org.newdawn.slick.*;

public class Main extends BasicGame{

     static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = true;
    static boolean showFPS = true;
    static String title = "game test";
    static int fpslimit = 40;
   
    public Main(String title) {
        super(title);
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
       
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
       
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
       
    }
   
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }
}
