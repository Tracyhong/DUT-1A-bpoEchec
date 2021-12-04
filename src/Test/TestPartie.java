package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import appli.Application;
import jeu.Joueur;
import jeu.Partie;

public class TestPartie {

	@Test
	public void testSaisieSansPile() {
		String[] saisie= new String[]{"1a","2b","3c","4d"};
		int[] saisie1=new int[] {1,2,3,4};
		int[] saisieSansPile=Partie.saisieSansPile(saisie);
		assertEquals(saisieSansPile,saisie1);
	}
	

}
