package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	
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
		
		if (pileLimite.isEmpty() || donnerSommet(pileLimite) instanceof FinLimite || estPrioritaire()) {
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
	
	public void deposer(Carte carte) {
		
		if (carte instanceof Borne borne) {
			pileBorne.add(borne);
		} else if (carte instanceof Limite limite){
			pileLimite.add(limite);
		} else if (carte instanceof Bataille bataille) {
			pileBataille.add(bataille);
		} else if (carte instanceof Botte botte) {
			bottes.add(botte);
		}
	}
	
	
	public boolean peutAvancer() {
//		return (!pileBataille.isEmpty() && pileBataille.get(pileBataille.size() - 1).equals(Cartes.FEU_VERT));
		
		Bataille sommet = donnerSommet(pileBataille);
		if (sommet != null) {
			
//			if (!pileBataille.isEmpty() && estPrioritaire()) {
//				return true;
//			} else if (sommet.equals(Cartes.FEU_VERT))
//			{
//				return true;
//			}
//			
			
			return     (!pileBataille.isEmpty() && estPrioritaire()) 
					|| (sommet.equals(Cartes.FEU_VERT))
					|| (sommet instanceof Parade && estPrioritaire())
					|| (sommet instanceof Attaque && sommet.equals(Cartes.FEU_ROUGE) && estPrioritaire())
					|| (sommet instanceof Attaque && !sommet.equals(Cartes.FEU_ROUGE) && bottes.contains(new Botte(sommet.getType())) && estPrioritaire())
					;
			
			//return !pileBataille.isEmpty() && sommet.equals(Cartes.FEU_VERT) && estPrioritaire();
		}
		return false;
		
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
		} else if (estPrioritaire()) {
			return false;
		} else {
			Bataille sommet = donnerSommet(pileBataille);
			return	((sommet != null) && (sommet.equals(Cartes.FEU_ROUGE)))
					|| (sommet instanceof Parade && !(sommet.equals(Cartes.FEU_VERT)))
					|| (sommet instanceof Attaque && bottes.contains(new Botte(sommet.getType())));
		}
//		Bataille bataille = donnerSommet(pileBataille);
//		if (bataille == null) {
//			return false;
//		} else {
//			//Bataille b = donnerSommet(pileBataille);
//			return (bataille.equals(Cartes.FEU_ROUGE)) || (bataille instanceof Parade && !(bataille.equals(Cartes.FEU_VERT)));
//		}
		
		
	}		
	
	public boolean estDepotBorneAutorise(Borne borne) {
//		Bataille bat = donnerSommet(pileBataille);
//		Borne b = donnerSommet(pileBorne);
//		return (b != null) && (bat != null) && !(bat.equals(Cartes.FEU_ROUGE))
//				&& b.getKm() + donnerKmParcourus() <= 1000
//				&& b.getKm() <= donnerLimitationVitesse();
		
		return peutAvancer()
				&& borne.getKm() <= donnerLimitationVitesse()
				&& borne.getKm() + donnerKmParcourus() <= 1000;
	}			
	
	
	 public boolean estDepotLimiteAutorise(Limite limite) {
//		 if (limite instanceof DebutLimite) {
//			 return pileLimite.isEmpty() || (donnerSommet(pileLimite) instanceof FinLimite);
//		 }
//		 
//		 return false;
		 if (estPrioritaire()) {
			 return false;
		 } else if (limite instanceof FinLimite) {
			 return (donnerSommet(pileLimite) instanceof DebutLimite);
		 } else {
			 return (donnerSommet(pileLimite) == null) || donnerSommet(pileLimite) instanceof FinLimite;
		 }
	 }
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		Bataille sommet = donnerSommet(pileBataille);
		
		if (bottes.contains(new Botte(bataille.getType()))) {
			return false;
		}
		
		if (bataille instanceof Attaque) {
			return peutAvancer();
			//return (s != null) && !s.equals(Cartes.FEU_ROUGE);
		}
		if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT))
			{
				return estDepotFeuVertAutorise(); // tout simplement
			} else {
				return (sommet instanceof Attaque) && (sommet.getType().equals(bataille.getType()));
			} 	// ai efface (s != null) d'apres recommandation Sonar Lynt
		}	
		
		//c'est cette fonction qui ne pose problème (d'aprè professeur et je suis d'accord))
		
		return false;
	}
	
	
	public boolean estDepotAutorise(Carte carte) {
		
		//j'arrive pas à le faire avec switch, decidement
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if (carte instanceof Botte botte) {
			return true;
		}
		return false;		
	}
	
	
	
	public boolean estPrioritaire() {
		return bottes.contains(new Botte(Type.FEU));
	}
	
	
	
	
	
	
	
}
