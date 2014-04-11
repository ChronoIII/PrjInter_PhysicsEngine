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
    Module modele;
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

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
	this.modele = modele;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	Input a = gc.getInput();
	listImagesProjectiles = new ArrayList<Image>();
	listImagesStructures = new ArrayList<Image>();
	bg = new Image("blackscreen.jpg");




//	controleur.addProjectile(x, y);
//	c = new Ennemie(controleur);



	textfield = new TextField(gc, gc.getDefaultFont(), 500, 500, 200, 20);
	textfield.setBorderColor(Color.black);
	textfield.setBackgroundColor(Color.white);
	textfield.setTextColor(Color.black);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	// On met à jour à chaque seconde
	controleur.avancerTemps();
	
	
	Input Key = gc.getInput();

	//key input
	if (Key.isKeyDown(Input.KEY_SPACE)) {
	    if (f > 600 || f < 0) {
		c *= -1;
	    }
	    f += c;
	} else {
	    if (f != 0) {
		controleur.addProjectile(0, 0, f, angle);
		f = 0;
		angle = 0;
	    }
	}

	if (Key.isKeyDown(Input.KEY_A)) {
	    if (angle > 90 || angle < 0) {
		ca *= -1;
	    }
	    angle += ca;
	}

	if (Key.isKeyPressed(Input.KEY_1)) {


	    if (!controleur.listProjectiles().isEmpty()) {

		controleur.listProjectiles().get(0).detruir();
	    }
	    if (controleur.listProjectiles().isEmpty()) {
	    }
	}
	if (Key.isKeyDown(Input.KEY_M)) {

	    sbg.enterState(0);
	}
	
	//fin key input
	
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

	//mouvement
	for (int i = 0; i < listImagesProjectiles.size(); i++) {

	    //condition sol et plafond
	    if (controleur.listProjectiles().get(i).getY() < -1 || controleur.listProjectiles().get(i).getY() > 500) {
		if (controleur.listProjectiles().get(i).getY() < -1) {
		    controleur.listProjectiles().get(i).setYo(499);
		} else {
		    controleur.listProjectiles().get(i).setYo(0);
		}
		controleur.listProjectiles().get(i).setXo(controleur.listProjectiles().get(i).getX());

		controleur.listProjectiles().get(i).setHaut(!controleur.listProjectiles().get(i).isHaut());

		controleur.listProjectiles().get(i).setV(controleur.listProjectiles().get(i).getV() * 0.82);
		
		controleur.listProjectiles().get(i).setTempsProjectile(0);
	    }


	    //confition mur
	    if (controleur.listProjectiles().get(i).getX() < -1 || controleur.listProjectiles().get(i).getX() > 1100) {
		System.out.println(controleur.listProjectiles().get(i).getX());
		if (controleur.listProjectiles().get(i).getX() > 1100) {
		    controleur.listProjectiles().get(i).setXo(1099);
		} else {
		    controleur.listProjectiles().get(i).setXo(0);
		}

		controleur.listProjectiles().get(i).setYo(500 - controleur.listProjectiles().get(i).getY());

		controleur.listProjectiles().get(i).setDroite(!controleur.listProjectiles().get(i).isDroite());

		controleur.listProjectiles().get(i).setV(controleur.listProjectiles().get(i).getV() * 0.82);
		
		controleur.listProjectiles().get(i).setTempsProjectile(0);
	    }

	    if (controleur.listProjectiles().get(i).getY() > -50 && controleur.listProjectiles().get(i).getX() < 1200) {
		controleur.bougerProjectile();
	    }
	}
	//fin mouvement
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

	bg.draw();

	for (int i = 0; i < listImagesProjectiles.size(); i++) {
	    listImagesProjectiles.get(i).draw((int) (controleur.positionProjectileX(i)), (int) (controleur.positionProjectileY(i)));
	}
	for (int i = 0; i < listImagesStructures.size(); i++) {
	    listImagesStructures.get(i).draw((int) (controleur.positionStructureX(i)), (int) (controleur.positionStructureY(i)));
	}




	g.drawOval(90, 10, 50, 50);
	g.drawOval(150, 13, 50, 50);
	g.drawString("force: " + f, 300, 100);
	g.drawString("angle: " + angle, 300, 150);
	g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 300, 200);
	textfield.render(gc, g);
    }

    @Override
    public int getID() {
	return state;
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
