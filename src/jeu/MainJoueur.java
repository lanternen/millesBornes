package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {

	private List<Carte> main = new ArrayList<>();
	
	public void prendre (Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}
	
	public String afficherMain () {
		StringBuilder s = new StringBuilder();
		for (Carte c : main) {
			s.append(c.toString() + '\n');
		}
		return s.toString();
	}
	
	@Override
	public String toString() {
		return afficherMain();
	}
	
	
}
