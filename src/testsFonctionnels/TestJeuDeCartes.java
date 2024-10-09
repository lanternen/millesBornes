package testsFonctionnels;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.JeuDeCartes;

class TestJeuDeCartes {

//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@Test
//	void test() {
//		JeuDeCartes j = new JeuDeCartes();
//		String chaine = j.affichageJeuDeCartes();
//		assertNotNull(chaine);
//		//System.out.println("\n" + chaine);
//	}
//	
//	
	
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println("JEU:\n" + jeu.affichageJeuDeCartes());
		
		if (!jeu.checkCount()) {
			System.out.println("erreur de nombre");
		}
	}

}
