package jeu;

import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import utils.GestionCartes;

public class Jeu {

	private Sabot sabot;
	private List<Carte> listeCartes;
	
	public Jeu (Carte[] cartes){
		listeCartes = GestionCartes.melanger(Arrays.asList(cartes));
		sabot = new Sabot( listeCartes.toArray(new Carte[0]));	// cast changé en new Carte[0]
																// après correction SonarLynt
	}
	
}
