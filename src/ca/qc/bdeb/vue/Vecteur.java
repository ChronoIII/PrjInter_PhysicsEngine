
package ca.qc.bdeb.vue;


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
