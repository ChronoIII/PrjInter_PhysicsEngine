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

    int state;
    Controleur controleur;
    private ArrayList<Image> listImagesStructures;
    private ArrayList<Animation> listAnimationProjectiles;
    private Image catapule;
    private Image bg;
    double t = 0;
    TextField textfield;
    double force = 0;
    double angle = 0;
    private Music musiqueJeuPlay;
    private Animation projtest;
    private SpriteSheet projsheet;
    //------buttons----//
    private Image buttonInventaire;
    private Image buttonInventaire2;
    private Image buttonSettings;
    private Image buttonSave;
    private Image buttonLoad;
    private Image buttonPlay;
    private Image buttonPause;
    private Image buttonExit;
    private Image roue;
    private Image canon;
    private Image inventaireExit;
    private float lol = 90;
//    true = false, false = play
    private boolean modePausePlay;
    private boolean inventaire;
    private Color colorAlpha = new Color(1f, 1f, 1f, 0.75f);
    private boolean focusMenu = false;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
        this.state = state;
        this.controleur = controleur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


        Input a = gc.getInput();
        modePausePlay = true;
        inventaire = false;
        projsheet = new SpriteSheet("spiritesheet.png", 80, 59);
        projtest = new Animation(projsheet, 250);

        listAnimationProjectiles = new ArrayList<Animation>();
        listImagesStructures = new ArrayList<Image>();
        bg = new Image("background.jpg");

        buttonInventaire = new Image("inventory.png");
        buttonInventaire2 = new Image("inventory2.png");
        buttonSettings = new Image("settings2.png");
        buttonSave = new Image("save.png");
        buttonLoad = new Image("load.png");
        buttonPlay = new Image("play.png");
        buttonPause = new Image("pause.png");
        buttonExit = new Image("exit.png");
        inventaireExit = new Image("exitinventaire.png");
        roue = new Image("wheel.png");
        canon = new Image("canon.png");
        canon.rotate(Mouse.getX());
//        canon.rotate(280);
//        musiqueJeuPlay = new Music("gamemusic.wav");

//        musiqueJeuPlay.loop();

//	controleur.addProjectile(x, y);
//	c = new Ennemie(controleur);



        textfield = new TextField(gc, gc.getDefaultFont(), 5, 11, 200, 30);
        textfield.setBorderColor(Color.black);
        textfield.setBackgroundColor(Color.white);
        textfield.setTextColor(Color.black);
        textfield.setText("Score:");

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        // On met à jour à chaque seconde
//	controleur.avancerTemps();

        Input Key = gc.getInput();
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((Mouse.getX() < 1200 && Mouse.getY() > 600 && Mouse.getY() < 675) || (Mouse.getX() < 1200 && Mouse.getY() > 0 && Mouse.getY() < 88)) {
            focusMenu = true;
            System.out.println("lol");
        } else {
            focusMenu = false;
        }

        //Bouton///////////////////////////////////////////////////////////////////////////////
        //inventaire
        if (!inventaire) {
            if ((posX > 10 && posX < 85) && (posY > 10 && posY < 75)) {
                if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    buttonInventaire = new Image("inventory2.png");
                    System.out.println("Inventaire clicker");
                    inventaire = true;


                } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    buttonInventaire = new Image("inventory.png");
                }
                //exit
                if ((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) {
                    if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                        System.out.println("got clicked buddy! lets go back!");
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
//        save
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
            //fin Bouton///////////////////////////////////////////////////////////////////////////////

            //key input////////////////////////////////////////////////////////////////////////////
            if (!focusMenu) {
                if (Key.isKeyDown(Input.KEY_SPACE)) {
                    force += 3;
                } else {
                    if (force != 0) {
                        angle = Math.toDegrees((Math.atan((double) posY / posX)));
                        System.out.println("" + angle);
                        controleur.addProjectile(0, 0, force / 3, angle, 0.8);
                        force = 0;
                    }
                }

                if (Key.isKeyPressed(Input.KEY_1)) {


                    if (!controleur.listProjectiles().isEmpty()) {

                        controleur.listProjectiles().get(0).detruir();
                    }
                    if (controleur.listProjectiles().isEmpty()) {
                    }
                }
                if (Key.isKeyDown(Input.KEY_M)) {

                    sbg.enterState(0);
                }

                //fin key input///////////////////////////////////////////////////////////////////////

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




                //nouvelle image
                if (controleur.getNouvelleItemAffichable() != null) {
                    switch (controleur.getNouvelleItemAffichable().getNomImg()) {
                        case "animation2.png":
                            addAnimationProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
                            break;
                        case "inventory.png":
                            addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
                            break;
                    }
                }
            }}

            //mouvement///////////////////////////////////////////////////////////////////////////////

            controleur.bougerProjectiles();
            controleur.rebondProjectilesMur();

            //fin mouvement//////////////////////////////////////////////////////////////////////////////////////////

            //Canon rotation
            if(!inventaire){
            if (!focusMenu) {
                canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);

            }}

        
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
        }
        for (int i = 0; i < listImagesStructures.size(); i++) {
            listImagesStructures.get(i).draw((int) (controleur.positionStructureX(i)), (int) (controleur.positionStructureY(i)));
        }


        g.setColor(Color.black);
        g.drawString("force: " + force, 300, 100);
        g.drawString("angle: " + angle, 300, 150);
        g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 300, 200);
        textfield.render(gc, g);
//        projtest.draw(100, 100); //bat test
        buttonInventaire.draw(10, 600);
        buttonPlay.draw(100, 600);
        buttonExit.draw(1110, 5);

        buttonLoad.draw((1110), 600);
        buttonSave.draw((1110 - 90), 600);

        canon.draw(85 - 195, 500);

        roue.draw(35, 506);
        if (inventaire) {

            g.setColor(colorAlpha);
            g.fillRoundRect(0, 50, 475, 570, 30);
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
