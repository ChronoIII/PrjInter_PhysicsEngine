/*
 * Samuel
 * 2014-02-19
 */

package ca.qc.bdeb.inf204;

<<<<<<< HEAD
import ca.qc.bdeb.views.EngineScreen;
import ca.qc.bdeb.views.MainMenu;
=======
import ca.qc.bdeb.controler.Controleur;
>>>>>>> 3429911b92220f898b05e72380d74447d8c76e73
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

<<<<<<< HEAD
public class Main extends BasicGame {
//    Controleur cont = new Controleur();
 
=======
<<<<<<< HEAD
public class Main extends StateBasedGame{
    
    Animation a;
    
    final public static int menu = 0;
    final public static int engineScreen = 1;
=======
public class Main extends BasicGame{
    Controleur cont = new Controleur();
>>>>>>> 3429911b92220f898b05e72380d74447d8c76e73
>>>>>>> 46fec75032115a2ef09496a497297df834664e6a

    static int width = 640;
    static int height = 480;
   
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "game test";
    static int fpslimit = 40;
   
    public Main(String title) {
        super(title);
<<<<<<< HEAD
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException {
       
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
       
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
//        Image img = new Image ("little_baby.jpg");
//        img.draw(100,100);
       g.fillRect(100, 100, 100, 100);
       g.fillRect(200, 200, 200, 200);
=======
	this.addState(new MainMenu(menu));
	this.addState(new EngineScreen(engineScreen));
>>>>>>> 46fec75032115a2ef09496a497297df834664e6a
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
