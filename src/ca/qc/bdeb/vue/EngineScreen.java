/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import java.util.ArrayList;




import org.lwjgl.input.Mouse;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.TextField;

/**
 *
 * @author Samuel
 */
public class EngineScreen extends BasicGameState {

    int state;
    Controleur controleur;
    private ArrayList<Image> listImagesProjectiles;
    private ArrayList<Image> listImagesStructures;
    private Image catapule;
    private Image bg;
    double t = 0;
    TextField textfield;
    double f = 0;
    double c = 1;
    double ca = 1;
    double angle = 0;
    private Music musiqueJeuPlay;
    private Animation projtest;
    private SpriteSheet projsheet;
    //------buttons----//
    private Image buttonInventaire;
    private Image buttonInventaire2;
    private Image buttonSettings;
    private Image buttonSave;
    private Image buttonLoad;
    private Image buttonPlay;
    private Image buttonPause;
    private Image buttonExit;
    private Image roue;
    private Image canon;
    private float lol = 90;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	Input a = gc.getInput();
	projsheet = new SpriteSheet("animation.png", 93, 75);
	projtest = new Animation(projsheet, 250);
	listImagesProjectiles = new ArrayList<Image>();
	listImagesStructures = new ArrayList<Image>();
	bg = new Image("background.jpg");

	buttonInventaire = new Image("inventory.png");
	buttonInventaire2 = new Image("inventory2.png");
	buttonSettings = new Image("settings2.png");
	buttonSave = new Image("save.png");
	buttonLoad = new Image("load.png");
	buttonPlay = new Image("play.png");
	buttonPause = new Image("pause.png");
	buttonExit = new Image("exit.png");

	roue = new Image("wheel.png");
	canon = new Image("canon.png");
	canon.rotate(Mouse.getX());
//        canon.rotate(280);
//        musiqueJeuPlay = new Music("gamemusic.wav");

//        musiqueJeuPlay.loop();






//	controleur.addProjectile(x, y);
//	c = new Ennemie(controleur);



	textfield = new TextField(gc, gc.getDefaultFont(), 5, 11, 200, 30);
	textfield.setBorderColor(Color.black);
	textfield.setBackgroundColor(Color.white);
	textfield.setTextColor(Color.black);
	textfield.setText("Score:");

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	// On met à jour à chaque seconde
//	controleur.avancerTemps();


	Input Key = gc.getInput();

	//key input////////////////////////////////////////////////////////////////////////////
	if (Key.isKeyDown(Input.KEY_SPACE)) {
	    if (f > 600 || f < 0) {
		c *= -1;
	    }
	    f += 3 * c;
	} else {
	    if (f != 0) {
		angle = Math.toDegrees((Math.atan((double) Mouse.getY() / Mouse.getX())));
		System.out.println("" + angle);
		controleur.addProjectile(0, 0, f/3, angle);
		f = 0;
	    }
	}

	if (Key.isKeyDown(Input.KEY_A)) {
	    if (angle > 90 || angle < 0) {
		ca *= -1;
	    }
	    angle += ca;
	}

	if (Key.isKeyPressed(Input.KEY_1)) {

            System.out.println("destroy");
	    if (!controleur.listProjectiles().isEmpty()) {

		controleur.listProjectiles().get(0).detruir();
	    }
	    if (controleur.listProjectiles().isEmpty()) {
	    }
	}
	if (Key.isKeyDown(Input.KEY_M)) {

	    sbg.enterState(0);
	}

	//fin key input///////////////////////////////////////////////////////////////////////

	//nouvelle image
	if (controleur.getNouvelleItemAffichable() != null) {
	    switch (controleur.getNouvelleItemAffichable().getNomImg()) {
		case "bird.png":
		    addImageProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
		    break;
		case "Sans titre.png":
		    addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
		    break;
	    }
	    controleur.setNouvelleItemAffichable(null);
	}

	//mouvement///////////////////////////////////////////////////////////////////////////////
	controleur.bougerProjectiles();
	
	controleur.rebondProjectiles();

//	for (int i = 0; i < listImagesProjectiles.size(); i++) {
//
//	    //condition sol et plafond
//	    if (controleur.listProjectiles().get(i).getY() < -1 || controleur.listProjectiles().get(i).getY() > 500) {
//		if (controleur.listProjectiles().get(i).getY() < -1 ) {
//		    controleur.listProjectiles().get(i).setYo(499);
//		} else {
//		    controleur.listProjectiles().get(i).setYo(0);
//		}
//		controleur.listProjectiles().get(i).setXo(controleur.listProjectiles().get(i).getX());
//
//		controleur.listProjectiles().get(i).setHaut(!controleur.listProjectiles().get(i).isHaut());
//
//		controleur.listProjectiles().get(i).setV(controleur.listProjectiles().get(i).getV() * 0.82);
//		
//		controleur.listProjectiles().get(i).setTempsProjectile(0);
//	    }
//
//
//	    //confition mur
//	    if (controleur.listProjectiles().get(i).getX() < -1 || controleur.listProjectiles().get(i).getX() > 1100) {
//		System.out.println(controleur.listProjectiles().get(i).getX());
//		if (controleur.listProjectiles().get(i).getX() > 1100) {
//		    controleur.listProjectiles().get(i).setXo(1099);
//		} else {
//		    controleur.listProjectiles().get(i).setXo(0);
//		}
//
//		controleur.listProjectiles().get(i).setYo(500 - controleur.listProjectiles().get(i).getY());
//
//		controleur.listProjectiles().get(i).setDroite(!controleur.listProjectiles().get(i).isDroite());
//
//		controleur.listProjectiles().get(i).setV(controleur.listProjectiles().get(i).getV() * 0.82);
//		
//		controleur.listProjectiles().get(i).setTempsProjectile(0);
//	    }
//
//	    if (controleur.listProjectiles().get(i).getY() > -50 && controleur.listProjectiles().get(i).getX() < 1200) {
//		controleur.bougerProjectile();
//	    }
//	}


	//fin mouvement//////////////////////////////////////////////////////////////////////////////////////////
	canon.setRotation((float) Math.toDegrees((Math.atan((double) Mouse.getX() / Mouse.getY()))) + 270);
    }

    public int getID() {
	return state;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	bg.draw();


	for (int i = 0; i < listImagesProjectiles.size(); i++) {
	    listImagesProjectiles.get(i).draw((int) (controleur.positionProjectileX(i)), (int) (controleur.positionProjectileY(i)));
	}
	for (int i = 0; i < listImagesStructures.size(); i++) {
	}
	g.setColor(Color.black);
	g.drawString("force: " + f, 300, 100);
	g.drawString("angle: " + angle, 300, 150);
	g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 300, 200);
	textfield.render(gc, g);
	projtest.draw(100, 100);
	buttonInventaire.draw(10, 600);
	buttonPlay.draw(100, 600);
	buttonExit.draw(1110, 5);
	buttonSettings.draw(1110, 600);
	buttonLoad.draw((1110 - 90), 600);
	buttonSave.draw((1110 - 180), 600);

	canon.draw(85 - 195, 500);

        roue.draw(35, 506);
    }

    public void addImageProjectiles(String nomImg) throws SlickException {
	Image img = new Image(nomImg);
	img.setName(nomImg);
	listImagesProjectiles.add(img);
    }

    public void addImageStructures(String nomImg) throws SlickException {
	Image img = new Image(nomImg);
	img.setName(nomImg);
	listImagesStructures.add(img);
    }

    public ArrayList<Image> getListImagesProjectiles() {
	return listImagesProjectiles;
    }

    public void setListImagesProjectiles(ArrayList<Image> listImagesProjectiles) {
	this.listImagesProjectiles = listImagesProjectiles;
    }

    public ArrayList<Image> getListImagesStructures() {
	return listImagesStructures;
    }

    public void setListImagesStructures(ArrayList<Image> listImagesStructures) {
	this.listImagesStructures = listImagesStructures;
    }
}
