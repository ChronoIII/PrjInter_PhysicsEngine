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
    private Image buttonSettings;
    private Image introPhoto;
    private Music introMusic;

    public MainMenu(int state, Controleur controleur) throws SlickException {
        this.state = state;
        this.controleur = controleur;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("little_baby.jpg");
        introPhoto = new Image("limbo (2).jpg");
        buttonPlay = new Image("buttonsp.jpg");
        buttonCreate = new Image("buttons.jpg");
        buttonSettings = new Image("buttons.jpg");
        introMusic = new Music("intro.wav");
        introMusic.loop();

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input a = gc.getInput();
        introMusic.setVolume(0.25f);
        int posX = Mouse.getX();
        int posY = Mouse.getY();
//        if (a.isKeyDown(Input.KEY_ESCAPE)) {
//            System.exit(1);
//        }
        if ((posX > 795 && posX < 1168) && (posY > 182 && posY < 225)) {
            if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPlay = new Image("buttons - Copy.jpg");
                System.out.println("got clicked buddy!");
                introMusic.stop();
                sbg.enterState(1);
            } else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                buttonPlay = new Image("buttonsp.jpg");

            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        introPhoto.draw();
        buttonPlay.draw(795, 450);
        buttonCreate.draw(795, 510);
        buttonSettings.draw(795, 570);
//        Image img = new Image ("little_baby.jpg");
//	g.fillRect(100, 100, 100, 100);
//	g.fillRect(200, 200, 200, 200);
//         img.draw(100,100);
    }

    @Override
    public int getID() {
        return state;
    }
}
