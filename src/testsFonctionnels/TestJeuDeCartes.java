package testsFonctionnels;

import static org.junit.Assert.assertNotNull;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {

	
	void main() {
		JeuDeCartes j = new JeuDeCartes();
		String chaine = j.affichageJeuDeCartes();
		assertNotNull(chaine);
		System.out.println(chaine);
	}
	
	
}
