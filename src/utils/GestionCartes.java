package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {

	public GestionCartes() {
		// TODO Auto-generated constructor stub
	}

	
	public static <E> E extraire (List<E> l) {
		Random rn = new Random();
		int i = rn.nextInt(l.size());
		E e = l.get(i);
		l.remove(i);
		return e;
	}
	
	
	public static <E> E extraire2 (List<E> l) {
		Random rand = new Random();
		int rn = (rand.nextInt(l.size()));
		ListIterator<E> iter = l.listIterator();
		E e = null;
		
		for (; rn > 0 && iter.hasNext(); rn--) {
			e = iter.next();
		}
		iter.remove();
		
		return e;
	}

	public static <E> List<E> melanger (List<E> l) {
		int size = l.size();
		List<E> listeMelange = new ArrayList<>(size);
		
		for (; size > 0; size--) {
			listeMelange.add(extraire(l));
		}
		return listeMelange;
		
	}
	
	
	public static <E> boolean verifierMelange(List<E> liste1, List<E> liste2) {
		if (liste1.size() != liste2.size()) {
			return false;
		}
		
		Iterator<E> iter = liste1.iterator();
		E elt;
		
		while (iter.hasNext()) {
			elt = iter.next();
			if (Collections.frequency(liste1, elt) != Collections.frequency(liste2, elt)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	public static <E> List <E> rassembler(List <E> liste) {
		List <E> listeRes = new ArrayList<>(liste.size());
		Iterator<E> iter = liste.iterator();
		E elt;
		
		while(iter.hasNext()) {
			elt = iter.next();
			if (!(listeRes.contains(elt))) {
				int n = Collections.frequency(liste, elt);
				for (int i = 0; i< n; i++) {
					listeRes.add(elt);
				}
			}
		}
		return listeRes;
	}
	
	private <T> boolean trouverelementFinListe(List<T> listeRassemble, T element, int index) {
		int count = 0;
		Iterator<T> iter = listeRassemble.iterator();
		T elt;
		
		while (iter.hasNext()) {
			elt  = iter.next();
			if ((count == index) && elt == element)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static <E> boolean verifierRassemblement(List<E> liste) {
		
		E prev = null;

		int i = 0;
		for (ListIterator<E> iter = liste.listIterator(); iter.hasNext();) {
			int j = 0;
			E suivant = iter.next();
			if (prev != null && !suivant.equals(prev)) {
				for (ListIterator<E> iter2 = liste.listIterator(); iter2.hasNext();)
				{
					E suivant2 = iter2.next();
					if (j > i && suivant2.equals(prev)) {	//pas entièrement convaincu
						return false;
					}
					j++;
				}
			}
			prev = suivant;
			i++;
		}
		return true;
	}

}
