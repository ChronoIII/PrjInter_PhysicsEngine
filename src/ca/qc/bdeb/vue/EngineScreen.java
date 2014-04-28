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
    private float lol = 90;

    public EngineScreen(int state, Controleur controleur) throws SlickException {
        this.state = state;
        this.controleur = controleur;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


        Input a = gc.getInput();
        projsheet = new SpriteSheet("animation2.png", 68, 54);
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

        //Bouton///////////////////////////////////////////////////////////////////////////////
        //inventaire
        if ((posX > 10 && posX < 85) && (posY > 75 && posY < 10)) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonInventaire = new Image("inventory.png");
                System.out.println("allo");
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonInventaire = new Image("inventory.png");
                System.out.println("allo");
            }
        }

        if ((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPlay = new Image("buttons - Copy.jpg");
                System.out.println("got clicked buddy! lets go back!");
                sbg.enterState(0);
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            }
        }

        //Bouton///////////////////////////////////////////////////////////////////////////////

        //key input////////////////////////////////////////////////////////////////////////////
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
                case "animation2.png":
                    addAnimationProjectiles(controleur.getNouvelleItemAffichable().getNomImg());
                    break;
                case "Sans titre.png":
                    addImageStructures(controleur.getNouvelleItemAffichable().getNomImg());
                    break;
            }
            controleur.setNouvelleItemAffichable(null);
        }

        //mouvement///////////////////////////////////////////////////////////////////////////////
        controleur.bougerProjectiles();
        controleur.rebondProjectiles();
        //fin mouvement//////////////////////////////////////////////////////////////////////////////////////////

        canon.setRotation((float) Math.toDegrees((Math.atan((double) posX / posY))) + 270);
    }

    public int getID() {
        return state;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw();


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
        projtest.draw(100, 100);
        buttonInventaire.draw(10, 600);
        buttonPlay.draw(100, 600);
        buttonExit.draw(1110, 5);
        buttonSettings.draw(1110, 600);
        buttonLoad.draw((1110 - 90), 600);
        buttonSave.draw((1110 - 180), 600);

        canon.draw(85 - 195, 500);

        roue.draw(35, 506);

    }

   
    public void addAnimationProjectiles(String nomImg) throws SlickException {
    
        SpriteSheet projsheet = new SpriteSheet(nomImg, 68, 54);
        Animation projtest = new Animation(projsheet, 50);
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
