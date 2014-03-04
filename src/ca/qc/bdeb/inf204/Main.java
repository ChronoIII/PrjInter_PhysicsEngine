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
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main {

    //Screen propriétées
    static int width = 800;
    static int height = 600;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static int fpslimit = 60;
    static String title = "game test";

    public static void main(String[] args) throws SlickException {
	AppGameContainer app = new AppGameContainer(new Vue(title));
	app.setDisplayMode(width, height, fullscreen);
	app.setSmoothDeltas(true);
	app.setTargetFrameRate(fpslimit);
	app.setShowFPS(showFPS);
	app.start();
	
	//intitalisation du controleur
	new Controleur();
    }
}
