/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.module.Module;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

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
    int x = 10;
    int y = 10;
    Image r;
    Image b;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
	this.modele = modele;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	listImagesProjectiles = new ArrayList<Image>();
	listImagesStructures = new ArrayList<Image>();
	bg = new Image("bg.jpg");
//	controleur.addProjectile(x, y);
//	c = new Ennemie(controleur);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	Input a = gc.getInput();

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
//	if (controleur.listProjectiles().size() != listImageProjectiles.size()) {
//	    addImage(controleur.listProjectiles().get(controleur.listProjectiles().size() - 1).getNomImg());
//	}
	if (a.isKeyPressed(Input.KEY_SPACE)) {
	    controleur.addProjectile(x, y);
	    x = x + 10;
	    y = y + 10;
	}
	if (a.isKeyPressed(Input.KEY_1)) {
	    controleur.listProjectiles().get(0).detruir();
	}

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	bg.draw();
	for (int i = 0; i < listImagesProjectiles.size(); i++) {
	    listImagesProjectiles.get(i).draw(controleur.positionProjectileX(i), controleur.positionProjectileY(i));
	}
	for (int i = 0; i < listImagesStructures.size(); i++) {
	    listImagesStructures.get(i).draw(x, y);
	}

	
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
