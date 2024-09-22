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
	
	public Iterateur iterator() {
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
	
	
	public Carte piocher() {
		
		Iterateur mainNext = iterator();
		Iterateur mainRemove = iterator();
		Carte cartePiochee = mainNext.next();
		mainRemove.remove();
		return cartePiochee;
	}
	
	// j'ai fait deux main pour la pioche parce que faire next (pour obtenir la 1ère carte)
	// incrémente l'indice de l'itérateurr et ça ferait que le remove qui suit supprimerait
	//la 2e carte et pas la première
	
	
	
	
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
