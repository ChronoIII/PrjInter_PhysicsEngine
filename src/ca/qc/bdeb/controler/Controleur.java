package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.vue.Affichable;
import ca.qc.bdeb.vue.Cibles;
import ca.qc.bdeb.vue.EngineScreen;
import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.PlayScreen;
import ca.qc.bdeb.vue.Projectiles;
import ca.qc.bdeb.vue.Screen;
import ca.qc.bdeb.vue.Structures;
import ca.qc.bdeb.vue.Vecteur;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 * Le contrôleur qui permet de faire des 
 * 
 */

public class Controleur {

    //State
    private int menu = 0;
    private int engineScreen = 1;
    private int playScreen = 2;
    //module
    private Module module;
    //Screens
    private MainMenu mainMenu;
    private EngineScreen engineScreenMenu;
    private PlayScreen playScreenMenu;
    private Affichable nouvelleItemAffichable = null;

    public Controleur() throws SlickException {
	module = new Module(this);

	engineScreenMenu = new EngineScreen(engineScreen, this);
	mainMenu = new MainMenu(menu, this);
	playScreenMenu = new PlayScreen(playScreen, this);
    }

   
    /**
   
    ajouter des projectiles dans la liste
     * @param x
     * @param y
     * @param v
     * @param angle
     * @param facRebond
     * @throws SlickException 
     */
    public void addProjectile(int x, int y, double v, double angle, double facRebond) throws SlickException {
	module.getListProjectiles().add(new Projectiles((x + 40), (500 - y), v, angle, facRebond, this));
    }
/**
 * enlève les projectiles de l'écran et la liste
 * @param projectile
 * @param ecran 
 */
    //enlève  dans la liste
    public void enleverProjectiles(Projectiles projectile, Screen ecran) {
	module.getListProjectiles().remove(projectile);
	ecran.getListAnimationProjectiles().remove(0);
    }

    //retourne la position  en x
    public double positionProjectileX(int i) {
	return module.getListProjectiles().get(i).getPosition().getX();
    }

    //retourne la position en x
    public double positionProjectileY(int i) {
	return module.getListProjectiles().get(i).getPosition().getY();
    }

    public ArrayList<Projectiles> getListProjectiles() {
	return module.getListProjectiles();
    }
/**
 * Faire rebondir les projectiles sur les murs avec des conditions
 * @param proj 
 */
    //détecte les colliton avce le mur
    public void rebondProjectilesMur(Projectiles proj) {

	if ((proj.getPosition().getX() + proj.getBound().getX()) > module.getWidth()) {
	    proj.getPosition().setX(module.getWidth() - proj.getBound().getX());
	    module.rebond(proj, 'x');
	}

	if ((proj.getPosition().getY() + proj.getBound().getY()) > (module.getHeight() - 85)) {
	    proj.getPosition().setY((module.getHeight() - 85) - proj.getBound().getY());
	    module.rebond(proj, 'y');
	}
	if (proj.getPosition().getX() < 0) {
	    proj.getPosition().setX(0);
	    module.rebond(proj, 'x');
	}

	if (proj.getPosition().getY() < 75) {
	    proj.getPosition().setY(75);
	    module.rebond(proj, 'y');
	}
    }
/** 
 * Boucle qui permet de tester chaque projectiles par rapport au mur
 */
    public void rebondProjectilesMurLoop() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {

	    rebondProjectilesMur(module.getListProjectiles().get(i));
	}
    }
/**
 * Bouger chaque projectiles individuellement
 */
    public void bougerProjectiles() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {
	    module.getListProjectiles().get(i).getPosition().setX(module.getListProjectiles().get(i).getPosition().getX() + module.getListProjectiles().get(i).getVitesse().getX());
	    module.getListProjectiles().get(i).getPosition().setY(module.getListProjectiles().get(i).getPosition().getY() - module.getListProjectiles().get(i).getVitesse().getY());
	    module.getListProjectiles().get(i).getVitesse().setY(module.getListProjectiles().get(i).getVitesse().getY() - (module.getListProjectiles().get(i).getGravity() / 100));
	}
    }

    /** 
     * ajouter des structures dans la liste
     * @param x
     * @param y
     * @throws SlickException 
     */
    public void addStructure(int x, int y) throws SlickException {
	module.getListStructures().add(new Structures(x, y, this));
    }
/**
 * permet d'Enlever les structures
 * @param structure
 * @param ecran 
 */
    public void enleverStructure(Structures structure, Screen ecran) {
	module.getListStructures().remove(structure);
	ecran.getListImagesStructures().remove(0);
    }

    public double positionStructureX(int i) {
	return module.getListStructures().get(i).getPosition().getX();
    }

    public double positionStructureY(int i) {
	return module.getListStructures().get(i).getPosition().getY();
    } 
    public void setpositionStructureY(int i, Vecteur v) {
	module.getListStructures().get(i).setPosition(v);
    }

    public ArrayList<Structures> getListStructures() {
	return module.getListStructures();
    }
/**
 * Gérer les collisions et les rebonds des projectiles sur les structures.
 * @param proj
 * @param struc 
 */
    public void rebondProjectilesStructures(Projectiles proj, Structures struc) {

	if (proj.getPosition().getX() + proj.getBound().getX() > struc.getPosition().getX() && proj.getPosition().getX() < (struc.getPosition().getX() + struc.getBound().getX()) && proj.getPosition().getY() + proj.getBound().getY() > struc.getPosition().getY()) {
	    if (proj.getVitesse().getX() < 0) {
		if (Math.abs((struc.getPosition().getX() + struc.getBound().getX()) - proj.getPosition().getX()) > Math.abs(struc.getPosition().getY() - proj.getPosition().getY())) {
		    proj.getPosition().setY(struc.getPosition().getY());
		    module.rebond(proj, 'y');

		} else {
		    proj.getPosition().setX((struc.getPosition().getX() + struc.getBound().getX()));
		    module.rebond(proj, 'x');
		}

	    } 
                    else {
		if (Math.abs(struc.getPosition().getX() - proj.getPosition().getX()- proj.getBound().getX()) > Math.abs(struc.getPosition().getY() - (proj.getPosition().getY() + proj.getBound().getY() ) )) {
		    proj.getPosition().setY(struc.getPosition().getY()-proj.getBound().getY()-1);
		    module.rebond(proj, 'y');
		    
		} else {
		    proj.getPosition().setX(struc.getPosition().getX()-1-proj.getBound().getX());
		    module.rebond(proj, 'x');
		    
		}
	    }
	}
    }
/**
 * Permettent à plusieurs balles de collisionner 
 */
    public void rebondProjectilesStructuresLoop() {
	for (int j = 0; j < module.getListStructures().size(); j++) {
	    for (int i = 0; i < module.getListProjectiles().size(); i++) {

		rebondProjectilesStructures(module.getListProjectiles().get(i), module.getListStructures().get(j));
	    }
	}
    }

    //Cibles
    public void addCible(int x, int y) throws SlickException {
	module.getListCibles().add(new Cibles(x, y, this));
    }

    public void enleverCible(Cibles cible) {
	module.getListCibles().remove(cible);
	engineScreenMenu.getListAnimationCibles().remove(0);
    }

    public double positionCiblesX(int i) {
	return module.getListCibles().get(i).getPosition().getX();
    }

    public double positionCiblesY(int i) {
	return module.getListStructures().get(i).getPosition().getY();
    }

    public ArrayList<Cibles> getListCibles() {
	return module.getListCibles();
    }
    
    public void collisionCibleProjectiles(Projectiles proj, Cibles c,  Screen ecran){
        if (proj.getPosition().getX() + proj.getBound().getX() > c.getPosition().getX() && proj.getPosition().getX() < (c.getPosition().getX() + c.getBound().getX()) && proj.getPosition().getY() + proj.getBound().getY() > c.getPosition().getY() && proj.getPosition().getY() < c.getPosition().getY()+c.getBound().getY())
          {
            //détruire coeur, détruire cible, nouvelle cible
	      enleverCible(c);
	      enleverProjectiles(proj, ecran);
        }
    }
    
    public void collisionCibleProjectilesloop (Screen ecran) {
	for (int j = 0; j < module.getListCibles().size(); j++) {
	    for (int i = 0; i < module.getListProjectiles().size(); i++) {

		collisionCibleProjectiles(module.getListProjectiles().get(i), module.getListCibles().get(j), ecran);
	    }
	}
    }
    
    
    //Mettre un objet affichable dans la variable
    public void afficher(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }

    //getters and setters
    public Module getModule() {
	return module;
    }

    public EngineScreen getEngineScreenMenu() {
	return engineScreenMenu;
    }

    public MainMenu getMainMenu() {
	return mainMenu;
    }
    
   public PlayScreen getPlayScreenMenu(){
       return playScreenMenu;
   }

    public int getMenu() {
	return menu;
    }

    public int getEngineScreen() {
	return engineScreen;
    }
    
    public int getPlayScreen() {
	return playScreen;
    }
    public Affichable getNouvelleItemAffichable() {
	return nouvelleItemAffichable;
    }

    public void setNouvelleItemAffichable(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }
}
