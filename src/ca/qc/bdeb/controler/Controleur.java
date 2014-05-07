
package ca.qc.bdeb.controler;

import ca.qc.bdeb.module.Module;
import ca.qc.bdeb.vue.Affichable;
import ca.qc.bdeb.vue.EngineScreen;
import ca.qc.bdeb.vue.MainMenu;
import ca.qc.bdeb.vue.Projectiles;
import ca.qc.bdeb.vue.Structures;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

public class Controleur {

    //State
    private int menu = 0;
    private int engineScreen = 1;
    private Module module;
    //Screens
    private MainMenu mainMenu;
    private EngineScreen engineScreenMenu;
    private Affichable nouvelleItemAffichable = null;

    public Controleur() throws SlickException {
	module = new Module(this);

	engineScreenMenu = new EngineScreen(engineScreen, this);
	mainMenu = new MainMenu(menu, this);
    }

    //Projectiles
    public void addProjectile(int x, int y, double v, double angle, double facRebond) throws SlickException {
	module.getListProjectiles().add(new Projectiles((x + 40), (500-y ), v, angle, facRebond, this));
    }

    public void enleverProjectiles(Projectiles projectile) {
	module.getListProjectiles().remove(projectile);
	engineScreenMenu.getListAnimationProjectiles().remove(0);
    }

    public double positionProjectileX(int i) {
	return module.getListProjectiles().get(i).getPosition().getX();
    }

    public double positionProjectileY(int i) {
	return module.getListProjectiles().get(i).getPosition().getY();
    }

    public ArrayList<Projectiles> listProjectiles() {
	return module.getListProjectiles();
    }

    public void rebondProjectilesMur() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {

	    if ((module.getListProjectiles().get(i).getPosition().getX() + module.getListProjectiles().get(i).getBound().getX()) > module.getWidth()) {
		module.getListProjectiles().get(i).getPosition().setX(module.getWidth() - module.getListProjectiles().get(i).getBound().getX());
		module.rebond(module.getListProjectiles().get(i), 'x');
	    }

	    if ((module.getListProjectiles().get(i).getPosition().getY() + module.getListProjectiles().get(i).getBound().getY()) > (module.getHeight() - 85)) {
		module.getListProjectiles().get(i).getPosition().setY((module.getHeight() - 85) - module.getListProjectiles().get(i).getBound().getY());
		module.rebond(module.getListProjectiles().get(i), 'y');

	    }
	    if (module.getListProjectiles().get(i).getPosition().getX() < 0) {
		module.getListProjectiles().get(i).getPosition().setX(0);
		module.rebond(module.getListProjectiles().get(i), 'x');

	    }

	    if (module.getListProjectiles().get(i).getPosition().getY() < 75) {
		module.getListProjectiles().get(i).getPosition().setY(75);
		module.rebond(module.getListProjectiles().get(i), 'y');
	    }
	}
    }

    public void bougerProjectiles() {
	for (int i = 0; i < module.getListProjectiles().size(); i++) {
	    module.getListProjectiles().get(i).getPosition().setX(module.getListProjectiles().get(i).getPosition().getX() + module.getListProjectiles().get(i).getVitesse().getX());
	    module.getListProjectiles().get(i).getPosition().setY(module.getListProjectiles().get(i).getPosition().getY() - module.getListProjectiles().get(i).getVitesse().getY());
	    module.getListProjectiles().get(i).getVitesse().setY(module.getListProjectiles().get(i).getVitesse().getY() - (module.getListProjectiles().get(i).getGravity() / 100));
	}
    }

    public double orientationProjectil(Projectiles proj) {
	double angle;
	angle = Math.atan(proj.getVitesse().getX() / proj.getVitesse().getY());
	if (proj.getVitesse().getY() < 0) {
	    angle = angle + Math.PI;
	}
	return Math.toDegrees(angle);
    }

    //Structures
    public void addStructure(int x, int y) throws SlickException {
	module.getListStructures().add(new Structures(x, y, this));
    }

    public void enleverStructure(Structures structure) {
	module.getListStructures().remove(structure);
	engineScreenMenu.getListImagesStructures().remove(0);
    }

    public double positionStructureX(int i) {
	return module.getListStructures().get(i).getPosition().getX();
    }

    public double positionStructureY(int i) {
	return module.getListStructures().get(i).getPosition().getY();
    }

    public ArrayList<Structures> listStructures() {
	return module.getListStructures();
    }

    //Mettre un objet affichable dans la variable
    public void afficher(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }

    //Sauvegarde
    public void sauvegarderFichier(int nbreProjectile) {

	module.sauvegarde(nbreProjectile);
    }

    public void chargerFichier() {

	module.charger();
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

    public int getMenu() {
	return menu;
    }

    public int getEngineScreen() {
	return engineScreen;
    }

    public Affichable getNouvelleItemAffichable() {
	return nouvelleItemAffichable;
    }

    public void setNouvelleItemAffichable(Affichable nouvelleItemAffichable) {
	this.nouvelleItemAffichable = nouvelleItemAffichable;
    }
}
