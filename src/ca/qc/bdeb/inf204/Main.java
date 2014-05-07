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
        CanvasGameContainer app = new CanvasGameContainer(new Vue(title, controleur));

        //SetPropriétés
        //frame
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.add(app);
        frame.setSize(width, height);
        frame.setLocation(50, 50);
        app.getContainer().setIcon("icon.png");
        //State base game
        app.getContainer().setShowFPS(showFPS);
        app.getContainer().setTargetFrameRate(fpslimit);


        app.start();
    }

    //exit le jeu et la fenetre
    public static void exit() {
        frame.dispose();
        System.exit(0);
    }
}
