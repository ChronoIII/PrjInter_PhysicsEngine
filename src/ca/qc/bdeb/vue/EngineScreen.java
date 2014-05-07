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
    int coteDeLaForce = 1;
    //**Image
    private Image bg;
    //projectile
    private Animation projAnimation;
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
    private boolean modePausePlay; //play = trye, pause = false
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
        //Initialisation musique
        musiqueJeuPlay = new Music("music.wav");
        musiqueJeuPlay.setVolume(0.10f);

        //Initialisarions des états
        modePausePlay = true;
        inventaire = false;

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


        //Loop musique
        if (!musiqueJeuPlay.playing()) {

            musiqueJeuPlay.loop();
        }
        //Position de la souris
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        //Pour vérifier si le curseur est sur la bar noir, changant l'état du jeu
        if ((Mouse.getX() < 1200 && Mouse.getY() > 600 && Mouse.getY() < 675) || (Mouse.getX() < 1200 && Mouse.getY() > 0 && Mouse.getY() < 88)) {
            focusMenu = true;
        } else {
            focusMenu = false;
        }
        //pause mode erase projectiles
        if (!modePausePlay) {
            for (int i = 0; i < controleur.listProjectiles().size() + i; i++) {
                controleur.enleverProjectiles(controleur.listProjectiles().get(0));
            }
            force = 0;
            canon.setRotation(-35);

        }

        //inventaire(Etat de jeu), lorsque l'on n'est pas dans l'inventaire 
        if (!inventaire) {

            if (!modePausePlay) {
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
            }

            //exit
            if ((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) {
                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    System.out.println("got clicked buddy! lets go back!");

                    for (int i = 0; i < controleur.listProjectiles().size() + i; i++) {
                        controleur.enleverProjectiles(controleur.listProjectiles().get(0));
                    }
                    if (musiqueJeuPlay.playing()) {
                        musiqueJeuPlay.stop();

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
            if (!modePausePlay) {
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
            }

            //**key input
            //si le curseur n'est pas sur les bars noirs
            if (!focusMenu) {
<<<<<<< HEAD
                if (modePausePlay) {
                    //touche pour lancer
                    if (Key.isKeyDown(Input.KEY_SPACE)) {
                        if (force < -1 || force > 100) {
                            coteDeLaForce *= -1;
                        }
                        force += 2 * coteDeLaForce;
                    } else {
                        //lorsque l'on relache, le projectiles fait feu
                        if (force != 0) {
                            angle = Math.toDegrees((Math.atan((double) posY / posX)));
                            controleur.addProjectile((int) (200 * Math.acos(angle)) + 50, (int) (200 * Math.asin(angle)) - 50, force / 3, angle, 0.8);
                            force = 0;
                        }
=======
                if(modePausePlay){
                //touche pour lancer
                if (Key.isKeyDown(Input.KEY_SPACE)) {
                    if (force < -1 || force > 100) {
                        coteDeLaForce *= -1;
                    }
                    force += 2 * coteDeLaForce;
                } else {
                    //lorsque l'on relache, le projectiles fait feu
                    if (force != 0) {
                        angle = Math.toDegrees((Math.atan((double) posY / posX)));
                        controleur.addProjectile((int) (170 * Math.cos(Math.toRadians(angle))),(int) (170 * Math.sin(Math.toRadians(angle))), force / 4, angle, 0.8);
                        force = 0;
                    }
                }}}
                //**

                //Création de nouvelles images, regard s'il y a un objet affichable
                if (controleur.getNouvelleItemAffichable() != null) {
                    switch (controleur.getNouvelleItemAffichable().getNomImg()) {
                        case "spiritesheet.png":
                            addAnimationProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
                            break;
                        case "Sans titre.png":
                            addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
                            break;
>>>>>>> cdfa2782c132e1df745accc325d6e95bc6439a90
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
                    case "Sans titre.png":
                        addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
                        break;
                }
                controleur.setNouvelleItemAffichable(null);
            }

            //Canon rotation
            if (!inventaire) {
                if (!focusMenu) {
                    if (modePausePlay) {
                        canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);
                    }

                }
            }


        }

        //Mouvement
        controleur.bougerProjectiles();
        controleur.rebondProjectilesMur();

<<<<<<< HEAD
       //reverse
	for (int i = 0; i < listAnimationProjectiles.size(); i++) {
	    if (controleur.listProjectiles().get(i).isReverse()) {
		if ( listAnimationProjectiles.get(i).getCurrentFrame().getName() == "spiritesheet.png") {
		    listAnimationProjectiles.remove(i);
		    addAnimationProjectiles(i, controleur.listProjectiles().get(i).getNomImgReverse());
		}
	    } else if (listAnimationProjectiles.get(i).getCurrentFrame().getName() == "spiritesheetreverse.png") {
		listAnimationProjectiles.remove(i);
		addAnimationProjectiles(i, controleur.listProjectiles().get(i).getNomImg());
	    }
	}
=======
        //reverse
        for (int i = 0; i < listAnimationProjectiles.size(); i++) {
            if (controleur.listProjectiles().get(i).isReverse()) {
                if (listAnimationProjectiles.get(i).getCurrentFrame().getName() == "spiritesheet.png") {
                    listAnimationProjectiles.remove(i);
                    addAnimationProjectiles(i, controleur.listProjectiles().get(i).getNomImgReverse());
                }

            } else if (listAnimationProjectiles.get(i).getCurrentFrame().getName() == "spiritesheetreverse.png") {
                listAnimationProjectiles.remove(i);
                addAnimationProjectiles(i, controleur.listProjectiles().get(i).getNomImg());
            }
        }
>>>>>>> a81b8f3c9cd7bb24b0cf5ef81a7a0313d3b15329

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        //Position de la souris
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        //background
        bg.draw();

        //positions et mouvements des projectiles
        for (int i = 0; i < listAnimationProjectiles.size(); i++) {
            listAnimationProjectiles.get(i).draw((int) (controleur.positionProjectileX(i)), (int) (controleur.positionProjectileY(i)));
//	    listAnimationProjectiles.get(i).getCurrentFrame().setRotation((float) (controleur.orientationProjectil(controleur.listProjectiles().get(i))));
        }
        //positions et mouvements des structures
        for (int i = 0; i < listImagesStructures.size(); i++) {
            listImagesStructures.get(i).draw((int) (controleur.positionStructureX(i)), (int) (controleur.positionStructureY(i)));
        }

        //Stats sur force, angle et les positions du curseur
        g.setColor(Color.black);
        g.drawString("force: " + force, 300, 100);
        g.drawString("angle: " + Math.toDegrees((Math.atan((double) posY / posX))), 300, 150);
        g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 300, 200);

        //boutons
        buttonInventaire.draw(10, 600);
        buttonPlay.draw(100, 600);
        buttonExit.draw(1110, 5);
        buttonLoad.draw((1110), 600);
        buttonSave.draw((1110 - 90), 600);

        //canon
        canon.draw(85 - 195, 500 + 7);
        roue.draw(35, 506 + 7);

        //barre de force
        g.fillRect(100, 250, (int) force, 20);

        //lorsque l'inventaire est ouvert
        if (inventaire) {

            //dessiner le menu de l'inventaire et le bouton exit
            g.setColor(colorAlpha);
            g.fillRoundRect(0, 80, 475, 505, 30);
            inventaireExit.draw(435, 55);

            //exit inventaire des 2 manières
            if ((posX > 438 && posX < 483) && (posY > 573 && posY < 619)) {

                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

                    buttonInventaire = new Image("inventory.png");
                    inventaire = false;
                }
            }
            if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {

                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

                    buttonInventaire = new Image("inventory.png");
                    inventaire = false;
                }
            }
        }

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

    public void addImageStructures(String nomImg) throws SlickException {

        Image img = new Image(nomImg);
        img.setName(nomImg);
        listImagesStructures.add(img);
    }

    //getters and setters
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
