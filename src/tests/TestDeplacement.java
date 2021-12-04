package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import echiquier.Coord;
import echiquier.IPiece;
import jeu.Partie;
import piece.Tour;

class TestDeplacement {
	public static final int TAILLE_ECHIQUIER = 8;
	@Test
	void testVerifCaseLibre() {
		Partie partie=new Partie();
		
		//partie.afficheEchiquier();
		char[] saisie=new char[] {'b','7','b','8'};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertTrue(partie.recupSaisie(saisieCoord[0]).verifCaseLibre(saisieCoord[1]));
        partie.getEchiquier().placer(0,1, new Tour(0,1,'t',"noir",partie.getEchiquier()));
        //partie.afficheEchiquier();
        assertFalse(partie.recupSaisie(saisieCoord[0]).verifCaseLibre(saisieCoord[1]));
	}
	@Test
	void testVerifCheminLibre() {
		Partie partie=new Partie();
		
		//pour la tour blanche
		char[] saisie=new char[] {'b','7','b','8'};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertTrue(partie.recupSaisie(saisieCoord[0]).verifCheminLibre(saisieCoord[1]));
        
        Coord[] coord=new Coord[2];
        coord[0]=new Coord(1,1);
        coord[1]=new Coord(2,1);
        partie.getEchiquier().getPiece(coord[0]).deplacer(partie.getEchiquier(), coord);
        char[] saisie1=new char[] {'b','6','f','6'};
        int[] saisieInt1=partie.charToInt(saisie1);
        Coord[] saisieCoord1=partie.convertirEnCoord(saisieInt1);
        assertFalse(partie.recupSaisie(saisieCoord1[0]).verifCheminLibre(saisieCoord1[1]));
        //pour le roi blanc
      	char[] saisie2=new char[] {'e','6','d','6'};
        int[] saisieInt2=partie.charToInt(saisie2);
        Coord[] saisieCoord2=partie.convertirEnCoord(saisieInt2);
        assertTrue(partie.recupSaisie(saisieCoord2[0]).verifCheminLibre(saisieCoord2[1]));
        partie.getEchiquier().placer(2,3, new Tour(2,3,'t',"noir",partie.getEchiquier()));
        //partie.afficheEchiquier();
        
        assertFalse(partie.recupSaisie(saisieCoord2[0]).verifCheminLibre(saisieCoord2[1]));
	}
	@Test
	void testCoupPossible() {
		Partie partie=new Partie();
		//TOUR
		char[] saisieTour=new char[] {'b','7','b','1'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        assertTrue(partie.recupSaisie(saisieCoordTour[0]).coupPossible(saisieCoordTour[1]));
        char[] saisieTour1=new char[] {'b','7','a','8'};
        int[] saisieIntTour1=partie.charToInt(saisieTour1);
        Coord[] saisieCoordTour1=partie.convertirEnCoord(saisieIntTour1);
        assertFalse(partie.recupSaisie(saisieCoordTour1[0]).coupPossible(saisieCoordTour1[1]));
        char[] saisieTour2=new char[] {'b','7','a','3'};
        int[] saisieIntTour2=partie.charToInt(saisieTour2);
        Coord[] saisieCoordTour2=partie.convertirEnCoord(saisieIntTour2);
        assertFalse(partie.recupSaisie(saisieCoordTour2[0]).coupPossible(saisieCoordTour2[1]));
        
        //ROI
        char[] saisieRoi=new char[] {'e','6','d','7'};
        int[] saisieIntRoi=partie.charToInt(saisieRoi);
        Coord[] sasisieCoordRoi=partie.convertirEnCoord(saisieIntRoi);
        assertTrue(partie.recupSaisie(sasisieCoordRoi[0]).coupPossible(sasisieCoordRoi[1]));
        char[] saisieRoi1=new char[] {'e','6','e','3'};
        int[] saisieIntRoi1=partie.charToInt(saisieRoi1);
        Coord[] saisieCoordRoi1=partie.convertirEnCoord(saisieIntRoi1);
        assertFalse(partie.recupSaisie(saisieCoordRoi1[0]).coupPossible(saisieCoordRoi1[1]));
        char[] saisieRoi2=new char[] {'e','6','c','5'};
        int[] saisieIntRoi2=partie.charToInt(saisieRoi2);
        Coord[] saisieCoordRoi2=partie.convertirEnCoord(saisieIntRoi2);
        assertFalse(partie.recupSaisie(saisieCoordRoi2[0]).coupPossible(saisieCoordRoi2[1]));
	} 
	@Test
	void testVerifMouv() { 
		Partie partie=new Partie();
		//partie.afficheEchiquier();
		char[] saisieTour=new char[] {'b','7','b','2'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        assertTrue(partie.recupSaisie(saisieCoordTour[0]).verifMouv(partie.getJcourant(),saisieCoordTour[1]));
        Coord[] coord=new Coord[2];
        coord[0]=new Coord(1,1);
        coord[1]=new Coord(2,1);
        partie.getEchiquier().getPiece(coord[0]).deplacer(partie.getEchiquier(), coord);
        char[] saisieTour1=new char[] {'b','6','g','6'};
        int[] saisieIntTour1=partie.charToInt(saisieTour1);
        Coord[] saisieCoordTour1=partie.convertirEnCoord(saisieIntTour1);
        assertFalse(partie.recupSaisie(saisieCoordTour1[0]).verifMouv(partie.getJcourant(),saisieCoordTour1[1]));
	}
	@Test
	void testVerifCaseOccupAdverse() {
		Partie partie=new Partie();
		partie.getEchiquier().placer(0,1, new Tour(0,1,'t',"noir",partie.getEchiquier()));
		//partie.afficheEchiquier();
		char[] saisieTour=new char[] {'b','7','b','8'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        assertTrue(partie.recupSaisie(saisieCoordTour[0]).verifCaseOccupeAdverse(partie.getJcourant(), saisieCoordTour[1]));
	}
	@Test
	void testVerifManger() {
		Partie partie=new Partie();
		partie.getEchiquier().placer(0,1, new Tour(0,1,'t',"noir",partie.getEchiquier()));
		//partie.afficheEchiquier();
		char[] saisieTour=new char[] {'b','7','b','8'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        assertTrue(partie.recupSaisie(saisieCoordTour[0]).verifManger(partie.getJcourant(),saisieCoordTour[1]));
	}
	
	
	
	
	 
	
	@Test
	void testVerifCoup() {
		Partie partie=new Partie();
		partie.getEchiquier().placer(1,7, new Tour(1,7,'t',"noir",partie.getEchiquier()));
		partie.afficheEchiquier();
		//CAS 1 : verifMouv
		char[] saisieTour=new char[] {'b','7','g','7'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        assertTrue(partie.recupSaisie(saisieCoordTour[0]).verifCoup(partie.getJcourant(),partie.getJadverse(),saisieCoordTour,partie.getEchiquier()));
         //PB COPIER ECHIQUIER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        //CAS 2 : verifManger
        partie.afficheEchiquier();
		char[] saisieTour1=new char[] {'b','7','h','7'};
        int[] saisieIntTour1=partie.charToInt(saisieTour1);
        Coord[] saisieCoordTour1=partie.convertirEnCoord(saisieIntTour1);
        assertTrue(partie.recupSaisie(saisieCoordTour1[0]).verifCoup(partie.getJcourant(),partie.getJadverse(),saisieCoordTour1,partie.getEchiquier()));
	}
	@Test
	void testDeplacer() {
		Partie partie=new Partie();
		//partie.afficheEchiquier();
		char[] saisieTour=new char[] {'b','7','b','6'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        partie.recupSaisie(saisieCoordTour[0]).deplacer(partie.getEchiquier(),saisieCoordTour);
		//partie.afficheEchiquier();
		assertEquals(partie.recupSaisie(saisieCoordTour[1]),partie.getEchiquier().getPiece(saisieCoordTour[1]));
		assertTrue(partie.getEchiquier().caseVide(saisieCoordTour[0]));
	}
	@Test
	void testManger() {
		Partie partie=new Partie();
		IPiece t=new Tour(0,1,'t',"noir",partie.getEchiquier());
		partie.getEchiquier().placer(0,1,t );
		//partie.afficheEchiquier();
		char[] saisieTour=new char[] {'b','7','b','8'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        partie.recupSaisie(saisieCoordTour[0]).manger(partie.getEchiquier(),saisieCoordTour);
		//partie.afficheEchiquier();
		assertEquals(partie.recupSaisie(saisieCoordTour[1]),partie.getEchiquier().getPiece(saisieCoordTour[1]));
		assertTrue(partie.getEchiquier().caseVide(saisieCoordTour[0]));
		assertNotEquals(partie.recupSaisie(saisieCoordTour[1]),t);
	}
	@Test
	void testCoup() {
		Partie partie=new Partie();
		//partie.afficheEchiquier();
		//DEPLACER
		char[] saisieTour=new char[] {'b','7','g','7'};
        int[] saisieIntTour=partie.charToInt(saisieTour);
        Coord[] saisieCoordTour=partie.convertirEnCoord(saisieIntTour);
        partie.recupSaisie(saisieCoordTour[0]).coup(partie.getJcourant(),saisieCoordTour,partie.getEchiquier());
		//partie.afficheEchiquier();
		assertEquals(partie.recupSaisie(saisieCoordTour[1]),partie.getEchiquier().getPiece(saisieCoordTour[1]));
		assertTrue(partie.getEchiquier().caseVide(saisieCoordTour[0]));
		//MANGER 
		IPiece t=new Tour(1,7,'t',"noir",partie.getEchiquier());
		partie.getEchiquier().placer(1,7,t );
		//partie.afficheEchiquier();
		char[] saisieTour1=new char[] {'g','7','h','7'};
        int[] saisieIntTour1=partie.charToInt(saisieTour1);
        Coord[] saisieCoordTour1=partie.convertirEnCoord(saisieIntTour1);
        partie.recupSaisie(saisieCoordTour1[0]).coup(partie.getJcourant(),saisieCoordTour1,partie.getEchiquier());
		//partie.afficheEchiquier();
		assertEquals(partie.recupSaisie(saisieCoordTour[1]),partie.getEchiquier().getPiece(saisieCoordTour[1]));
		assertTrue(partie.getEchiquier().caseVide(saisieCoordTour[0]));
		assertNotEquals(partie.recupSaisie(saisieCoordTour[1]),t);
	} 
	
} 
