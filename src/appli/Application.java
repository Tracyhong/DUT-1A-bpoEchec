/**
 * @sujet : Projet BPO : java. Jeu "The GAME - Le Duel"
 * @author : Tracy HONG et Nahean BADAR
 * @date : Février - Mars 2021
 */

package appli;




import jeu.Joueur;
import jeu.Partie;

public class Application {
	//LES AFFICHAGES  ----------------------------------------------------------------------------------------------------------------
	
	/**
	 * Affiche le plateau : les bases de chaque joueur et les cartes en main du joueur courant
	 * @param NORD
	 * @param SUD
	 * @param Jcourant
	 */
	private static void afficherPlateau(Joueur NORD, Joueur SUD, Joueur Jcourant) {
		
		System.out.println("NORD ^[" + (String.format("%02d", NORD.getPileAsc()) + "] v["
				+ (String.format("%02d", NORD.getPileDesc()) 
				+"] (m" + NORD.getCarteEnMain_size()) 
				+ "p" + NORD.getPioche_size() + ")"));
		System.out.println("SUD  ^[" + (String.format("%02d", SUD.getPileAsc()) + "] v["
				+ (String.format("%02d", SUD.getPileDesc()) 
				+ "] (m" + SUD.getCarteEnMain_size()) 
				+ "p" + SUD.getPioche_size() + ")"));
		
		if(Jcourant==NORD) {
			System.out.print("cartes NORD { ");
			for(int carte : Jcourant.getCarteEnMain())
				System.out.print(String.format("%02d",carte) + " ");
			System.out.println("}");
			//System.out.println("cartes NORD { "+Jcourant.CarteEnMain_toString().replace("[", "").replace("]", "").replace(",", "")+ " }");
		}
		if(Jcourant==SUD) {
			System.out.print("cartes SUD { ");
			for(int carte : Jcourant.getCarteEnMain())
				System.out.print(String.format("%02d",carte) + " ");
			System.out.println("}");			
			//System.out.println("cartes SUD { "+Jcourant.CarteEnMain_toString().replace("[", "").replace("]", "").replace(",", "")+ " }");
		}
	}
	/**
	 * affiche le gagnant
	 * @param NORD
	 * @param SUD
	 * @param Jcourant
	 * @param Jadverse
	 */
	private static void afficherGagnant(Joueur NORD, Joueur SUD, Joueur Jcourant, Joueur Jadverse) {
		String nomCourant,nomAdverse;
		if(Jcourant==NORD) {
			nomCourant="NORD";
			nomAdverse="SUD";
		}
		else {
			nomCourant="SUD";
			nomAdverse="NORD";
		}
		
		if(partieGagnee(Jcourant)) {
			afficherPlateau(NORD,SUD,Jcourant);
			System.out.println("partie finie, " + nomCourant + " a gagné");
			
		}
		else if(partiePerdue(Jcourant,Jadverse)) {
			afficherPlateau(NORD,SUD,Jcourant);
			System.out.println("partie finie, " + nomAdverse + " a gagné");
		}
	}
	
	
	/**
	 * affiche en fin de tour, le nombre de cartes posées et le nombre de cartes piochées du joueur courant
	 * @param Jcourant
	 * @param Jadverse
	 * @param saisieAvecPile
	 * @param saisieSansPile
	 */
	private static void afficherFinTour(Joueur Jcourant, Joueur Jadverse, String[] saisieAvecPile, int[] saisieSansPile) {
		int cartesPiochees=Partie.piocherFinTour(Jcourant, saisieAvecPile);
		int cartesPosees=Partie.poserCarte(Jcourant, Jadverse, saisieAvecPile, saisieSansPile);
		
		System.out.println(cartesPosees + " cartes posées, "+cartesPiochees+" cartes piochées");
	}
	
	// VERIFICATION DE FIN DE PARTIE ---------------------------------------------------------------------------------------------------
	/**
	 * vérifie si la partie est finie
	 * @param Jcourant
	 * @param Jadverse
	 * @return vrai ou faux
	 */
	private static boolean finPartie(Joueur Jcourant, Joueur Jadverse) {
		if(partieGagnee(Jcourant)||partiePerdue(Jcourant,Jadverse))
			return true;
		else return false;
	}
		
		

	/**
	 * le joueur courant a gagné s'il n'a plus de carte dans sa pioche et sa main
	 * @param Jcourant
	 * @return vrai ou faux en fonction du joueur courant
	 */
	private static boolean partieGagnee(Joueur Jcourant){
		if(Jcourant.piocheIsEmpty() && Jcourant.getCarteEnMain().isEmpty()) {
			return true;
		}
		
		return false;
	}
	/**
	 * le joueur courant a perdu s'il ne peux pas poser 2 cartes ou plus
	 * @param Jcourant
	 * @param Jadverse
	 * @return vrai ou faux
	 */
	private static boolean partiePerdue(Joueur Jcourant,Joueur Jadverse) {
		 int cpt=0;
		 if(Jcourant.getCarteEnMain_size()<2)
			 return true;
		 else {
			Joueur JtmpCourant=new Joueur(Jcourant);//joueur temporaire pour poser des cartes temporairement
			Joueur JtmpAdverse=new Joueur(Jadverse);
			

			for(int carte=0; carte<Jcourant.getCarteEnMain_size();carte++) {
				if(cpt>=2)
					return false;
				if(JtmpCourant.getCarteEnMain().get(carte)>JtmpCourant.getPileAsc() || JtmpCourant.getCarteEnMain().get(carte)==(JtmpCourant.getPileAsc()-10)) {
					JtmpCourant.setPileAsc(JtmpCourant.getCarteEnMain().get(carte));
					cpt++;
					continue;
				}
				
				if(JtmpCourant.getCarteEnMain().get(carte)<JtmpCourant.getPileDesc() || JtmpCourant.getCarteEnMain().get(carte)==(JtmpCourant.getPileDesc()+10)) {	
					JtmpCourant.setPileDesc(JtmpCourant.getCarteEnMain().get(carte));
					cpt++;
					continue;
				}
				if(JtmpCourant.getCarteEnMain().get(carte)<JtmpAdverse.getPileAsc() ) {
					JtmpCourant.setPileDesc(JtmpCourant.getCarteEnMain().get(carte));
					cpt++;
					continue;
				}
				if(JtmpCourant.getCarteEnMain().get(carte)>JtmpAdverse.getPileDesc()) {
					JtmpCourant.setPileDesc(JtmpCourant.getCarteEnMain().get(carte));
					cpt++;
					continue;
				}
			}
		} 
		if(cpt<2)
			return true;
		return false;
	 }



	//***********************************************************************************************************************
	public static void main(String[] args){
		
		
		Joueur NORD = new Joueur();
		Joueur SUD = new Joueur();
		
		Joueur Jcourant=NORD;
		Joueur Jadverse=SUD;
		
		String[] saisieAvecPile=new String[Joueur.MAX_MAIN];
		int[] saisieSansPile=new int[Joueur.MAX_MAIN];
		//reflechir a une autre solution car pb inverser joueur et verif fin partie
		//joueur courant = nord. si Nord gagne puis inverser joueur, sud continue a jouer or le jeu est censé etre deja fini
		
		do {
		afficherPlateau(NORD,SUD,Jcourant); 
		saisieAvecPile=Partie.saisie(Jcourant,Jadverse);
		
		saisieSansPile=Partie.saisieSansPile(saisieAvecPile);
		
		Partie.poserCarte(Jcourant,Jadverse,saisieAvecPile,saisieSansPile);
		afficherFinTour(Jcourant,Jadverse,saisieAvecPile,saisieSansPile);

		if(Jcourant==NORD) {
			Jcourant=SUD;
			Jadverse=NORD;
		}
		else if(Jcourant==SUD) {
			Jcourant=NORD;
			Jadverse=SUD;
		}
		}
		while(!finPartie(Jcourant,Jadverse));
		
		afficherGagnant(NORD,SUD,Jcourant,Jadverse);
	}
}
