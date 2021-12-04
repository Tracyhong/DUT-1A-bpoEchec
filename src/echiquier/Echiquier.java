package echiquier;
import java.util.ArrayList;
import jeu.Joueur;
import piece.*;

public class Echiquier {
	public static final int TAILLE_ECHIQUIER = 8;
	//attributs
	private static int nbLig=TAILLE_ECHIQUIER;
	private static int nbCol=TAILLE_ECHIQUIER;
	private Case [][] grille;
	
	// Constructeur plateau
	public Echiquier() {
		grille = new Case[nbLig][nbCol];
		for(int x=0; x<nbLig; x++) {
			for(int y=0; y<nbCol; y++) {
				grille[x][y] = new Case();
			}
		}
		miseEnPlace();
	}
	
	// COPIER ECHIQUIER A PARTIR D'UNE AUTRE
	public Echiquier(Echiquier e) {
		ArrayList<IPiece> pBlanc=e.getPiecesJoueur("blanc");
		ArrayList<IPiece> pNoir=e.getPiecesJoueur("noir");
		
		grille = new Case[nbLig][nbCol];
		for(int x=0; x<nbLig; x++) {
			for(int y=0; y<nbCol; y++) {
				grille[x][y] = new Case();
			}
		}
		
		for(IPiece p:pBlanc) {
			Coord c=new Coord(p.getCoord());
			if(p.getFamille()=="roi")
				placer(c.x,c.y,new Roi(c.x,c.y,'R',"blanc",this));
			if(p.getFamille()=="tour")
				placer(c.x,c.y,new Tour(c.x,c.y,'T',"blanc",this));
				
		}
		for(IPiece p:pNoir) {
			Coord c=new Coord(p.getCoord());
			if(p.getFamille()=="roi")
				placer(c.x,c.y,new Roi(c.x,c.y,'r',"noir",this));
			if(p.getFamille()=="tour")
				placer(c.x,c.y,new Tour(c.x,c.y,'t',"noir",this));
		}
	}
	/**
	 * place les pieces sur l'echiquier
	 */
	private void miseEnPlace() {
		placer(0,4,new Roi(0,4,'r',"noir",this));
		placer(2,4,new Roi(2,4,'R',"blanc",this));
		placer(1,1,new Tour(1,1,'T',"blanc",this));
	}
	/**
	 * affiche l'echiquier
	 */
	public void afficher() {
		StringBuilder echiquier=new StringBuilder();
		echiquier.append("    a   b   c   d   e   f   g   h \n");
		for(int ligne=0, num=8; ligne<nbLig;ligne++,num--) {
			echiquier.append("   --- --- --- --- --- --- --- ---\n");
			echiquier.append(num+" ");
			for(int col=0; col<nbCol; col++) {
				echiquier.append("|"+grille[ligne][col]);
			}
			echiquier.append("| "+num);
			echiquier.append("\n");
			
			
		}
		echiquier.append("   --- --- --- --- --- --- --- ---\n");
		echiquier.append("    a   b   c   d   e   f   g   h \n");
		System.out.println(echiquier.toString());
	}		
	/**
	 * place les pieces sur l'echiquier
	 * @param lig
	 * @param col
	 * @param piece
	 */
	public void placer (int lig, int col, IPiece piece) {
        
        //On test si on se trouve bien sur la grille
        if(lig<0 || col<0 || lig > nbLig || col> nbCol ) {
            
            System.out.println("Erreur!");
            return;
        }
         
        if(grille[lig][col].estVide()) {
            grille[lig][col].setPiece(piece);
        }
        
        else {
            System.out.println("Erreur, cette zone n'est pas vide !");
        }
	}
    //----------------------------------------------
	//getters et setters
	public IPiece getPiece(Coord coord) {
		return grille[coord.getX()][coord.getY()].getPiece();
	}
	public boolean caseVide(Coord coord) {
		return grille[coord.getX()][coord.getY()].estVide();
	}
	public void setPiece(Coord coord, IPiece p) {
		grille[coord.getX()][coord.getY()].setPiece(p);
	}
	public void viderCase(Coord coord) {
		grille[coord.getX()][coord.getY()].vider();
	}
	public ArrayList<IPiece> getPiecesJoueur(String couleur){
    	ArrayList<IPiece> p = new ArrayList<IPiece>();
    	for(int i=0; i<grille.length;i++){
    		for(int j=0; j<grille.length;j++){
    			if(!this.grille[i][j].estVide()){
    				if(this.grille[i][j].getPiece().getCouleur().equals(couleur))
    					p.add(this.grille[i][j].getPiece());
    			}
    		}
    	}
    	return p;
    }
	//--------------------------------------------------
	/**
	 * Cherche le roi du joueur dans l'echiquier
	 * @param Jcourant
	 * @return la coord du roi
	 */
	public Coord rechercheRoi(Joueur Jcourant) {
		ArrayList<IPiece>pieces=getPiecesJoueur(Jcourant.getCouleur());
		for(IPiece piece:pieces) {
			if(piece.getFamille().equals("roi"))
				return piece.getCoord();
		}
		return null; 
	}
	/**
	 * verifie si la coord est dans l'echiquier
	 * @param coord
	 * @return true ou false
	 */
	public boolean dansEchiquier(Coord coord) {
		if(coord.getX()<0||coord.getX()>=nbLig||coord.getY()<0||coord.getY()>=nbCol)
			return false;
		else return true;
	}
}

