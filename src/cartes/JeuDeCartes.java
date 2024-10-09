package cartes;

public class JeuDeCartes {

	public static final int NB_MAX = 106;
	private Configuration[] typesDeCartes = new Configuration[] {
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
	
	
//	public Configuration[] getTypesDeCartes() {
//		return typesDeCartes;
//	}

	
	
	
//	public JeuDeCartes() {
//
//	}

	public String affichageJeuDeCartes() {
		
		StringBuilder jeuComplet = new StringBuilder();
		//for (Configuration configuration : typesDeCartes) {
		for (int i = 0; i < typesDeCartes.length; i++) {
			//System.out.println(typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte());
			jeuComplet.append(typesDeCartes[i].getNbExemplaires() + " " + typesDeCartes[i].getCarte() + "\n");
		}
		
		return jeuComplet.toString();		
	}
	
	
	public static int  getNbCartesMax() {
		return NB_MAX;
	}
	
	
	public int getCount() {
		int count = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaires(); j++) {
				count++;
			}
		}
		return count;
	}
	
	
	public Carte[] donnerCartes() {
		
		
		Carte[] cartes = new Carte[getCount()];
				
		int i = 0;
		for (int j = 0; j < 19; j++) {		// pour chacune des configurations (chaque case possible de typesDeCartes)
			for (int nCarte = 0; nCarte < typesDeCartes[j].getNbExemplaires(); nCarte++) {
				cartes[i++] = typesDeCartes[j].getCarte();
			}
		}
		
		return cartes;
	}
		
	
	
	public boolean checkCount() {
//		Carte[] cartes = donnerCartes();
//		int count = 0;
//		for (int i = 0; i < 19; i++)
//		{
//			Carte c = typesDeCartes[i].getCarte();
//			for (int j = 0; j < cartes.length; j++)
//			{
//				if (cartes[j].equals(c))
//				{
//					count++;
//				}
//			}
//			if (typesDeCartes[i].getNbExemplaires() != count)
//			{
//				return false;
//			}
//			count = 0;
//		}
//		return true;
		
		Carte[] cartes = donnerCartes();
		return cartes.length == getCount();
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
