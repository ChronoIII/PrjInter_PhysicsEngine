/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.vue;

/**
 *
 * @author Ordi-de-Zman
 */
public class Vecteur {

    private double x;
    private double y;
    
    public Vecteur(int x, int y){
	this.x = x;
	this.y = y;
    }
     public Vecteur(){
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }
}
