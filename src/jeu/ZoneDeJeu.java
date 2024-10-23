package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {

	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	
	
	private <E> E donnerSommet(List<E> pile) {
		if (!pile.isEmpty()) {
			return pile.get(pile.size() - 1);
		}
		return null;
	}
	
	public int donnerLimitationVitesse() {
		int limite = 50;
//		if (pileLimite.isEmpty() || pileLimite.get(pileLimite.size() - 1) instanceof FinLimite) { //- 1 à revoir
//			limite = 200;
//		}
		
		if (pileLimite.isEmpty() || donnerSommet(pileLimite) instanceof FinLimite) { //- 1 à revoir
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
	
	
	public boolean peutAvancer() {
//		return (!pileBataille.isEmpty() && pileBataille.get(pileBataille.size() - 1).equals(Cartes.FEU_VERT));
		Bataille b = donnerSommet(pileBataille);
		if (b != null)
		{
			return b.equals(Cartes.FEU_VERT);
		}
		return false;
	}	
	
	
	
	public boolean estDepotFeuVertAutorise() {
		if (!pileLimite.isEmpty()) {
			return true;
		}
		Limite l = donnerSommet(pileLimite);
		if (l != null) {
			return (l.equals(Cartes.FEU_ROUGE));
		} else {
			Bataille b = donnerSommet(pileBataille);
			return (b instanceof Parade && !(b.equals(Cartes.FEU_VERT)));
		}
		
	}		
	
	public boolean estDepotBorneAutorise() {
		Bataille bat = donnerSommet(pileBataille);
		Borne b = donnerSommet(pileBorne);
		if (bat == null) {
			return false;
		} else {
			return  !(bat.equals(Cartes.FEU_ROUGE))
				&& (b.getKm() + (donnerKmParcourus()) < 1000)
				&& b.getKm() <= donnerLimitationVitesse();
		}
	}			
	
	
	 public boolean estDepotLimiteAutorise(Limite limite) {
		 if (limite instanceof DebutLimite) {
			 return pileLimite.isEmpty() || (donnerSommet(pileLimite) instanceof FinLimite);
		 }
		 if (limite instanceof FinLimite) {
			 return (donnerSommet(pileLimite) instanceof DebutLimite);
		 }
		 else return false;
	 }
	
	
	
	
	
	
	
}
