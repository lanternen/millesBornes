package testsFonctionnels;

import cartes.*;
import jeu.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SabotTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		
		JeuDeCartes j = new JeuDeCartes();		
		Carte[] c = j.getCartes();
		
		Sabot s = new Sabot(c, c.length);
		

		Iterator<Carte> iter = s.iterator();
		while (iter.hasNext()) {
			Carte carte = s.piocher();
			System.out.println(carte.toString());
		}
		
//		for (Iterator<Carte> iter = s.iterator(); iter.hasNext();) {
//			Carte carte = s.piocher();
//			System.out.println(carte);
//			System.out.println(iter.next());
//			iter.remove();
//		}
	
		
	}

}
