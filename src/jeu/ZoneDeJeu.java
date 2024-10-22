package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {

	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	
	
	
	public int donnerLimitationVitesse() {
		int limite = 50;
		if (pileLimite.isEmpty() || pileLimite.get(pileLimite.size() - 1) instanceof FinLimite) { //- 1 à revoir
			limite = 200;
		}
		
		return limite;
	}
	
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Borne b : pileBorne) {
			km += b.getKm();
		}
		return km;
	}
	
	public void deposer(Carte c) {
		
		if (c instanceof Borne b) {
			pileBorne.add(b);
		} else if (c instanceof Limite l){
			pileLimite.add(l);
		} else if (c instanceof Bataille b) {
			pileBataille.add(b);
		}
	}
	
	
}
