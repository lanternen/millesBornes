package testsFonctionnels;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cartes.JeuDeCartes;

class TestJeuDeCartes {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		JeuDeCartes j = new JeuDeCartes();
		String chaine = j.affichageJeuDeCartes();
		assertNotNull(chaine);
		//System.out.println("\n" + chaine);
	}

}
