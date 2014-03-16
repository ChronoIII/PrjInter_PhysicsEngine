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
    private ArrayList<Image> listImages;
    private Image catapule;
    private Ennemie c;
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
	listImages = new ArrayList<Image>();
//	controleur.addProjectile(x, y);
//	c = new Ennemie(controleur);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	Input a = gc.getInput();

	if (controleur.getNouvelleItemAffichable() != null) {
	    addImage(controleur.getNouvelleItemAffichable().getNomImg());
	    controleur.setNouvelleItemAffichable(null);
	}
//	if (controleur.listProjectiles().size() != listImageProjectiles.size()) {
//	    addImage(controleur.listProjectiles().get(controleur.listProjectiles().size() - 1).getNomImg());
//	}
	if (a.isKeyPressed(Input.KEY_SPACE)) {
	    System.out.println("space");
	    controleur.addProjectile(x, y);
	    x = x + 10;
	    y = y + 10;
	}
	if (a.isKeyPressed(Input.KEY_1)) {
	    System.out.println("1");
	    c = new Ennemie(controleur);
	}
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	int indexProjectile = 0;
	int indexEnnemie = 0;
	for (int i = 0; i < listImages.size(); i++) {
	    if(listImages.get(i).getName() == "little_baby.jpg"){
		System.out.println(" entre dans projectile");
	    listImages.get(i).draw(controleur.positionProjectileX(indexProjectile), controleur.positionProjectileY(indexProjectile));
	    indexProjectile++;
	    }
	    if(listImages.get(i).getName() == "Sans titre.png"){
		System.out.println(" entre dans lautre");
	    listImages.get(i).draw(x, y);
	    indexEnnemie++;
	    }
	}
    }

    @Override
    public int getID() {
	return state;
    }

    public ArrayList<Image> getListImageProjectiles() {
	return listImages;
    }

    public void addImage(String nomImg) throws SlickException {
	Image img = new Image(nomImg);
	img.setName(nomImg);
	listImages.add(img);
    }
}
