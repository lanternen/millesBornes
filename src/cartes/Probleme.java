package cartes;

public abstract class Probleme extends Carte {

	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		return 31 * getType().hashCode() + super.hashCode();
	}	
	
	@Override
	public boolean equals(Object o) {
		if (super.equals(o) && o instanceof Probleme probleme) {
				return type.equals(probleme.getType()); 		//merge deux if en un suite à recommandation SonarLynt
			}
		return false;
	}
	
}
