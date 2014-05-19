
package ca.qc.bdeb.vue;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public interface Screen {
    
    public void addImageStructures(String nomImg) throws SlickException;
    
    public void addAnimationCibles(String nomImg) throws SlickException;
    
    public ArrayList<Animation> getListAnimationProjectiles();
    
    public ArrayList<Image> getListImagesStructures();
}
