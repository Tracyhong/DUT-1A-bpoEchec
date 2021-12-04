package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import echiquier.Coord;
import jeu.Partie;
import piece.*;

class TestPartie {

	@Test
	void testEchecEtMat() { // test sur le roi noir
		Partie partie=new Partie();
		partie.inverserJoueur();
		assertFalse(partie.getEchiquier().getPiece(partie.getEchiquier().rechercheRoi(partie.getJcourant())).estEchec(partie.getJcourant(),partie.getJadverse())); 
		//partie.afficheEchiquier();
		partie.inverserJoueur();
		char[] saisieTour=new char[] {'b','7','b','8'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        partie.recupSaisie(saisieCoordTour[0]).coup(partie.getJcourant(),saisieCoordTour,partie.getEchiquier());
        partie.inverserJoueur();
        //partie.afficheEchiquier();
        assertTrue(partie.getEchiquier().getPiece(partie.getEchiquier().rechercheRoi(partie.getJcourant())).estEchec(partie.getJcourant(),partie.getJadverse()));//NON si verifCoup dans estEchec et OUI si coupPossible et verifChemin libre
        assertTrue(partie.getEchiquier().getPiece(partie.getEchiquier().rechercheRoi(partie.getJcourant())).estEchecEtMat(partie.getJcourant(),partie.getJadverse()));
        partie.inverserJoueur();
        //partie.afficheEchiquier();
		char[] saisieRoi=new char[] {'e','6','e','5'};
        int[] saisieIntRoi=partie.charToInt(saisieRoi);
        Coord[] saisieCoordRoi=partie.convertirEnCoord(saisieIntRoi);
        partie.recupSaisie(saisieCoordRoi[0]).coup(partie.getJcourant(),saisieCoordRoi,partie.getEchiquier());
        partie.inverserJoueur();
        //partie.afficheEchiquier();
        assertFalse(partie.getEchiquier().getPiece(partie.getEchiquier().rechercheRoi(partie.getJcourant())).estEchecEtMat(partie.getJcourant(),partie.getJadverse()));
	}   
	
	@Test
	void testPat() {
		Partie partie = new Partie();
		assertFalse(partie.estPat());
		//partie.afficheEchiquier();
		//enlever toutes les pieces pour les replacer
		partie.getEchiquier().viderCase(partie.getEchiquier().rechercheRoi(partie.getJcourant()));
		partie.getEchiquier().viderCase(partie.getEchiquier().rechercheRoi(partie.getJadverse()));
		Coord coordTour=new Coord(1,1);
		partie.getEchiquier().viderCase(coordTour);
		//partie.afficheEchiquier();
		partie.getEchiquier().placer(7, 7, new Roi(7,7,'r',"noir",partie.getEchiquier()));
		partie.getEchiquier().placer(6,5, new Roi(6,5,'R',"blanc",partie.getEchiquier()));
		partie.getEchiquier().placer(6, 6, new Tour(6,6,'T',"blanc",partie.getEchiquier()));
		//partie.afficheEchiquier();
		partie.inverserJoueur();
		assertTrue(partie.estPat());
	}
	@Test
	void testFinPartie() {
		Partie partie=new Partie();
		assertFalse(partie.finPartie());
		//partie.afficheEchiquier();
		//ECHEC ET MAT pour le noir
		char[] saisieTour=new char[] {'b','7','b','8'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        partie.recupSaisie(saisieCoordTour[0]).coup(partie.getJcourant(),saisieCoordTour,partie.getEchiquier());
        partie.inverserJoueur();
        partie.afficheEchiquier();
        assertTrue(partie.finPartie());
        
        //PAT
        partie.getEchiquier().viderCase(partie.getEchiquier().rechercheRoi(partie.getJcourant()));
		partie.getEchiquier().viderCase(partie.getEchiquier().rechercheRoi(partie.getJadverse()));
		Coord coordTour=new Coord(0,1);
		partie.getEchiquier().viderCase(coordTour);
		partie.afficheEchiquier();
		partie.getEchiquier().placer(7, 7, new Roi(7,7,'r',"noir",partie.getEchiquier()));
		partie.getEchiquier().placer(6,5, new Roi(6,5,'R',"blanc",partie.getEchiquier()));
		partie.getEchiquier().placer(6, 6, new Tour(6,6,'T',"blanc",partie.getEchiquier()));
		partie.afficheEchiquier();
		assertTrue(partie.finPartie());
		partie.inverserJoueur();
		assertFalse(partie.finPartie());
		
	}
	
}
