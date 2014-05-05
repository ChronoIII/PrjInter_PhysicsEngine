/*
 * Samuel
 * 2014-02-19
 */
package ca.qc.bdeb.inf204;

import ca.qc.bdeb.vue.Vue;
import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.EngineScreen;
import javax.swing.JFrame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main {

    //Screen propriétées
    static int width = 1200;
    static int height = 675;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static int fpslimit = 60;
    static String title = "no name";
    static Controleur controleur;
    static JFrame frame;

    public static void main(String[] args) throws SlickException {

	//intitalisation du controleur
	controleur = new Controleur();

	//Créé la fenètre
	frame = new JFrame();
	CanvasGameContainer app2 = new CanvasGameContainer(new Vue(title, controleur));
	AppGameContainer app = new AppGameContainer(new Vue(title, controleur));
	
	frame.setUndecorated(true);
	frame.setVisible(true);
	frame.add(app2);
	frame.setSize(width, height);
	frame.setLocation(50, 50);
	
	
	app2.getContainer().setShowFPS(showFPS);
	app2.getContainer().setTargetFrameRate(fpslimit);
	app2.getContainer().setIcon("icon.png");
	
	app.setDisplayMode(width, height, fullscreen);
	app.setTargetFrameRate(fpslimit);
	app.setShowFPS(showFPS);
	app.setIcon("icon.png");
	app2.start();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void exit(){
	frame.dispose();
	System.exit(0);
    }
}
