/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Samuel
 */
//C'est la que tu dois faire de test d'affichage Amé ^^
public class MainMenu extends BasicGameState {

    private int state;
    private Controleur controleur;
    private Image img;
    private Image buttonPlay;
    private Image buttonCreate;
    private Image buttonCredits;
    private Image introPhoto;
    private Music introMusic;
    private boolean isClicked;
    private Image iris;
    private Sound rire;

    public MainMenu(int state, Controleur controleur) throws SlickException {
        this.state = state;
        this.controleur = controleur;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("little_baby.jpg");
        introPhoto = new Image("realbg.jpg");
        buttonPlay = new Image("buttonPlay.jpg");
        buttonCreate = new Image("buttonCreate.jpg");
        buttonCredits = new Image("buttonCredits.jpg");
        iris = new Image("eyeball.png");
        introMusic = new Music("intro.wav");
        introMusic.loop();
        isClicked = true;
        rire = new Sound ("rire.mp3");
      

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input a = gc.getInput();
        introMusic.setVolume(0.25f);
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (isClicked) {

            buttonPlay = new Image("buttonPlay.jpg");
        } else {
            buttonPlay = new Image("buttons - Copy.jpg");
        }
//boutons
        //play
        if ((posX > 795 && posX < 1168) && (posY > 182 && posY < 225)) {
            if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
                introMusic.stop();
                sbg.enterState(1);
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
               
            }
        }
        //create
        
         if ((posX > 795 && posX < 1168) && (posY > 121 && posY < 166)) {
            if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
                System.out.println("CREATE");
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
               
            }
        }
         //credits
           if ((posX > 795 && posX < 1168) && (posY > 61 && posY < 105)) {
            if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
                System.out.println("CREDITS");
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
               
            }
            
          
        }
           if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                
               rire.play();
            }
           
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       
        introPhoto.draw();
        buttonPlay.draw(795, 450);
        buttonCreate.draw(795, 510);
        buttonCredits.draw(795, 570);
        iris.draw(297+Mouse.getX()*13/1200,220-Mouse.getY()*8/675);
//        Image img = new Image ("little_baby.jpg");
//	g.fillRect(100, 100, 100, 100);
//	g.fillRect(200, 200, 200, 200);
//         img.draw(100,100);
        g.setColor(Color.pink);
        g.drawString("" + Mouse.getX() + ", " + Mouse.getY(), 90, 80);
        
    }

    @Override
    public int getID() {
        return state;
    }
}
