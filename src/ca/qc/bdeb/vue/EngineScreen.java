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


public class EngineScreen extends BasicGameState implements Screen {

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
    private Image buttonInventaire;
    private Image buttonInventaire2;
    private Image buttonPlay;
    private Image buttonExit;
    private Image inventaireExit;
    //**
    //État dans le jeux
    private boolean modePausePlay; //play = true, pause = false
    private boolean inventaire;
    private boolean focusMenu = false;
    private boolean lockInventaire;//true = ne peut pas etre clicker, false = pas lock, mode placement
    private boolean ombrage;
    //couleur de l'inventaire
    private Color colorAlpha = new Color(0.84f, 0.84f, 0.84f, 0.85f);
    //musique
    private Music musiqueJeuPlay;
    //tableau0
    private int tableauPlacement[][];
    private Structures tableauPlacementStr[][];

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


        //structure&son tableau
        structure = new Image("structure.png");
        tableauPlacement = new int[5][7];

        for (int i = 0; i < tableauPlacement.length; i++) {

            for (int j = 0; j < tableauPlacement[0].length; j++) {

                tableauPlacement[i][j] = 0;

            }
        }

        //Initialisarions des listes
        listAnimationProjectiles = new ArrayList<Animation>();
        listImagesStructures = new ArrayList<Image>();
        listAnimationCibles = new ArrayList<Animation>();

        //Initialisarion background
        bg = new Image("background.jpg");

        //Initialisarion des images Boutons
        buttonInventaire = new Image("inventory.png");
        buttonInventaire2 = new Image("inventory2.png");
        buttonPlay = new Image("pause.png");
        buttonExit = new Image("back.png");
        inventaireExit = new Image("exitinventaire.png");

        //Initialisarion des images canon
        roue = new Image("wheel.png");
        canon = new Image("canon.png");

        //Initialisation animation cible
        coeurSheet = new SpriteSheet("heartsprite2.png", 34, 54);
        cibleAnimation = new Animation(coeurSheet, 140);
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

        //Lock
        if (lockInventaire) {
            if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {
                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                }
            }

        } else {//Lock
            if (lockInventaire) {
                if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {
                    if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        buttonInventaire = new Image("inventory2.png");
                        inventaire = true;

                    } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                        buttonInventaire = new Image("inventory.png");
                    }
                }

            }
        }
        //Pour vérifier si le curseur est sur la bar noir, changant l'état du jeu
        if ((Mouse.getX() < 1200 && Mouse.getY() > 600 && Mouse.getY() < 675) || (Mouse.getX() < 1200 && Mouse.getY() > 0 && Mouse.getY() < 88)) {
            focusMenu = true;
        } else {
            focusMenu = false;
        }
        //pause mode erase projectiles
        if (!modePausePlay) {
            for (int i = 0; i < controleur.getListProjectiles().size() + i; i++) {
                controleur.enleverProjectiles(controleur.getListProjectiles().get(0), this);
            }
//            for (int i = 0; i < controleur.listStructures().size() + i; i++) {
//                controleur.enleverStructure(controleur.listStructures().get(0));
//            }
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

                    for (int i = 0; i < controleur.getListProjectiles().size() + i; i++) {
                        controleur.enleverProjectiles(controleur.getListProjectiles().get(0), this);

                    }
                    for (int i = 0; i < controleur.getListStructures().size() + i; i++) {
                        controleur.enleverStructure(controleur.getListStructures().get(0), this);
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

                        buttonPlay = new Image("play.png");
                        modePausePlay = false;
                    } else {
                        buttonPlay = new Image("pause.png");

                        modePausePlay = true;
                    }
                }
            }

            //**key input
            //si le curseur n'est pas sur les bars noirs
            if (!focusMenu) {
                if (modePausePlay) {
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
                    case "heartsprite.png"://n'est pas utiliser
                        addAnimationCibles(controleur.getNouvelleItemAffichable().getNomImg());
                        break;
                }
                controleur.setNouvelleItemAffichable(null);
            }

            //Canon rotation


            if (modePausePlay && !inventaire && !focusMenu) {
                canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);
            }



        } else { //quand je suis dans inventaire
            //dans Inventaire, click structure et cible et exit
            //clique sur structure
            if ((posX > 35 && posX < 85) && (posY > 460 && posY < 510)) {
                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    System.out.println("struc pris");
//                    controleur.addStructure(0, 0);
                    inventaire = false;
                    lockInventaire = true;
                }
            }

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

        //Mouvement
        controleur.bougerProjectiles();
        controleur.rebondProjectilesMurLoop();
	controleur.collisionCibleProjectilesloop(this);


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



        //placement des 1 dans le tableau de numero
        int compteur;
        if (lockInventaire) {

            if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                compteur = 1;

                for (int j = 0; j < tableauPlacement[0].length; j++) {
                    if (Mouse.getX() - 100 * (j + 1) >= 450 && Mouse.getX() - 100 * (j + 1) <= 500) {

                        for (int i = tableauPlacement.length - 1; i >= 0; i--) {
                            if (compteur == 1) {
                                if (tableauPlacement[i][j] == 0) {
                                    System.out.println("je suis en " + i + "," + j);
                                    tableauPlacement[i][j] = 1;
                                    compteur = 0;
                                     lockInventaire = false;
                                }
                            }

                        }
                    }
                }
            }
           
        }

        for (int i = 0; i < tableauPlacement.length; i++) {
            for (int j = 0; j < tableauPlacement[0].length; j++) {
                if (tableauPlacement[i][j] == 1) {
                    controleur.addStructure(90 + 100 * i - 3, (j * 100) + 500);
                }
            }

        }

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

        //canon
        canon.draw(85 - 195, 500 + 7);
        roue.draw(35, 506 + 7);

        //barre de force
        g.fillRect(100, 250, (int) force, 20);

        //lorsque l'inventaire est ouvert (fin mouvement structure)
        if (inventaire) {
            //dessiner le menu de l'inventaire et le bouton exit
            g.setColor(colorAlpha);
            g.fillRoundRect(0, 80, 475, 505, 30);
            inventaireExit.draw(435, 55);
            cibleAnimation.draw(35, 105);
            structure.draw(35, 165);

        }
        // FIN INVENTAIRE
    }

    public int getID() {
        return state;
    }

    //ajouer un objet affichable
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

    //méthode inutiliser
    public void addAnimationCibles(String nomImg) throws SlickException {

        cibleAnimation = new Animation(new SpriteSheet(nomImg, 25, 40), 140);
        cibleAnimation.getCurrentFrame().setName(nomImg);
        listAnimationProjectiles.add(cibleAnimation);
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

    public ArrayList<Animation> getListAnimationCibles() {
        return listAnimationCibles;
    }

    public void setListAnimationCibles(ArrayList<Animation> listAnimationCibles) {
        this.listAnimationCibles = listAnimationCibles;
    }
}
