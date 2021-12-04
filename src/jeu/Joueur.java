/**
 * @sujet : Projet BPO : java. Jeu "The GAME - Le Duel"
 * @author : Tracy HONG et Nahean BADAR
 * @date : Février - Mars 2021
 */
package jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Joueur {
	// Données ----------------------------------------------------------------------------------------------
	private int pileAsc;
	private int pileDesc;
	private ArrayList<Integer> pioche;
	private ArrayList<Integer> carteEnMain;

	// Constante ---------------------------------------------------------------------------------------------
	public static final int MAX_MAIN=6;
	private static final int MIN_PIOCHE=2;
	private static final int MAX_PIOCHE=59;
	

	//Constructeur ---------------------------------------------------------------------------------------------
	public Joueur() {
		this.pileAsc=1;
		this.pileDesc=60;
		
		initPioche();
		carteEnMain=new ArrayList<Integer>();
		for(int i=0;i<MAX_MAIN;++i) {
			piocher();
		}
	}
	//Constructeur pour joueur tmp -------------------------------------------------------------------------------
	/**
	 * Constructeur pour joueur tmp
	 * @param J : joueur à copier
	 */
	public Joueur(Joueur J) {
		this.pileAsc=J.pileAsc;
		this.pileDesc=J.pileDesc;
		pioche = new ArrayList<Integer>(J.pioche);
		
		carteEnMain=new ArrayList<Integer>(J.carteEnMain);
	}
	
	// Getter -----------------------------------------------------------------------------------------------------
	/**
	 * récupère la pile ascendante
	 * @return la pile ascendante du joueur
	 */
	public int getPileAsc() {
		return this.pileAsc; 
	}
	/**
	 * récupère la pile descendante
	 * @return la pile descendante du joueur
	 */
	public int getPileDesc() {
		return this.pileDesc;
	}
	/**
	 * récupère le nombre des cartes en main
	 * @return le nombre des cartes en main
	 */
	public int getCarteEnMain_size() {
		return this.carteEnMain.size();
	}
	/**
	 * récupère les cartes en main
	 * @return les cartes en main
	 */
	public ArrayList<Integer> getCarteEnMain() {
		return this.carteEnMain;
	}
	/**
	 * récupère une carte en main à la position i
	 * @param i
	 * @return la carte en main à la position i
	 */
	public int getCarteDansMain(int i) {
		return this.carteEnMain.get(i);
	}
	/**
	 * récupère le nombre de carte dans la pioche
	 * @return le nombre de carte dans la pioche
	 */
	public int getPioche_size() {
		return this.pioche.size();
	}
	
	//setter
	/**
	 * modifie la pile ascendante
	 * @param carte
	 */
	public void setPileAsc(int carte) { 
		this.pileAsc=carte;
	}
	/**
	 * modifie la pile descendante
	 * @param carte
	 */
	public void setPileDesc(int carte) {
		this.pileDesc=carte;
	}

	//pioche
	/**
	 * initialise la pioche
	 */
	private void initPioche() {
		pioche = new ArrayList<Integer>();
        for (int i = MIN_PIOCHE; i <= MAX_PIOCHE; i++) {
           pioche.add(i);
        }
       Collections.shuffle(pioche);		
	}
	/**
	 * verifier si la pioche est vide
	 * @return vrai ou faux
	 */
	public boolean piocheIsEmpty() {
		return pioche.isEmpty();
	}	
	/**
	 * pioche une carte et la met dans la main
	 */
	public void piocher() {
		if(!piocheIsEmpty()) {
			int cartePiochée=pioche.remove(0);
			this.carteEnMain.add(cartePiochée);
			Collections.sort(this.carteEnMain);
		}
	}
	/**
	 * retire la carte de la main à la position i
	 * @param i
	 */
	public void retirerCarte(int i) {
		this.carteEnMain.remove(i);
	}
}
