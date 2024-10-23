package jeu;

import cartes.Carte;

public class Joueur {

	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur; 
	
	public Joueur () {
		// pas nécessaire ?
	}
	
	
	
	
	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}

	public void donner(Carte c) {
		mainJoueur.prendre(c);
	}

	public Carte prendreCarte (Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		return sabot.piocher();
	}


	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public int hashCode() {
		return 31 *nom.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Joueur j) {
			return nom.equals(j.toString());
		}
		return false;
	}
	
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte c) {
		return zoneDeJeu.estDepotAutorise(c);
	}
	
}
