package cartes;

public interface Cartes {

	public static final Carte PRIORITAIRE = new Botte(Type.FEU);
	public static final Carte FEU_ROUGE = new Attaque(Type.FEU);
	public static final Carte FEU_VERT = new Parade(Type.FEU);
	
}
