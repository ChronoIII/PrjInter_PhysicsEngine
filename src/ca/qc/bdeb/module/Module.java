
package ca.qc.bdeb.module;

import ca.qc.bdeb.controler.Controleur;
import ca.qc.bdeb.vue.Cibles;
import ca.qc.bdeb.vue.Projectiles;
import ca.qc.bdeb.vue.Structures;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;


public class Module {

    //Listes
    private ArrayList<Projectiles> listProjectiles;
    private ArrayList<Structures> listStructures;
    private ArrayList<Boolean> listStructuresPlacer;
    private ArrayList<Cibles> listCibles;
    private ArrayList<String[]> listPosStructures;
    private ArrayList<String[]> listPosCibles;
    //variable de dimantion
    int width = 1200;
    int height = 675;
    //controlller
    private Controleur controleur;
    

    public Module(Controleur controleur) throws SlickException {

	this.controleur = controleur;

	//Initialiser les liste
	listProjectiles = new ArrayList<Projectiles>();
	listStructures = new ArrayList<Structures>();
	listCibles = new ArrayList<Cibles>();
       
    }

    //se que fait le projectiles lorsqu'il touche un surface
    public void rebond(Projectiles projectile, char orientation) {
	if (orientation == 'y') {
	    projectile.getVitesse().setY(-projectile.getVitesse().getY() * projectile.getFacRebond());
	    projectile.getVitesse().setX(projectile.getVitesse().getX() * projectile.getFacRebond());
	    if (projectile.getVitesse().getY() < 1.80 && projectile.getVitesse().getY() > -1.80) {
		projectile.getVitesse().setY(-projectile.getVitesse().getY() * 0.8);
	    }
	    if (projectile.getVitesse().getY() < 0.18 && projectile.getVitesse().getY() > -0.18) {
		projectile.getVitesse().setY(0);
	    }
	}
	if (orientation == 'x') {
	    projectile.getVitesse().setX(-projectile.getVitesse().getX());
	    projectile.setReverse(!projectile.isReverse());
	}
    }
    
     public void sauvegarde(int nbreProjectiles) {
    }

    public void charger() {
    }
    
    public void lireNiveau(String nomTxt) throws Exception{
	BufferedReader FichierEntree;
	String sLine;
	FichierEntree = new BufferedReader(new FileReader(nomTxt));
	sLine = FichierEntree.readLine();
	while(sLine != null){
	    if(sLine.charAt(0) == 's'){
		listPosStructures.add(sLine.substring(1).split(":"));
	    }
	    if(sLine.charAt(0) == 'c'){
		listPosCibles.add(sLine.substring(1).split(":"));
	    }
	    sLine = FichierEntree.readLine();
	}
	FichierEntree.close();
    }
	    

    //setters and getters
    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public ArrayList<Projectiles> getListProjectiles() {
	return listProjectiles;
    }

    public void setListProjectiles(ArrayList<Projectiles> listeProjectiles) {
	this.listProjectiles = listeProjectiles;
    }

    public ArrayList<Structures> getListStructures() {
	return listStructures;
    }

    public void setListStructures(ArrayList<Structures> listStructures) {
	this.listStructures = listStructures;
    }

    public ArrayList<Cibles> getListCibles() {
	return listCibles;
    }

    public void setListCibles(ArrayList<Cibles> listCibles) {
	this.listCibles = listCibles;
    }

    public ArrayList<String[]> getListPosStructures() {
	return listPosStructures;
    }

    public void setListPosStructures(ArrayList<String[]> listPosStructures) {
	this.listPosStructures = listPosStructures;
    }

    public ArrayList<String[]> getListPosCibles() {
	return listPosCibles;
    }

    public void setListPosCibles(ArrayList<String[]> listPosCibles) {
	this.listPosCibles = listPosCibles;
    }
}
