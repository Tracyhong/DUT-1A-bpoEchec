package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import echiquier.Coord;
import jeu.Partie;

class TestSaisie {
	public static final int TAILLE_ECHIQUIER = 8;
	@Test
	void testSaisie() {
		Partie partie=new Partie();
		char[] saisie=new char[] {'a','1','a','2'};  
        int[] saisieInt=partie.charToInt(saisie);
        int[] testSaisieInt=new int[] {1,8,1,7};
        assertEquals(saisieInt[0],testSaisieInt[0]);
        assertEquals(saisieInt[1],testSaisieInt[1]);
        assertEquals(saisieInt[2],testSaisieInt[2]);
        assertEquals(saisieInt[3],testSaisieInt[3]);
        
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        Coord[] testSaisieCoord= new Coord[2];
        testSaisieCoord[0]=new Coord(7,0);
        testSaisieCoord[1]=new Coord(6,0);
        assertEquals(saisieCoord[0].getX(),testSaisieCoord[0].getX());
        assertEquals(saisieCoord[0].getY(),testSaisieCoord[0].getY());
        assertEquals(saisieCoord[1].getX(),testSaisieCoord[1].getX());
        assertEquals(saisieCoord[1].getY(),testSaisieCoord[1].getY());
	}
 
	@Test
	void testBonFormat() {
		Partie partie=new Partie();
		char[] saisie=new char[] {'a','1','a','2'};
		assertTrue(partie.bonFormat(saisie));
		char[] saisie1=new char[] {'1','a','a','2'};
		assertFalse(partie.bonFormat(saisie1));
		char[] saisie2=new char[] {'r','6','a','2'};
		assertFalse(partie.bonFormat(saisie2));
	}
	
	void testVerifPieceJouee(char c1, char c2, char c3, char c4,char c11, char c22, char c33, char c44) {
		Partie partie=new Partie();
		
		char[] saisie=new char[] {c1,c2,c3,c4};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertTrue(partie.verifPieceJouee(partie.getJcourant(), saisieCoord[0]));
        
        char[] saisie1=new char[] {c11,c22,c33,c44};
        int[] saisieInt1=partie.charToInt(saisie1);
        Coord[] saisieCoord1=partie.convertirEnCoord(saisieInt1);
        assertFalse(partie.verifPieceJouee(partie.getJcourant(), saisieCoord1[0])); 
	}
	@Test
	void testRecupSaisie() {
		Partie partie=new Partie();
		char[] saisie=new char[] {'a','1','a','2'};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertEquals(partie.recupSaisie(saisieCoord[0]),partie.getEchiquier().getPiece(saisieCoord[0]));
	}
	@Test
	void testInverserJoueur() {
		Partie partie=new Partie();
		assertTrue(partie.getJcourantCouleur()=="blanc");
		partie.inverserJoueur();
		assertFalse(partie.getJcourantCouleur()=="blanc");
		assertTrue(partie.getJcourantCouleur()=="noir");
		assertTrue(partie.getJadverseCouleur()=="blanc");
		
		//testVerifPieceJouee
		char[] saisie=new char[] {'e','8','e','7'};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertTrue(partie.verifPieceJouee(partie.getJcourant(), saisieCoord[0]));
        
        partie.inverserJoueur();
        testVerifPieceJouee('b','7','b','6','b','1','b','2');
	}
	@Test
	void testVerifSaisie() {
		Partie partie=new Partie();
		partie.afficheEchiquier();
		char[] saisie=new char[] {'b','7','b','8'};
        int[] saisieInt=partie.charToInt(saisie);
        Coord[] saisieCoord=partie.convertirEnCoord(saisieInt);
        assertTrue(partie.verifSaisie(saisie,saisieCoord)); // A RETESTER APRES PB SIMULATION 
	}  
	
}
