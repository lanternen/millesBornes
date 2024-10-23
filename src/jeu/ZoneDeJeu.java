package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
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
	// pour eviter de faire pileLimite.get(pileLimite.size() -1) tout le temps
	
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
		} else if (c instanceof Bataille bat) {
			pileBataille.add(bat);
		}
	}
	
	
	public boolean peutAvancer() {
//		return (!pileBataille.isEmpty() && pileBataille.get(pileBataille.size() - 1).equals(Cartes.FEU_VERT));
		return (!pileBataille.isEmpty() && donnerSommet(pileBataille).equals(Cartes.FEU_VERT));
//		Bataille b = donnerSommet(pileBataille);
//		if (b != null)
//		{
//			return b.equals(Cartes.FEU_VERT);
//		}
//		return false;
	}	
	
	
	
	public boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty()) {
			return true;
		}
		Bataille l = donnerSommet(pileBataille);
		if (l != null) {
			return (l.equals(Cartes.FEU_ROUGE));
		} else {
			//Bataille b = donnerSommet(pileBataille);
			return (l instanceof Parade && !(l.equals(Cartes.FEU_VERT)));
		}
		
	}		
	
	public boolean estDepotBorneAutorise() {
		Bataille bat = donnerSommet(pileBataille);
		Borne b = donnerSommet(pileBorne);
		return  (b != null) && (bat != null) && !(bat.equals(Cartes.FEU_ROUGE))
				&& (b.getKm() + donnerKmParcourus() <= 1000)
				&& b.getKm() <= donnerLimitationVitesse();

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
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		Bataille s = donnerSommet(pileBataille);
		if (bataille instanceof Attaque) {
			return (s != null) && !s.equals(Cartes.FEU_ROUGE);
		}
		if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT))
			{
				return estDepotFeuVertAutorise(); // tout simplement
			} else {
				return (s instanceof Attaque) && (s.getType().equals(bataille.getType()));
			}	// ai efface (s != null) d'apres recommandation Sonar Lynt
		}
		return false;
	}
	
	
	public boolean estDepotAutorise(Carte carte) {
		
		//j'arrive pas à le faire avec switch, decidement
		if (carte instanceof Borne) {
			return estDepotBorneAutorise();
		}
		if (carte instanceof Bataille bat) {
			return estDepotBatailleAutorise(bat);
		}
		if (carte instanceof Limite l) {
			return estDepotLimiteAutorise(l);
		}
		
		return false;		
	}
	
	
	
	
}
