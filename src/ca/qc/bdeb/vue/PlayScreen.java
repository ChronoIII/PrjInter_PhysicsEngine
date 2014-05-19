/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Samuel
 */
public class PlayScreen extends BasicGameState implements Screen {

    //Identifiant
    int state;
    Controleur controleur;
    //Listes
    private ArrayList<Image> listImagesStructures;
    private ArrayList<Animation> listAnimationProjectiles;
    private ArrayList<Animation> listAnimationCibles;
    //Données temp commune
    double force = 0;
    double angle = 0;
    int coteDeLaForce = 1;
    //**Image
    private Image bg;
    //projectile
    private Animation projAnimation;
    //coeur cible
    private SpriteSheet coeurSheet;
    private Animation cibleAnimation;
    //structures
    private Image structure;
    //canon
    private Image roue;
    private Image canon;
    //Boutons
    private Image buttonExit;
    //**
    //État dans le jeux
    private boolean focusMenu;
    private boolean finDeJeu;
    private boolean debutDejeu;
    //couleur de l'inventaire
    private Color colorAlpha = new Color(0.84f, 0.84f, 0.84f, 0.85f);
    //musique
    private Music musiqueJeuPlay;

    public PlayScreen(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	//Initialisation musique
	musiqueJeuPlay = new Music("music.wav");
	musiqueJeuPlay.setVolume(0.10f);

	//structure
	structure = new Image("structure.png");
	//Initialisarions des listes
	listAnimationProjectiles = new ArrayList<Animation>();
	listImagesStructures = new ArrayList<Image>();
	listAnimationCibles = new ArrayList<Animation>();

	//Initialisarion background
	bg = new Image("background.jpg");

	//Initialisarion des images Boutons
	buttonExit = new Image("back.png");

	//Initialisarion des images canon
	roue = new Image("wheel.png");
	canon = new Image("canon.png");

	//Initialisation animation cible
	coeurSheet = new SpriteSheet("heartsprite.png", 25, 40);
	cibleAnimation = new Animation(coeurSheet, 140);

	//Initialisation des états
	focusMenu = false;
	finDeJeu = false;//inutilisre
	debutDejeu = false;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	//Input pour les commende au clavier
	Input Key = gc.getInput();

	//Position de la souris
	int posX = Mouse.getX();
	int posY = Mouse.getY();

	//Loop musique
	if (!musiqueJeuPlay.playing()) {

	    musiqueJeuPlay.loop();
	}

	//Pour vérifier si le curseur est sur la bar noir, changant l'état du jeu
	if ((Mouse.getX() < 1200 && Mouse.getY() > 600 && Mouse.getY() < 675) || (Mouse.getX() < 1200 && Mouse.getY() > 0 && Mouse.getY() < 88)) {
	    focusMenu = true;
	} else {
	    focusMenu = false;
	}

	//commence le jeu et fait apparaètre tous les paramètres
	if (Key.isKeyDown(Input.KEY_ENTER)) {
	    debutDejeu = true;
	   controleur.addStructure(500, 450);
	   controleur.addStructure(700, 450);
	}

	//**Boutons
	//exit
	if (((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) || finDeJeu) {
	    if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		System.out.println("got clicked buddy! lets go back!");

		for (int i = 0; i < controleur.getListProjectiles().size() + i; i++) {
		    controleur.enleverProjectiles(controleur.getListProjectiles().get(0), this);

		}
//		for (int i = 0; i < controleur.getListStructures().size() + i; i++) {
//		    controleur.enleverStructure(controleur.getListStructures().get(0), this);
//		}
		if (musiqueJeuPlay.playing()) {

		    musiqueJeuPlay.stop();
		}
		finDeJeu = false;
		debutDejeu = false;
		sbg.enterState(0);
	    } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
	    }
	}

	if (debutDejeu) {
	    //continue de jouer 
	    if (!finDeJeu) {//inutiliser
		//**key input
		//si le curseur n'est pas sur les bars noirs
		if (!focusMenu) {
		    //touche pour lancer
		    if (Key.isKeyDown(Input.KEY_SPACE) || gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			if (force < -1 || force > 100) {
			    coteDeLaForce *= -1;
			}
			force += 2 * coteDeLaForce;
		    } else {
			//lorsque l'on relache, le projectiles fait feu
			if (force != 0) {
			    angle = Math.toDegrees((Math.atan((double) posY / posX)));
			    controleur.addProjectile((int) (170 * Math.cos(Math.toRadians(angle))), (int) (170 * Math.sin(Math.toRadians(angle))), force / 5, angle, 0.8);
			    force = 0;
			}
		    }

		}
		//**

		//Création de nouvelles images, regard s'il y a un objet affichable
		if (controleur.getNouvelleItemAffichable() != null) {
		    switch (controleur.getNouvelleItemAffichable().getNomImg()) {
			case "spiritesheet.png":
			    addAnimationProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
			    break;
			case "structure.png":
			    addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
			    break;
			case "heartsprite.png"://inutiliser
			    addAnimationCibles(controleur.getNouvelleItemAffichable().getNomImg());
			    break;
		    }
		    controleur.setNouvelleItemAffichable(null);
		}

		//Canon rotation
		canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);

		//Mouvement
		controleur.bougerProjectiles();
		controleur.rebondProjectilesMurLoop();
		controleur.rebondProjectilesStructuresLoop();

		//reverse
		for (int i = 0; i < listAnimationProjectiles.size(); i++) {
		    if (controleur.getListProjectiles().get(i).isReverse()) {
			if ("spiritesheet.png".equals(listAnimationProjectiles.get(i).getCurrentFrame().getName())) {
			    listAnimationProjectiles.remove(i);
			    addAnimationProjectiles(i, controleur.getListProjectiles().get(i).getNomImgReverse());
			}
		    } else if ("spiritesheetreverse.png".equals(listAnimationProjectiles.get(i).getCurrentFrame().getName())) {
			listAnimationProjectiles.remove(i);
			addAnimationProjectiles(i, controleur.getListProjectiles().get(i).getNomImg());
		    }
		}
	    }
	}

	//fin de jeu // inutiliser
	if (controleur.getListCibles().size() <= -2 || controleur.getListProjectiles().size() >= 6) {
	    controleur.enleverProjectiles(controleur.getListProjectiles().get(controleur.getListProjectiles().size() - 1), this);
	    finDeJeu = true;
	}
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

	//Position de la souris
	int posX = Mouse.getX();
	int posY = Mouse.getY();

<<<<<<< HEAD
        
=======
>>>>>>> 5cf328c90918c8b1c3bf9397db0dcbef4546cdfd
	//background
	bg.draw();

	g.setColor(Color.black);
	//positions et mouvements des projectiles
	for (int i = 0; i < listAnimationProjectiles.size(); i++) {
	    listAnimationProjectiles.get(i).draw((int) (controleur.positionProjectileX(i)), (int) (controleur.positionProjectileY(i)));
	}
	//positions et mouvements des structures
	for (int i = 0; i < listImagesStructures.size(); i++) {
	    listImagesStructures.get(i).draw((int) (controleur.positionStructureX(i)), (int) (controleur.positionStructureY(i)));
	}
	//positions et mouvements des cibles
	for (int i = 0; i < listAnimationCibles.size(); i++) {
	    listAnimationCibles.get(i).draw((int) (controleur.positionCiblesX(i)), (int) (controleur.positionCiblesY(i)));
	}
	
	if (finDeJeu) {//inutiliser
	    g.drawString("fin", 500, 300);
	}

	if (!debutDejeu) {
	    g.drawString("click entrer pour commencer", 500, 300);
	}

	//boutons
	buttonExit.draw(1110, 5);

	//canon
	canon.draw(85 - 195, 500 + 7);
	roue.draw(35, 506 + 7);

	//barre de force
	g.fillRect(100, 250, (int) force, 20);
    }

    public int getID() {
	return state;
    }

    public void addAnimationProjectiles(String nomImg) throws SlickException {

	projAnimation = new Animation(new SpriteSheet(nomImg, 80, 59), 60);
	projAnimation.getCurrentFrame().setName(nomImg);
	listAnimationProjectiles.add(projAnimation);
    }

    public void addAnimationProjectiles(int i, String nomImg) throws SlickException {

	projAnimation = new Animation(new SpriteSheet(nomImg, 80, 59), 60);
	projAnimation.getCurrentFrame().setName(nomImg);
	listAnimationProjectiles.add(i, projAnimation);
    }

    @Override
    public void addImageStructures(String nomImg) throws SlickException {

	Image img = new Image(nomImg);
	img.setName(nomImg);
	listImagesStructures.add(img);
    }

    @Override
    public void addAnimationCibles(String nomImg) throws SlickException {

	cibleAnimation = new Animation(new SpriteSheet(nomImg, 25, 40), 140);
	cibleAnimation.getCurrentFrame().setName(nomImg);
	listAnimationProjectiles.add(cibleAnimation);
    }

    //getters and setters
    @Override
    public ArrayList<Animation> getListAnimationProjectiles() {
	return listAnimationProjectiles;
    }

    public void setListAnimationProjectiles(ArrayList<Animation> listAnimationProjectiles) {
	this.listAnimationProjectiles = listAnimationProjectiles;
    }

    @Override
    public ArrayList<Image> getListImagesStructures() {
	return listImagesStructures;
    }

    public void setListImagesStructures(ArrayList<Image> listImagesStructures) {
	this.listImagesStructures = listImagesStructures;
    }

    public ArrayList<Animation> getListAnimationCibles() {
	return listAnimationCibles;
    }

    public void setListAnimationCibles(ArrayList<Animation> listAnimationCibles) {
	this.listAnimationCibles = listAnimationCibles;
    }
}
