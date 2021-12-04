package piece;

import java.util.ArrayList;

import echiquier.Coord;
import echiquier.Echiquier;
import jeu.Joueur;

public class Tour extends Piece{
	/**
	 * CONSTRUCTEUR
	 * @param x
	 * @param y
	 * @param couleur
	 */
	public Tour(int x, int y, char nom,String couleur,Echiquier echiquier){
		super(x,y,nom,couleur,"tour",echiquier);
	}
	
	@Override
	/**
	 * Verifie si le coup est possible selon les règles de la piece
	 * ici verifie si la piece se deplace verticalement ou horizontalement
	 * @param xy coordonnées de la case d'arrivée
	 * @return true si le coup est possible sinon false
	 */
	public boolean coupPossible(Coord coord) {
		for(int i = 0;i <=Echiquier.TAILLE_ECHIQUIER;i++){
			//deplacement vertical (sur la meme colonne)
    		if(i==coord.getX() && coord.getY()==this.getY()){
    			return true;
    		}
    		//deplacement horizontal (sur la meme ligne)
    		if(i==coord.getY() && coord.getX()==this.getX()){
    			return true;
    		}
    	}
		return false;
	}
	/**
	 * Verifie si le chemin est libre de la tour jusqu'a la case d'arrivée
	 * @param coord : case d'arrivee
	 * @return true ou false
	 */
	@Override 
	public boolean verifCheminLibre(Coord coord) {
		Coord coordCase=new Coord();
	    if(this.getX() != coord.getX() && this.getY() ==coord.getY()){
	    	if(this.getX() > coord.getX()){ // vers haut
	    		for(int i=this.getX()-1;i>coord.getX();i--){
	    			coordCase.set(i, coord.getY());
	    			if(!this.getEchiquier().caseVide(coordCase)){
	    				return false; 
	    			}
	    		}
	    		return true;
	    	}
	    	if(this.getX() < coord.getX()){ // vers bas
	    		for(int i=this.getX()+1;i<coord.getX();i++){
	    			coordCase.set(i, coord.getY());
	    			if(!this.getEchiquier().caseVide(coordCase)){
	    				return false;
	    			}
	    		} 
	    		return true;
	    	}
	    }
	    if(this.getY() != coord.getY() && this.getX() == coord.getX()){
	    	if(this.getY() > coord.getY()){ // vers gauche
	    		for(int i=this.getY()-1;i>coord.getY();i--){
	    			coordCase.set(coord.getX(),i);
	    			if(!this.getEchiquier().caseVide(coordCase)){
	    				return false;
	    			}
	    		}
	    		return true;
	    	}
	    	if(this.getY() < coord.getY()){ // vers droite
	    		for(int i=this.getY()+1;i<coord.getY();i++){
	    			coordCase.set(coord.getX(),i);
	    			if(!this.getEchiquier().caseVide(coordCase)){
	    				return false;
	    			}
	    		}
	    	return true;
	    	}
	    }
	    return false;
	}
	/**
	 * Recupere les cases de deplacement possible 
	 * @return les coord dans arrayList
	 */
	@Override
	public ArrayList<Coord> casesPossible() {
		ArrayList<Coord> cases=new ArrayList<Coord>();
		Coord coord=new Coord();
		// vertical haut
		for (int x = this.getX() - 1; x >= 0; x--) {
			coord.set(x, this.getY());
			if (this.getEchiquier().dansEchiquier(coord) && (x != this.getX()) && this.getEchiquier().caseVide(coord))
				cases.add(new Coord(x, this.getY()));
			else
				break;
		}
		//vertical bas
		for(int x=this.getX()+1; x<Echiquier.TAILLE_ECHIQUIER; x++) {
		    coord.set(x, this.getY());
		    if(this.getEchiquier().dansEchiquier(coord)&&(x!=this.getX())&&this.getEchiquier().caseVide(coord)) 
		          cases.add(new Coord(x,this.getY()));
		    else break;
		}
		//horizontal gauche
		for(int y=this.getY()-1; y>=0; y--) {
		    coord.set(this.getX(), y);
		    if(this.getEchiquier().dansEchiquier(coord)&&(y!=this.getY())&&this.getEchiquier().caseVide(coord)) 
		    	cases.add(new Coord(this.getX(),y));
			else break;
		}
		//horizontal droite
		for (int y = this.getY() + 1; y < Echiquier.TAILLE_ECHIQUIER; y++) {
			coord.set(this.getX(), y);
			if (this.getEchiquier().dansEchiquier(coord) && (y != this.getY()) && this.getEchiquier().caseVide(coord))
				cases.add(new Coord(this.getX(), y));
			else
				break;
		}
		return cases;
	}
	
	//si la piece menace le roi
	/**
	 * Recupere les cases jusqu'au roi
	 * @param coordRoi
	 * @return coord dans arrayList
	 */
	@Override
	public ArrayList<Coord> casesVersRoi(Coord coordRoi){ //a revoir
    	ArrayList<Coord> cases=new ArrayList<Coord>();
    		//si le roi est en haut
    		if(coordRoi.getX()<this.getX()&&coordRoi.getY()==this.getY()) {
    			for(int i=this.getX()-1;i>coordRoi.getX();i--) {
    				
    				cases.add(new Coord(i,this.getY()));
    			}
    		}
    		//si le roi est en bas
    		if(coordRoi.getX()>this.getX()&&coordRoi.getY()==this.getY()) {
    			for(int i=this.getX()+1;i<coordRoi.getX();i++) {
    				cases.add(new Coord(i,this.getY()));
    			}
    		}
    		//si le roi est a droite
    		if(coordRoi.getX()==this.getX()&&coordRoi.getY()>this.getY()) {
    			for(int i=this.getY()+1;i<coordRoi.getY();i++) {
    				
    				cases.add(new Coord(this.getX(),i));
    			}
    		}
    		//si le roi est a gauche
    		if(coordRoi.getX()==this.getX()&&coordRoi.getY()<this.getY()) {
    			for(int i=this.getY()-1;i>coordRoi.getY();i--) {
    				
    				cases.add(new Coord(this.getX(),i));
    			}
    		}
    	
    	return cases;
    }

	@Override
	public boolean estEchecEtMat(Joueur Jcourant,Joueur Jadverse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estEchec(Joueur Jcourant,Joueur Jadverse) {
		// TODO Auto-generated method stub
		return false;
	}
	

	



	
	

}
