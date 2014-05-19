/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

/**
 * Obliger les éléments affichables d'avoir certaines caratéristiques
 * 
 */
public interface Affichable {
    
    public String getNomImg();
    public Vecteur getPosition();
    public void setPosition(Vecteur position);
    
}
