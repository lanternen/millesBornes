package cartes;

public abstract class Carte {

	
	
	@Override
	public int hashCode() {
		return 31 *getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Carte ca) {
			return toString().equals(ca.toString());
		}
		return false;
	}
	
	
	
}
