package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable <Carte> {

	private Carte[] cartes;
	private int nbCartes = 0;
	private int nbOperations = 0;
	
	public Sabot(Carte[] cartes, int nbCartesMax) {
		this.cartes = new Carte[nbCartesMax];

	}
	
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes < cartes.length) {
			cartes[nbCartes] = carte;
			nbOperations++;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	
	
	
	
	
	
	
	private class Iterateur implements Iterator <Carte> {
		
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsReference;
		
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new IllegalStateException();
			}
		}
		
		
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || nextEffectue) {
				throw new NoSuchElementException();
			}
			for (int i = indiceIterateur; i < nbCartes; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nbOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if (nbOperations != nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
		
		
	}

	
	
}
