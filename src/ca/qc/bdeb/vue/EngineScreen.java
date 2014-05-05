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

    //Identifiant
    int state;
    Controleur controleur;
    //Listes
    private ArrayList<Image> listImagesStructures;
    private ArrayList<Animation> listAnimationProjectiles;
    //Données temp commune
    double force = 0;
    double angle = 0;
    //**Image
    private Image bg;
    //projectile
    private SpriteSheet projsheet;
    private Animation projtest;
    //canon
    private Image roue;
    private Image canon;
    //Boutons
    private Image buttonInventaire;
    private Image buttonInventaire2;
    private Image buttonSettings;
    private Image buttonSave;
    private Image buttonLoad;
    private Image buttonPlay;
    private Image buttonPause;
    private Image buttonExit;
    private Image inventaireExit;
    //**
    //État dans le jeux
    private boolean modePausePlay;
    private boolean inventaire;
    private boolean focusMenu = false;
    //couleur de l'inventaire
    private Color colorAlpha = new Color(0.84f, 0.84f, 0.84f, 0.85f);
    //musique
    private Music musiqueJeuPlay;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	//Initialisarions des états
	modePausePlay = true;
	inventaire = false;

	//Initialisarions des animations projectiles
	projsheet = new SpriteSheet("spiritesheet.png", 80, 59);
	projtest = new Animation(projsheet, 250);

	//Initialisarions des listes
	listAnimationProjectiles = new ArrayList<Animation>();
	listImagesStructures = new ArrayList<Image>();

	//Initialisarion background
	bg = new Image("background.jpg");

	//Initialisarion des images Boutons
	buttonInventaire = new Image("inventory.png");
	buttonInventaire2 = new Image("inventory2.png");
	buttonSettings = new Image("settings2.png");
	buttonSave = new Image("save.png");
	buttonLoad = new Image("load.png");
	buttonPlay = new Image("play.png");
	buttonPause = new Image("pause.png");
	buttonExit = new Image("back.png");
	inventaireExit = new Image("exitinventaire.png");

	//Initialisarion des images canon
	roue = new Image("wheel.png");
	canon = new Image("canon.png");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	//Input pour les commende au clavier
	Input Key = gc.getInput();

	//Position de la souris
	int posX = Mouse.getX();
	int posY = Mouse.getY();

	//Pour vérifier si le curseur est sur la bar noir, changant l'état du jeu
	if ((Mouse.getX() < 1200 && Mouse.getY() > 600 && Mouse.getY() < 675) || (Mouse.getX() < 1200 && Mouse.getY() > 0 && Mouse.getY() < 88)) {
	    focusMenu = true;
	} else {
	    focusMenu = false;
	}


	//inventaire(Etat de jeu), lorsque l'on n'est pas dans l'inventaire 
	if (!inventaire) {

	    //**Boutons
	    //inventaire 2, lors que l'inventaire est déployé
	    if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    buttonInventaire = new Image("inventory2.png");
		    inventaire = true;

		} else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
		    buttonInventaire = new Image("inventory.png");
		}
	    }

	    //exit
	    if ((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    System.out.println("got clicked buddy! lets go back!");

		    for (int i = 0; i < controleur.listProjectiles().size() + i; i++) {
			controleur.enleverProjectiles(controleur.listProjectiles().get(0));
		    }

		    sbg.enterState(0);
		} else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
		}
	    }

	    //play
	    if ((posX > 100 && posX < 175) && (posY > 10 && posY < 75)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    if (modePausePlay) {
			buttonPlay = new Image("pause.png");
			modePausePlay = false;

		    } else {
			buttonPlay = new Image("play.png");
			modePausePlay = true;
		    }


		}
	    }

	    //save
	    if ((posX > 1020 && posX < 1100) && (posY > 10 && posY < 75)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    System.out.println("save button");

		    controleur.sauvegarderFichier(controleur.listProjectiles().size());

		}
	    }

	    //load
	    if ((posX > 1109 && posX < 1190) && (posY > 10 && posY < 75)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    System.out.println("load button");
		    controleur.chargerFichier();
		}
	    }

	    //**key input
	    //si le curseur n'est pas sur les bars noirs
	    if (!focusMenu) {
		//touche pour lancer
		if (Key.isKeyDown(Input.KEY_SPACE)) {
		    force += 3;
		} else {
		    //lorsque l'on relache, le projectiles fait feu
		    if (force != 0) {
			angle = Math.toDegrees((Math.atan((double) posY / posX)));
			System.out.println("" + angle);
			controleur.addProjectile((int) (200 * Math.acos(angle)) + 50, (int) (200 * Math.asin(angle)) - 50, force / 3, angle, 0.8);
			force = 0;


		    }
		}
		//**

		//nouvelle image
		if (controleur.getNouvelleItemAffichable() != null) {
		    switch (controleur.getNouvelleItemAffichable().getNomImg()) {
			case "spiritesheet.png":
			    addAnimationProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
			    break;
			case "Sans titre.png":
			    addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
			    break;
		    }
		    controleur.setNouvelleItemAffichable(null);
		}

		//Mouvement
		controleur.bougerProjectiles();
		controleur.rebondProjectilesMur();

		//Canon rotation
		if (!inventaire) {
		    if (!focusMenu) {
			canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);

		    }
		}
	    }
	}
    }

    public int getID() {
	return state;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	bg.draw();
	int posX = Mouse.getX();
	int posY = Mouse.getY();

	for (int i = 0; i < listAnimationProjectiles.size(); i++) {
	    listAnimationProjectiles.get(i).draw((int) (controleur.positionProjectileX(i)), (int) (controleur.positionProjectileY(i)));
//	    listAnimationProjectiles.get(i).getCurrentFrame().setRotation((float) (controleur.orientationProjectil(controleur.listProjectiles().get(i))));
	}
	for (int i = 0; i < listImagesStructures.size(); i++) {
	    listImagesStructures.get(i).draw((int) (controleur.positionStructureX(i)), (int) (controleur.positionStructureY(i)));
	}


	g.setColor(Color.black);
	g.drawString("force: " + force, 300, 100);
	g.drawString("angle: " + Math.toDegrees((Math.atan((double) posY / posX))), 300, 150);
	g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 300, 200);
//        projtest.draw(100, 100); //bat test
	buttonInventaire.draw(10, 600);
	buttonPlay.draw(100, 600);
	buttonExit.draw(1110, 5);

	buttonLoad.draw((1110), 600);
	buttonSave.draw((1110 - 90), 600);

	canon.draw(85 - 195, 500 + 7);

	roue.draw(35, 506 + 7);
	if (inventaire) {

	    g.setColor(colorAlpha);
	    g.fillRoundRect(0, 80, 475, 505, 30);


	    inventaireExit.draw(435, 55);
	    if ((posX > 438 && posX < 483) && (posY > 573 && posY < 619)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    buttonInventaire = new Image("inventory.png");
		    System.out.println("close inventory");

		    inventaire = false;

		}
	    }

	    if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    buttonInventaire = new Image("inventory.png");
		    System.out.println("Inventaire clicker pour fermer");
		    inventaire = false;


		}
	    }
	}

    }

    public void addAnimationProjectiles(String nomImg) throws SlickException {

	SpriteSheet projsheet = new SpriteSheet(nomImg, 80, 59);
	Animation projtest = new Animation(projsheet, 60);
	listAnimationProjectiles.add(projtest);
    }

    public void addImageStructures(String nomImg) throws SlickException {
	Image img = new Image(nomImg);
	img.setName(nomImg);
	listImagesStructures.add(img);
    }

    public ArrayList<Animation> getListAnimationProjectiles() {
	return listAnimationProjectiles;
    }

    public void setListAnimationProjectiles(ArrayList<Animation> listAnimationProjectiles) {
	this.listAnimationProjectiles = listAnimationProjectiles;
    }

    public ArrayList<Image> getListImagesStructures() {
	return listImagesStructures;
    }

    public void setListImagesStructures(ArrayList<Image> listImagesStructures) {
	this.listImagesStructures = listImagesStructures;
    }
}
