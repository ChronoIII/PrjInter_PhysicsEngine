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
    double t = 0;

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
	    controleur.addProjectile(0,0);
	}
	if (a.isKeyPressed(Input.KEY_1)) {
	    controleur.listProjectiles().get(0).detruir();
	}

	// On met à jour à chaque seconde
	t = t + 0.1;

	for (int i = 0; i < listImagesProjectiles.size(); i++) {
	    if (controleur.getYf() < -1 || controleur.getYf() > 851) {
		if (controleur.getYf() < -1) {
		    controleur.setYo(0);
		} else {
		    controleur.setYo(850);
		}
		controleur.setXo(controleur.getXf());
		controleur.setHaut(!controleur.isHaut());


		controleur.setV(controleur.getV() * 0.82);
//         JOptionPane.showMessageDialog(null, pan.getV());
		t = 0;

	    }



	    if (controleur.getXf() < 0 || controleur.getXf() > 1150) {
		System.out.println(controleur.getXf());
		if (controleur.getXf() > 1150) {
		    controleur.setXo(1149);
		} else {
		    controleur.setXo(1);
		}

		controleur.setYo(controleur.getYf());
		controleur.setDroite(!controleur.isDroite());

		controleur.setV(controleur.getV() * 0.82);
//         JOptionPane.showMessageDialog(null, pan.getV());
		t = 0;


	    }




	    if (controleur.getYf() > -50 && controleur.getXf() < 1200) {
		
		controleur.mouvement2D(t);
		controleur.listProjectiles().get(0).setY((int) Math.round(controleur.getXf()));
		controleur.listProjectiles().get(0).setY((int) Math.round(790 - controleur.getYf()));

	    }
	}
	System.out.println(t);
//                else{t=0;
//                pan.setYo(0);
//                pan.setV(pan.getV());
//                pan.setAngle(pan.getAngle());
//                pan.setXo(0);
//                pan.setXf(0);
//                pan.setYf(0);
//                }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	bg.draw();
	for (int i = 0; i < listImagesProjectiles.size(); i++) {
	    listImagesProjectiles.get(i).draw(controleur.positionProjectileX(i), controleur.positionProjectileY(i));
	}
	for (int i = 0; i < listImagesStructures.size(); i++) {
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
