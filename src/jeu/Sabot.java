package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Sabot implements Iterable <Carte> {

	private Carte[] cartes;
	private int nbCartes = JeuDeCartes.getNbCartesMax();
	private int nbOperations = 0;
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		//System.out.println(cartes);	//c'est �a qui me printait [Lcartes.Carte;@1175e2db
						// cartes est un tab, �videmment qu'on peut pas le print comme �a
	}
	
	public Iterateur iterator() {
		return new Iterateur();
	}
	
	
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes < JeuDeCartes.getNbCartesMax()) {
			cartes[nbCartes] = carte;
			nbOperations++;
			nbCartes++;
		} else {
			throw new IllegalStateException();
		}
	}
	
	public Carte piocher() {
		
		Iterator<Carte> mainNext = iterator();
		//Iterateur mainRemove = iterator();
		Carte cartePiochee = mainNext.next();
		//mainRemove.remove();
		mainNext.remove();
		return cartePiochee;
		
	}
	
	// j'ai fait deux "main" pour la pioche parce que faire next (pour obtenir la 1ère carte)
	// incrémente l'indice de l'itérateurr et ça ferait que le remove qui suit supprimerait
	//la 2e carte et pas la première
	
	
	
	
	private class Iterateur implements Iterator <Carte> {
		
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsReference = nbOperations;
		
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
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {	// ! avant nextEffectue !!!!!
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
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
