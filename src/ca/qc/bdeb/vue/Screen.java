/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

import org.newdawn.slick.SlickException;

/**
 *
 * @author Samuel
 */
public interface Screen {
    
    public void addImageStructures(String nomImg) throws SlickException;
    
    public void addAnimationCibles(String nomImg) throws SlickException;
}
