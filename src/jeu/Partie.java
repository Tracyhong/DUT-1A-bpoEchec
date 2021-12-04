package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import echiquier.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;


public class Partie {
	private Echiquier echiquier;
	private Joueur jBlanc;
	private Joueur jNoir;
	
	private Joueur Jcourant;
	private Joueur Jadverse;
	/**
	 * Constructeur partie
	 */
	public Partie() {
		echiquier=new Echiquier();
		jBlanc=new Joueur("blanc");
		jNoir=new Joueur("noir");
		Jcourant=jBlanc;
		Jadverse=jNoir;
		
	}
	/**
	 * Affiche l'echiquier
	 */
	public void afficheEchiquier() {
		echiquier.afficher();
	}
	//--------------------------------------------------------------
	//Tous les getters 
    public Echiquier getEchiquier() {
    	return echiquier;
    }
    public Joueur getJcourant() {
    	return Jcourant;
    }
    public Joueur getJadverse() {
    	return Jadverse;
    }
    public String getJcourantCouleur() {
    	return this.Jcourant.getCouleur();
    }
    public String getJadverseCouleur() {
    	return this.Jadverse.getCouleur();
    }
    //--------------------------------------------------------------
    //POUR SAISIE
    /**
     * Permet de convertir la saisie en int
     * @param saisieChar tableau de char
     * @return tableau de int
     */
    public int[] charToInt (char[] saisieChar) {
	    
	    String lettres="abcdefgh"; 
	    String chiffres="87654321";//a l'envers car l'echiquier a l'envers 
	    int[] tab=new int[4];
	   
    	for(int i=0;i<4;i++) {
	    	if (Character.isAlphabetic(saisieChar[i]))
	    		tab[i]=lettres.indexOf(saisieChar[i])+1;//y
	    	else if(Character.isDigit(saisieChar[i]))
	    		tab[i]=chiffres.indexOf(saisieChar[i])+1;
	    		//tab[i]=Character.getNumericValue(saisieChar[i]);//x
	    	//[lettre,chiffre,lettre,chiffre]
    		//[y,x,y,x] (a l'envers)
    	}
    	
	    return tab;
    }
    //convertir en coord
    /**
     * Permet de convertir la saisie en Coord
     * @param saisieInt : tableau de int
     * @return tableau de Coord
     */
    public Coord[] convertirEnCoord(int[] saisieInt) {
    	Coord[] coord=new Coord[2]; //2 pour les 2 coord de la saisie
    	
    	//-1 pour bien correspondre aux indices du tableau a 2 dimension de l'echiquier 
    	coord[0]=new Coord(saisieInt[1]-1,saisieInt[0]-1); 
    	coord[1]=new Coord(saisieInt[3]-1,saisieInt[2]-1);
    	return coord;
    }
    //-----------------------------------------------------------------
    // LA SAISIE 
    /**
    * Demande à saisir les coups 
    * @return tableau de char de la saisie
    */
    private char[] saisir() {
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	String s=sc.next();
		char[] saisie=new char[4];
		for(int i=0; i<4;i++) {
			saisie[i]= s.charAt(i);
		}
		return saisie;
    }
    /**
     * Effectue la saisie 
     * @return tableau de Coord
     */
    public Coord[] saisie() {
    	char[] saisie=saisir();
    	if(abandon(saisie)) {
    		System.out.println("le gagnant est "+Jadverse.getCouleur()+" !");
    		System.exit(0);}
    	if(nul(saisie)) {
    		System.out.println("MATCH NUL !");
    		System.exit(0);}
    	int[] saisieInt=charToInt(saisie);
    	Coord[] coord=convertirEnCoord(saisieInt);
    	while(!verifSaisie(saisie,coord)) {       
    		//signaler l'erreur
    		System.out.println("Mauvaise saisie !");
    		saisie=saisir();
    		saisieInt=charToInt(saisie);
    		coord=convertirEnCoord(saisieInt); 
    	}
    	return coord;
    }  
    
    //verifSaisie
    /**
     * Verifie la saisie
     * @param saisie : tableau de char de la saisie
     * @param coord : tableau de Coord de la saisie
     * @return true si la saisie est correcte, sinon false
     */
    public boolean verifSaisie(char[] saisie, Coord[]coord) {
    	if(bonFormat(saisie)) {
    		if(verifPieceJouee(Jcourant,coord[0]))
    			if(recupSaisie(coord[0]).verifCoup(Jcourant,Jadverse,coord,this.echiquier))
    				return true;
    	}
    	return false;
    } 
    //------------------------------------------
    //POUR VERIF SAISIE
    /**
     * Verifie si la saisie a le bon format
     * @param saisie : tableau de char de la saisie
     * @return true si c'est le bon format sinon false
     */
    public boolean bonFormat(char[] saisie) {
		if(Character.isAlphabetic(saisie[0])&&Character.isAlphabetic(saisie[2]) 
				&& Character.isDigit(saisie[1])&&Character.isDigit(saisie[3])) {
			if(Character.isLowerCase(saisie[0])&&Character.isLowerCase(saisie[2])) {
				if(dansEchiquier(saisie))
					return true;
			}
		}
    	return false;
    }
    /**
     * Verifie si la saisie est bien dans l'echiquier
     * @param saisie tableau de char
     * @return true ou false
     */
    private boolean dansEchiquier(char[] saisie) {
    	
    	if(saisie[0]>='a'&&saisie[0]<='h'&&saisie[1]>'0'&&saisie[1]<='8'/*Appli.TAILLE_ECHIQUIER*/
    			&&saisie[2]>='a'&&saisie[2]<='h'&saisie[3]>'0'&&saisie[3]<='8'/*Appli.TAILLE_ECHIQUIER*/)
      		return true;
    	return false;
    }
    /**
     * Verifie si la piece qu'on veut jouer existe bien à la coord donnée
     * @param Jcourant
     * @param coord 
     * @return true ou false
     */
    public boolean verifPieceJouee(Joueur Jcourant, Coord coord) {
    	if((!echiquier.caseVide(coord))&&recupSaisie(coord).getCouleur().equals(Jcourant.getCouleur()))
    		return true;
    	else return false; 
    } 
    /**
     * identifier la piece à jouer
     * @param coord
     * @return la piece 
     */
    public IPiece recupSaisie(Coord coord) {
    	return echiquier.getPiece(coord);
    }
    
    //-------------------------------------------
    /**
     * Inverse à chaque tour les joueurs
     */
    public void inverserJoueur() {
    	if (Jcourant==jBlanc) {
    		Jcourant=jNoir;
    		Jadverse=jBlanc;
    	}
    	else {
    		Jcourant=jBlanc;
    		Jadverse=jNoir;
    	}	
    }
    
    /**
     * Verifie si le jeu est pat c'est à dire : 
     *    le roi n'est pas en echec
     *    il ne peut pas se déplacer sans se mettre en echec
     *    ses pieces ne peuvent pas bouger sans mettre le roi en danger
     * @return true ou false
     */
    public boolean estPat() {
    	//roi pas en echec
    	if(!this.echiquier.getPiece(this.echiquier.rechercheRoi(Jcourant)).estEchec(Jcourant,Jadverse)) {
			//roi ne peut pas se deplacer
			ArrayList<Coord> casesPossibleRoi=this.echiquier.getPiece(this.echiquier.rechercheRoi(Jcourant)).casesPossible();
			Coord[] coordCoup=new Coord[2];
			coordCoup[0]=this.echiquier.rechercheRoi(Jcourant);
			for(Coord coord:casesPossibleRoi) {
				coordCoup[1]=coord;
				if(this.echiquier.getPiece(this.echiquier.rechercheRoi(Jcourant)).verifCoup(Jcourant,Jadverse, coordCoup,this.echiquier))
					return false;
			}
			//le joueur ne peut pas deplacer ses pieces
			ArrayList<IPiece> pieces=this.echiquier.getPiecesJoueur(getJcourantCouleur());
			//enlever le roi
			pieces.remove(this.echiquier.getPiece(this.echiquier.rechercheRoi(Jcourant)));
			Coord[] coord=new Coord[2];
			for(IPiece piece:pieces) {
				ArrayList<Coord> casesPossible=piece.casesPossible();
				coord[0]=new Coord(piece.getCoord());
				for(Coord c:casesPossible) {
					coord[1]=new Coord(c); 
					if(piece.verifCoup(Jcourant, Jadverse,coord, this.echiquier))
						return false;
				} 
			}
			return true;
    	}
		else return false;
		
	}
    /**
     * Permet d'abandonner la partie si le joueur saisit "stop"
     * @param saisie
     * @return true ou false
     */
    public boolean abandon(char[]saisie) {
    	char[] stop= {'s','t','o','p'};
    	return Arrays.equals(stop,saisie);
    }
    /**
     * Permet de declarer la proposition de partie nulle si le joueur saisit "null"
     * @param saisie
     * @return true ou false
     */
    public boolean nul(char[]saisie) {
    	char[] nul= {'n','u','l','l'};
    	return Arrays.equals(nul,saisie);
    }
    /**
     * Verifie si la partie est fini : par echec et mat ou pat
     * @return true ou false
     */
    public boolean finPartie() {
    	//echec et mat
    	if(this.echiquier.getPiece(this.echiquier.rechercheRoi(Jcourant)).estEchecEtMat(Jcourant,Jadverse)) {
    		System.out.println("Le gagnant est "+Jadverse.getCouleur()+" ! Félicitation");	
			return true;}
    	//pat
		else if(estPat()) {
    		System.out.println("PAT ! Partie Nulle.");
    		return true;}
    	
    	return false;
    }
}

