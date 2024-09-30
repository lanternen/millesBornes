package utils;

import java.util.ArrayList;
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

	public static <E> List<E> melange (List<E> l) {
		int size = l.size();
		List<E> listeMelange = new ArrayList<>(size);
		
		for (; size > 0; size--) {
			listeMelange.add(extraire(l));
		}
		return listeMelange;
		
	}
	
	
	public <E> boolean verifierMelange(List<E> liste1, List<E> liste2) {
		if (liste1.size() != liste2.size()) {
			return false;
		}
		
		// rajouter la comparaison de chaque élément
		
		return true;
		
		
	}
	





}
