/**
 * 
 */
package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import jeu.Joueur;

/**
 * @author tracy
 *
 */
public class TestJoueur {

	@Test
	public void test() {
		Joueur NORD=new Joueur();
		Joueur SUD=new Joueur();
		assertTrue(NORD.getCarteEnMain_size()==6);
		assertTrue(SUD.getCarteEnMain_size()==6);
		
		assertTrue(NORD.getPioche_size()==58);
		assertTrue(SUD.getPioche_size()==58);
	}
	
	public void testSetter() {
		Joueur J=new Joueur();
		
		J.setPileAsc(4);
		assertTrue(J.getPileAsc()==4);
		J.setPileDesc(28);
		assertTrue(J.getPileDesc()==28);
	}
	
	public void testPioche() {
		Joueur J=new Joueur();
		int carteEnMain=J.getCarteEnMain_size();
		J.piocher();
		assertTrue(J.getCarteEnMain_size()>carteEnMain);
	}
	public void testRetirerCarte() {
		Joueur J=new Joueur();
		int carteEnMain=J.getCarteDansMain(0);
		J.retirerCarte(0);
		assertTrue(J.getCarteDansMain(0)!=carteEnMain);
	}
}
