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
    private ArrayList<Image> listImageProjectiles;
    private Image catapule;
    int x = 10;
    int y = 10;
    Image a;
    Image b;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
	this.modele = modele;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	listImageProjectiles = new ArrayList<Image>();
	controleur.addProjectile(x, y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	Input a = gc.getInput();
	
	if(controleur.getNouvelleItemAffichable() != null){
	    System.out.println("11");
	     addImage(controleur.getNouvelleItemAffichable().getNomImg());
	     controleur.setNouvelleItemAffichable(null);
	}
//	if (controleur.listProjectiles().size() != listImageProjectiles.size()) {
//	    addImage(controleur.listProjectiles().get(controleur.listProjectiles().size() - 1).getNomImg());
//	}
	if(a.isKeyPressed(Input.KEY_SPACE)){
	    controleur.addProjectile(x, y);
	    x = x + 10;
	    y = y + 10;
	}
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	for (int i = 0; i < listImageProjectiles.size(); i++) {
	    listImageProjectiles.get(i).draw(controleur.positionProjectileX(i), controleur.positionProjectileY(i));
	}
    }

    @Override
    public int getID() {
	return state;
    }

    public ArrayList<Image> getListImageProjectiles() {
	return listImageProjectiles;
    }

    public void addImage(String nomImg) throws SlickException {
	listImageProjectiles.add(new Image(nomImg));
    }
}
