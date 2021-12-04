package piece;


import java.util.ArrayList;

import echiquier.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;
import jeu.Joueur;

public class Roi extends Piece{
	private IPiece pieceMenacante;
	//CONSTRUCTEUR DU ROI
	public Roi(int x, int y, char nom,String couleur,Echiquier echiquier){
		super(x,y,nom,couleur,"roi",echiquier);
	}
	/**
	 * Verifie si le roi est en echec
	 * @param Jcourant
	 * @param Jadverse
	 */
	public boolean estEchec(Joueur Jcourant,Joueur Jadverse) {
		
    	ArrayList<IPiece> piecesAdverse = new ArrayList<>();      //a optimiser si on finit le proj !! car il loue de la memoire a chaque fois
    	//recupere toutes les pieces adverses
    	if(Jcourant.getCouleur().equals("blanc")) {
    		piecesAdverse=this.getEchiquier().getPiecesJoueur("noir");
    	} 
    	else 
    		piecesAdverse=this.getEchiquier().getPiecesJoueur("blanc");
    	
    	for(IPiece piece : piecesAdverse){
    		if(piece.coupPossible(this.getCoord())) {
    			if(piece.getFamille()=="roi") {
    				if(piece.verifManger(Jadverse, this.getCoord())){ //Jcourant
    					pieceMenacante=piece;
    	    			return true;
    				}
    			}
    			else if(piece.verifCheminLibre(this.getCoord())) {
    				pieceMenacante=piece; //recuperer la piece menacante
    				return true;
    			}
    		}
    	}
		return false;
	}
	/**
	 * Verifie si le roi est echec et mat
	 * @param Jcourant
	 * @param Jadverse
	 * @return true ou false
	 * 	 
	*/
	public boolean estEchecEtMat(Joueur Jcourant,Joueur Jadverse) {
		//assert(estEchec)
		if(this.estEchec(Jcourant,Jadverse)) {
			//roi ne peut pas se deplacer
			ArrayList<Coord> casesPossible=this.casesPossible();
			Coord[] tabcoord=new Coord[2];
			tabcoord[0]=this.getCoord();
			for(Coord coord:casesPossible) {
				tabcoord[1]=coord;
				if(this.verifCoup(Jcourant,Jadverse,tabcoord,this.getEchiquier()))
					return false;
			} 
			//les autres pieces ne peuvent pas sauver le roi
			//recuperer les autres pieces du joueur
			ArrayList<IPiece> pieces=this.getEchiquier().getPiecesJoueur(this.getCouleur());
			//if(pieces.contains(this))
			//enlever le roi de la liste
			pieces.remove(this); 
			
			//tester pour chaque piece si elle peut manger la piece menacante ou se placer entre la piece menacante et le roi
			for(IPiece piece:pieces) {
				if(piece.verifManger(Jcourant, pieceMenacante.getCoord())) {
					return false;
				}
				else {
					ArrayList<Coord> cases=pieceMenacante.casesVersRoi(this.getCoord());
					for(Coord c:cases){
						if(piece.verifMouv(Jcourant, c));
							return false;
					}
				}
					
					
			}
			return true;
		}
		else return false;
	}
	/**
	 * Recupere les cases de deplacement possible du roi
	 * @return coord dans arrayList 
	 */
	public ArrayList<Coord> casesPossible(){
		//recuperer les cases ou le roi peut se deplacer
		ArrayList<Coord> cases=new ArrayList<Coord>();
		Coord coord=new Coord();
		 for(int y=this.getY()-1; y<=this.getY()+1; y++) {
            for(int x=this.getX()-1; x<=this.getX()+1; x++) {
               coord.set(x, y);
                if(this.getEchiquier().dansEchiquier(coord)&&(x!=this.getX() || y!=this.getY())) 
                	cases.add(new Coord(x,y));
            }
		 }
		return cases;
	}
	@Override
	/**
	 * Verifie si le coup est possible selon les règles de la piece
	 * ici verifie si la piece se deplace une case autour
	 * @param xy coordonnées de la case d'arrivée
	 * @return true si le coup est possible sinon false
	 */
	public boolean coupPossible(Coord coord) {
		//deplacement horizontal
    	if(coord.getX()==this.getX()+1 && coord.getY()==this.getY() || coord.getX()==this.getX()-1 && coord.getY()==this.getY()){
    		return true;
    	//deplacement vertical
    	}else if(coord.getX()==this.getX() && coord.getY()==this.getY()+1 || coord.getX()==this.getX() && coord.getY()==this.getY()-1){
    		return true;
    	//deplacement dans les 4 diagonales
    	}else if(coord.getX()==this.getX()+1 && coord.getY()==this.getY()+1 || coord.getX()==this.getX()+1 && coord.getY()==this.getY()-1 || coord.getX()==this.getX()-1 && coord.getY()==this.getY()-1 || coord.getX()==this.getX()-1 && coord.getY()==this.getY()+1){
    		return true;
    	}  
		return false;
	}
    
	/**
	 * verifie si le chemin est libre jusqua la case d'arrivée sans compter celle la
	 * @param coord
	 * @return true ou false
	 */
	@Override
	public boolean verifCheminLibre(Coord coord) {
		if(verifCaseLibre(coord))
			return true;
		return false; 
	}
	/**
	 * verifie si le roi peut manger une piece
	 * @param Jcourant
	 * @param coord
	 * @return true ou false
	 */
	@Override
	 public boolean verifManger(Joueur Jcourant, Coord coord) {//regle + caseOccupAdverse
	    if(verifCaseOccupeAdverse(Jcourant, coord)) {
	    	if(coupPossible(coord))
	    		return true;
	    }
	    return false; 
	 }
	
	@Override
	public ArrayList<Coord> casesVersRoi(Coord coordRoi) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
