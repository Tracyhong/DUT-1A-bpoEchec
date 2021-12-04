/**
 * @sujet : Projet BPO : java. Jeu "The GAME - Le Duel"
 * @author : Tracy HONG et Nahean BADAR
 * @date : Février - Mars 2021
 */

package jeu;
import java.util.Arrays;
import java.util.Scanner;


public class Partie {

	//SAISIE
	/**
	 * demande la saisie et stocke dans tableau de string avec chaque coup par case
	 * @return tableau de string de la saisie 
	 */
	private static String[] saisir() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String saisie = sc.nextLine();

		return saisie.split(" ");
	}
	

	/**
	 * converti le tableau de saisie(saisieAvecPile) avec caractere pour les piles en tableau de int sans caractere (tab_saisieSansPile)
	 * @param saisieAvecPile
	 * @return tab_saisieSansPile
	 */
	public static int[] saisieSansPile(String[] saisie) {
		int coup;
		String[] saisieAvecPile=new String[saisie.length]; 
		
		for(coup=0;coup<saisieAvecPile.length;coup++) {
			//saisieAvecPile[coup]=saisieAvecPile[coup].replace("v", "").replace("^", "").replace("\'","");
			saisieAvecPile[coup]=saisie[coup].replaceAll("[^0-9]", "");
		}
		
		int[] saisieSansPile=new int[saisieAvecPile.length];
		for(coup=0;coup<saisieAvecPile.length;coup++) {
			saisieSansPile[coup]=Integer.parseInt(saisieAvecPile[coup]);
		}
		return saisieSansPile;
	}
	//SAISIE
	//**************************************************************
	/**
	 * demande et verifie la saisie
	 * @param Jcourant
	 * @param Jadverse
	 * @return saisieAvecPile : tableau de string de la saisie
	 */
	public static String[] saisie(Joueur Jcourant,Joueur Jadverse) {
		System.out.print("> ");
		String[] saisieAvecPile=saisir();
		
		int[] saisieSansPile=saisieSansPile(saisieAvecPile);
		
		while(!verifierSaisie(saisieAvecPile,saisieSansPile,Jcourant,Jadverse)) {
			System.out.print("#> ");

			saisieAvecPile=saisir();
			//tab_saisieAvecPile=saisieAvecPile(saisie);
			saisieSansPile=saisieSansPile(saisieAvecPile);
		}
		return saisieAvecPile;
	}
	
	
	
	
	//VERIFICATION SAISIE
	//*************************************************************************************************************
	/**
	 * verifie si chaque coup dans la saisie a le bon format : 2 chiffres avec v ou ^ ou v'ou ^' et une seule fois '
	 * @param saisie
	 * @return vrai ou faux
	 */
	private static boolean verifBonFormat(String[] saisie) {
		int cptSaisieAdverse=0; //si cpt = 2 alors le joueur veut poser une carte 2fois sur la pile adverse
		int cptValide=0;//cpt si le coup est valide
		
		for(String coup : saisie) {


			if(coup.length()==3 || coup.length()==4) {
				
				if(Character.isDigit(coup.charAt(0))&&Character.isDigit(coup.charAt(1))) {
					
					if(coup.charAt(2)==118||coup.charAt(2)==94) {
						
						if(coup.length()==4 && coup.charAt(3)==39)
							cptSaisieAdverse++;
						cptValide++;
					}
				}
			}
			if(cptSaisieAdverse>=2) {				
				return false;
			}
		}
		
		if (cptValide==saisie.length)
			return true;
		else return false;
	}
	/**
	 * verifie s'il n'y a pas de doublon dans la saisie
	 * @param saisie
	 * @return vrai ou faux
	 */
	private static boolean verifPasDoublon(int[] saisie) {
		int carteTmp=0;
		for(int i=0;i<saisie.length;i++) { 
			carteTmp=saisie[i];
			for(int j=i+1;j<saisie.length;j++) {
				if(carteTmp==saisie[j])
					return false;
			}
		}
		return true;
	}
	/**
	 * verifie si la saisie est croissante
	 * @param saisie
	 * @return vrai ou faux
	 */
	/*private static boolean verifCroissant(int[] saisie) {
		int[] tabTmp=new int[saisie.length];
		for(int coup=0;coup<saisie.length;coup++) { //for(int i=0;i<saisie.length;i++)
			tabTmp[coup]=saisie[coup];
		}
			
		Arrays.sort(tabTmp);

		if(Arrays.equals(tabTmp, saisie))
			return true;
			
		
		return false;
		
	}*/
	
	/**
	 * verifie si les coups saisis sont dans la main
	 * @param saisie
	 * @param Jcourant
	 * @return vrai ou faux
	 */
	private static boolean verifMain(int[] saisie,Joueur Jcourant) {
		/*(int i=1;i<saisie.length;i++) {
			if(!Jcourant.getCarteEnMain().contains(saisie[i]));
				return false;
		}*/
		int cpt=0;
		for(int coup=0;coup<saisie.length;coup++) {
			for(int carte=0;carte<Jcourant.getCarteEnMain_size();carte++) {
				if(Jcourant.getCarteDansMain(carte)==saisie[coup]) {
					cpt++;
					continue;
				}
			}
		}
		if(cpt==saisie.length)
			return true;
		else return false;
	}
	/**
	 * verifie si les coups de la saisie sont valides
	 * @param saisieAvecPile
	 * @param saisieSansPile
	 * @param Jcourant
	 * @param Jadverse
	 * @return vrai ou faux
	 */
	private static boolean verifPoserCarte(String[] saisieAvecPile,int[] saisieSansPile, Joueur Jcourant, Joueur Jadverse) {
		Joueur JtmpCourant=new Joueur(Jcourant);//joueur temporaire pour poser des cartes temporairement
		Joueur JtmpAdverse=new Joueur(Jadverse);
		
		for(int coup=0; coup<saisieAvecPile.length;coup++) {
			if(saisieAvecPile[coup].length()==4) { //si il y a 4 caracteres donc xxx'
				if(saisieAvecPile[coup].charAt(2)==94) {//^
					if(saisieSansPile[coup]<JtmpAdverse.getPileAsc() ) {
						JtmpCourant.setPileAsc(saisieSansPile[coup]);
						for(int carte=0;carte<JtmpCourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[coup]==JtmpCourant.getCarteDansMain(carte)) {
								JtmpCourant.retirerCarte(carte);
								break;
							}
						}
						continue;
					}
					else return false;
				}
				else if(saisieAvecPile[coup].charAt(2)==118) {//v
					if(saisieSansPile[coup]>JtmpAdverse.getPileDesc()) {
						JtmpCourant.setPileDesc(saisieSansPile[coup]);
						for(int carte=0;carte<JtmpCourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[coup]==JtmpCourant.getCarteDansMain(carte)) {
								JtmpCourant.retirerCarte(carte);
								break;
							}
						}
						continue;
					}
					else return false;
				}
			}
			else {//si il y a 3 caracteres
				if(saisieAvecPile[coup].charAt(2)==94) {//^
					if(saisieSansPile[coup]>JtmpCourant.getPileAsc() || saisieSansPile[coup]==(JtmpCourant.getPileAsc()-10)) {
						JtmpCourant.setPileAsc(saisieSansPile[coup]);
						for(int carte=0;carte<JtmpCourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[coup]==JtmpCourant.getCarteDansMain(carte)) {
								JtmpCourant.retirerCarte(carte);
								break;
							}
						}
						continue;
					}
					else return false;
				}
				else if(saisieAvecPile[coup].charAt(2)==118) {//v
					if(saisieSansPile[coup]<JtmpCourant.getPileDesc() || saisieSansPile[coup]==(JtmpCourant.getPileDesc()+10)) {
						JtmpCourant.setPileDesc(saisieSansPile[coup]);
						for(int carte=0;carte<JtmpCourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[coup]==JtmpCourant.getCarteDansMain(carte)) {
								JtmpCourant.retirerCarte(carte);
								break;
							}
						}
					}
					else return false; 
				}
			}
		}
		return true;
	}
	
	//verifier saisie
	/**
	 * fait la verification complete de la saisie
	 * @param saisieAvecPile
	 * @param saisieSansPile
	 * @param Jcourant
	 * @param Jadverse
	 * @return vrai ou faux
	 */
	private static boolean verifierSaisie(String[] saisieAvecPile,int[] saisieSansPile, Joueur Jcourant, Joueur Jadverse) {
		/*if(verifCoupSaisi(saisieAvecPile)&&verifPasDoublon(saisieSansPile)
				&&verifCroissant(saisieSansPile)&&verifMain(saisieSansPile,Jcourant)
				&&verifPoserCarte(saisieAvecPile,saisieSansPile,Jcourant,Jadverse)){
			return true;
		}*/
		//**************//pleins de if pour mieux comprendre quand on debug
		
		if(verifBonFormat(saisieAvecPile)){
			if(verifPasDoublon(saisieSansPile)) {
				//if(verifCroissant(saisieSansPile)) {
					if(verifMain(saisieSansPile,Jcourant)){
						if(verifPoserCarte(saisieAvecPile,saisieSansPile,Jcourant,Jadverse)) {
							return true;
						}
					}
				//}
			}
		}
		return false;
		
	}
	//******************************************************************************
	////****poser une carte
	/**
	 * pose les cartes sur les piles
	 * @param Jcourant
	 * @param Jadverse
	 * @param saisieAvecPile
	 * @param saisieSansPile
	 * @return le nombre de cartes posées
	 */
	public static int poserCarte(Joueur Jcourant,Joueur Jadverse, String[]saisieAvecPile, int[]saisieSansPile) {
		int nb_cartesPosees=0;

		for(String coup : saisieAvecPile) {
			
			if(coup.length()==3) {//dans notre base
				if(coup.charAt(2)==94) {//^
					Jcourant.setPileAsc(saisieSansPile[nb_cartesPosees]);
					for(int carte=0;carte<Jcourant.getCarteEnMain_size();carte++) {
						if(saisieSansPile[nb_cartesPosees]==Jcourant.getCarteDansMain(carte)) {
							Jcourant.retirerCarte(carte);
							continue;
						}
					}
				}
				if(coup.charAt(2)==118) {//v
					Jcourant.setPileDesc(saisieSansPile[nb_cartesPosees]);
					for(int carte=0;carte<Jcourant.getCarteEnMain_size();carte++) {
						if(saisieSansPile[nb_cartesPosees]==Jcourant.getCarteDansMain(carte)) {
							Jcourant.retirerCarte(carte);
							continue;
						}
					}
				}
			}
			else if(coup.length()==4) {//dans base adverse
					if(coup.charAt(2)==94) {//^
						Jadverse.setPileAsc(saisieSansPile[nb_cartesPosees]);
						for(int carte=0;carte<Jcourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[nb_cartesPosees]==Jcourant.getCarteDansMain(carte)) {
								Jcourant.retirerCarte(carte);
								continue;
							}
						}
					}
					if(coup.charAt(2)==118) {//v
						Jadverse.setPileDesc(saisieSansPile[nb_cartesPosees]);
						for(int carte=0;carte<Jcourant.getCarteEnMain_size();carte++) {
							if(saisieSansPile[nb_cartesPosees]==Jcourant.getCarteDansMain(carte)) {
								Jcourant.retirerCarte(carte);
								continue;
							}
						}
					}
			}
			nb_cartesPosees++;
		}
		return nb_cartesPosees;
	}
	
	////****fin de tour piocher
	/**
	 * pioche les cartes à la fin de chaque tour : 2 si on pose sur notre base et 
	 * piocher jusqu'à compléter la main si on pose chez l'adversaire
	 * 
	 * @param Jcourant
	 * @param saisieAvecPile
	 * @return nombre de cartes piochées
	 */
	public static int piocherFinTour(Joueur Jcourant, String[]saisieAvecPile) {
		int nb_cartesPiochees=0;
		boolean poserBaseAdverse=false;
		for(String coup : saisieAvecPile) {
			if(coup.length()==4) {
				poserBaseAdverse=true;
				break;
			}
		}
		if(poserBaseAdverse) {
			while(Jcourant.getCarteEnMain_size()!=Joueur.MAX_MAIN) {
				Jcourant.piocher();
				if(!Jcourant.piocheIsEmpty())
					nb_cartesPiochees++;
			}
		}
		
		else{
			Jcourant.piocher();
			Jcourant.piocher();
			if(!Jcourant.piocheIsEmpty())
				nb_cartesPiochees=2;
			
		}
		return nb_cartesPiochees;
	}
	
}
