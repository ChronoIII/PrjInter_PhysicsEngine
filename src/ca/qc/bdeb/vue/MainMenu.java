
package ca.qc.bdeb.vue;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.inf204.Main;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


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
    private Sound detruire;
    private Sound jouons;
    private boolean focusCredit = false;
    private Color colorAlpha = new Color(1f, 1f, 1f, 0.75f);
    private Image inventaireExit;
    private Image exitGame;

    public MainMenu(int state, Controleur controleur) throws SlickException {
	this.state = state;
	this.controleur = controleur;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	introPhoto = new Image("realbg.jpg");
	buttonPlay = new Image("buttonPlay.jpg");
	buttonCreate = new Image("buttonCreate.jpg");
	buttonCredits = new Image("buttonCredits.jpg");
	iris = new Image("eyeball.png");
	introMusic = new Music("intro.wav");

	isClicked = true;
	rire = new Sound("rire.wav");
	jouons = new Sound("letsplay.wav");
	detruire = new Sound("destroy.wav");
	inventaireExit = new Image("exitinventaire.png");
	exitGame = new Image("exit.png");


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	Input key = gc.getInput();
	introMusic.setVolume(0.25f);

	if (!introMusic.playing()) {

	    introMusic.play();
	    introMusic.loop();
	}

	int posX = Mouse.getX();
	int posY = Mouse.getY();
	if (isClicked) {
	    buttonPlay = new Image("buttonPlay.jpg");
	} else {
	    buttonPlay = new Image("buttons - Copy.jpg");
	}
	if (!focusCredit) {
	    //boutons
	    //play
	    if ((posX > 795 && posX < 1168) && (posY > 182 && posY < 225)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    System.out.println("PLAY");
		    jouons.play();
		    introMusic.stop();
		    sbg.enterState(2);
                    gc.sleep(2500);
		}
	    }
	    //create

	    if ((posX > 795 && posX < 1168) && (posY > 121 && posY < 166)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

		    System.out.println("CREATE");

		    introMusic.stop();
                    detruire.play();
                    gc.sleep(2500);
		    sbg.enterState(1);

		}
	    }
	    //credits
	    if ((posX > 795 && posX < 1168) && (posY > 61 && posY < 105)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

		    System.out.println("CREDITS");

		    rire.play();
		    focusCredit = true;
		} else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
		}


	    }
	    if ((posX > 1110 && posX < 1186) && (posY > 607 && posY < 670)) {
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    introMusic.stop();
		    rire.play();
		    gc.sleep(1500);
		    System.out.println("the end");
		    Main.exit();
		} else if (!gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
		}
	    }
	}
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	int posX = Mouse.getX();
	int posY = Mouse.getY();

	introPhoto.draw();
	buttonPlay.draw(795, 450);
	buttonCreate.draw(795, 510);
	buttonCredits.draw(795, 570);
	exitGame.draw(1110, 5);
	iris.draw(297 + Mouse.getX() * 13 / 1200, 220 - Mouse.getY() * 8 / 675);

	if (focusCredit) {

	    g.setColor(colorAlpha);
	    g.fillRoundRect(271, 75, 600, 570, 30);
	    inventaireExit.draw(435 + 271 + 125, 55);
	    g.setColor(Color.black);
	    g.drawString(" Fait par\n Samuel Laroche \n Amélie Nguyen\n et Zi Long Li\n nous vous présentons un version\n simplifier d'un moteur physique.", 400, 200);

	    //bouton pour fermer la fenêtre
	    if ((posX > 833 && posX < 880) && (posY > 573 && posY < 619)) {

		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		    focusCredit = false;


		}
	    }

	}

    }

    @Override
    public int getID() {
	return state;
    }
}
