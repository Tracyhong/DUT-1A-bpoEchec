package echiquier;

import java.util.ArrayList;

import jeu.Joueur;

public interface IPiece {
	
	boolean coupPossible(Coord coord);	
	boolean verifCheminLibre(Coord coord);
	boolean verifCaseLibre(Coord coord);
	boolean verifMouv(Joueur Jcourant,Coord coord);
	boolean verifManger(Joueur Jcourant, Coord coord);
	boolean verifCaseOccupeAdverse(Joueur Jcourant, Coord coord);

	void manger(Echiquier echiquier,Coord[] coord);
	void deplacer(Echiquier echiquier,Coord[] coord);
	void coup(Joueur Jcourant,Coord[] coord,Echiquier echiquier);
	ArrayList<Coord> casesVersRoi(Coord coordRoi);
	ArrayList<Coord> casesPossible();
	boolean verifCoup(Joueur Jcourant,Joueur Jadverse, Coord[] coord,Echiquier echiquier);
	String getCouleur();
	Coord getCoord();
	String getFamille();
	boolean estEchec(Joueur Jcourant,Joueur Jadverse);
	boolean estEchecEtMat(Joueur Jcourant,Joueur Jadverse);
}