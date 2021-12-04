package application;
import echiquier.Coord;
import jeu.Partie;

public class Appli { 
	/**
	 * Menu du jeu
	 */
	public static void menu() {
		StringBuilder sb=new StringBuilder();
		sb.append("   -----------------------------------------------------------------------------------------\n");
		sb.append("   | Dans cette version, le jeu simule la finale d'une partie d'�chec.                     |\n");
		sb.append("   | Le joueur blanc poss�de une tour et un roi et le joueur noir poss�de seulement le roi.|\n");
		sb.append("   | Le joueur blanc commance !                                                            |\n");
		//expliquer la saisie
		//que en minuscule
		sb.append("   | Pour saisir un coup, �crivez la case de d�part de la pi�ce puis la case d'arriv�e     |\n");
		sb.append("   | comme ceci : b1b2 (en minuscule)                                                      |\n");
		//expliquer l'abandon, la demande de nul
		sb.append("   | Pour abandonner, �crivez \"stop\"                                                       |\n");
		sb.append("   | Pour la proposition de nul, �crivez \"null\"                                            |\n");
		sb.append("   -----------------------------------------------------------------------------------------\n");
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		int cptT=1;
		menu();
		Partie partie=new Partie();
		do {
			System.out.println("______________\nRound "+cptT+"\n");
			cptT++;
			partie.afficheEchiquier();
			System.out.println("Joueur " + partie.getJcourantCouleur()+ " :");
			Coord[] coord=partie.saisie();
			partie.recupSaisie(coord[0]).coup(partie.getJcourant(), coord,partie.getEchiquier() );
			partie.inverserJoueur();
		}
		while(!partie.finPartie());
		
	}
}
