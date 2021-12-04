package piece;

import echiquier.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;
import jeu.Joueur;

public abstract class Piece implements IPiece {
	private char nom;
	private String couleur;
	private Coord coord;
	private Echiquier echiquier;
	private String famille;
	
	/**
	 * CONSTRUCTEUR DE PIECE
	 * @param x
	 * @param y
	 * @param nom
	 * @param couleur
	 * @param famille
	 * @param echiquier
	 */
	public Piece(int x, int y, char nom, String couleur,String famille,Echiquier echiquier){
    	this.coord=new Coord(x,y);
    	this.nom = nom;
    	this.couleur=couleur;
    	this.famille=famille;
    	this.echiquier=echiquier;
    }
	//-------------------------
	//getters
	public int getX() {
		return this.coord.getX();
	}
	public int getY() {
		return this.coord.getY();
	}
	public Coord getCoord() {
		return this.coord;
	}
	public char getNom() {
		return this.nom;
	}
	public String getCouleur() {
		return this.couleur;
	}
	public String getFamille() {
		return this.famille;
	}
	public Echiquier getEchiquier() {
		return echiquier;
	}
	//-------------------------
    /**
     * Setter pour x et y a la fois
     * pour changer les coordonnes de la piece
     * @param coord
     */
    public void setXY(Coord coord){
    	this.coord=coord;
    }
    /**
     * Verifie si la case d'arrivée est vide
     * @return true ou false
     */
    public boolean verifCaseLibre(Coord coord) {
    	if(this.echiquier.caseVide(coord))
    		return true;
    	return false;
    }
    /**
     * Verifie si le mouvement est possible
     * @param Jcourant : joueur courant
     * @param coord : coord case arrivee
     * @return true ou false
     */
    @Override
    public boolean verifMouv(Joueur Jcourant,Coord coord) { //regle+cheminLibre
    	if(verifCaseLibre(coord)) {
    		if(verifCheminLibre(coord)) {
    			if(coupPossible(coord)) 
    				return true;
    		}
    	}
    	return false;
    }
    /**
     * Verifie si la case d'arrivée est occupée par une piece adverse
     * @param Jcourant : joueur courant
     * @param coord : coord case arrivee
     * @return true ou false
     */
    @Override 
    public boolean verifCaseOccupeAdverse(Joueur Jcourant, Coord coord) {
    	if(Jcourant.getCouleur()=="blanc")
    		if(!this.echiquier.caseVide(coord))
		    	if(this.echiquier.getPiece(coord).getCouleur().equals("noir"))
		    		return true;
    	if(Jcourant.getCouleur()=="noir")
    		if(!this.echiquier.caseVide(coord))
		    	if(this.echiquier.getPiece(coord).getCouleur().equals("blanc"))
		    		return true;
    	return false;
    }
    
    /**
     * Verifie si la piece peut manger la piece adverse 
     * @param Jcourant : joueur courant
     * @param coord : coord case arrivee
     * @return true ou false
	*/
	@Override
    public boolean verifManger(Joueur Jcourant, Coord coord) {//regle + caseOccupAdverse
    	if(verifCaseOccupeAdverse(Jcourant, coord)) {
    		if(verifCheminLibre(coord)) {
    			if(coupPossible(coord))
    				return true;
    		}
    	}
    	return false; 
    }
    /**
     * Verifie si le coup est possible
     * @param Jcourant : joueur courant
     * @param Jadverse : joueur adverse
     * @param coord : tabelau de coord de la saisie
     * @param echiquier : echiquier 
     * @return true ou false

     */
    @Override
    public boolean verifCoup(Joueur Jcourant,Joueur Jadverse, Coord[] coord,Echiquier echiquier) {//pour la saisie?
    	if(verifMouv(Jcourant,coord[1])||verifManger(Jcourant, coord[1])) {
    		//simuler le coup
    		//verifier apres si le roi n'est pas en echec
    		return verifPasEchec(Jcourant,Jadverse,coord,echiquier);
    	}
    	return false;
    }
    /**
     * Verifie si le roi n'est pas en echec apres le coup
     * @param Jcourant
     * @param Jadverse
     * @param coord
     * @param echiquier
     * @return true ou false
     */
    private boolean verifPasEchec(Joueur Jcourant,Joueur Jadverse,Coord[] coord,Echiquier echiquier) {
    	Echiquier simulation=new Echiquier(echiquier);
    	simulation.getPiece(simulation.rechercheRoi(Jcourant)).coup(Jcourant,coord,simulation);
    	if(simulation.getPiece(simulation.rechercheRoi(Jcourant)).estEchec(Jcourant,Jadverse))   //a verifier
    		return false;
    	else return true;
    }
    
    /**
     * Deplace la piece : change les coordonnees de la piece et place dans l'echiquier
     * @param coord : coordonnees de la case d'arrivée
     */
    @Override
    public void deplacer(Echiquier echiquier,Coord[] coord) {
    	setXY(coord[1]);
    	echiquier.setPiece(coord[1], this);
    	echiquier.viderCase(coord[0]);
    }
    /** 
     * Mange la piece adverse : vide la case et deplace la piece
     * @param echiquier
     * @param coord : tableau de coord de la saisie
     */
    @Override
	public void manger(Echiquier echiquier,Coord[] coord) {
    	//enregistrer la piece adverse dans le tab prise
    	echiquier.viderCase(coord[1]);
    	deplacer(echiquier,coord);
    	echiquier.viderCase(coord[0]);

    }
    /**
     * Effectue le coup d'apres la saisie
     * @param Jcourant
     * @param coord
     * @param echiquier 
     */
    @Override
	public void coup(Joueur Jcourant,Coord[] coord,Echiquier echiquier) {
    	if(verifMouv(Jcourant,coord[1])) {
    		deplacer(echiquier,coord);
    	}
    	else if(verifManger(Jcourant, coord[1])) {
    		manger(echiquier,coord);
    	}
    }     
    /**
     * @return le nom de la piece
     */
    public String toString() {
    	return " " + this.nom + " ";
    }
}
