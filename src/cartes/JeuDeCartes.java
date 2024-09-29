package cartes;

public class JeuDeCartes {

	private static final int NB_MAX = 106;
	private Configuration[] typesDeCartes = new Configuration[19];		
	
	
//	public Configuration[] getTypesDeCartes() {
//		return typesDeCartes;
//	}

	
	public JeuDeCartes() {
		
		this.typesDeCartes = new Configuration[] {
				new Configuration(new Borne(25), 10),
				new Configuration(new Borne(50), 10),
				new Configuration(new Borne(75), 10),
				new Configuration(new Borne(100), 12),
				new Configuration(new Borne(200), 4),
				new Configuration(new Parade(Type.FEU), 14),
				new Configuration(new Parade(Type.ESSENCE), 6),
				new Configuration(new Parade(Type.CREVAISON), 6),
				new Configuration(new Parade(Type.ACCIDENT), 6),
				new Configuration(new FinLimite(), 6),
				new Configuration(new DebutLimite(), 4),
				new Configuration(new Attaque(Type.ACCIDENT), 3),
				new Configuration(new Attaque(Type.FEU), 5),
				new Configuration(new Attaque(Type.CREVAISON), 3),
				new Configuration(new Attaque(Type.ESSENCE), 3),
				new Configuration(new Botte(Type.ESSENCE), 1),
				new Configuration(new Botte(Type.FEU), 1),
				new Configuration(new Botte(Type.CREVAISON), 1),
				new Configuration(new Botte(Type.ACCIDENT), 1),
		};
		
	}

	public String affichageJeuDeCartes() {
		
		StringBuilder jeuComplet = new StringBuilder();
		//for (Configuration configuration : typesDeCartes) {
		for (int i = 0; i < typesDeCartes.length; i++) {
			System.out.println(typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte());
			jeuComplet.append(typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte() + "\n");
		}
		
		return jeuComplet.toString();		
	}
	
	
	public Carte[] getCartes() {
		Carte[] cartes = new Carte[NB_MAX];
		int indiceDeck = 0;		//l'indice du tab qu'on renvoie
		for (int i = 0; i < 19; i++) {		// pour chacune des configurations (chaque case possible de typesDeCartes)
			Configuration config = typesDeCartes[i];	// on créé une configuration
			for (int j = 0; j < config.getNbExemplaires(); j++) {	//pour chaque config, on répète pour le nombre d'exemplaires
				cartes[indiceDeck] = config.getCarte();		//on met la carte dans le tab renvoyé
				indiceDeck++;				//on incrémente l'indice du tab renvoyé
			}
		}
		
		return cartes;
	}
		
	
	private static class Configuration extends Carte {
		
		private int nbExemplaires;	
		private Carte carte;

		public Configuration(Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
		
		
	}
	
	
	
	
	
	
}
