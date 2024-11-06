package cartes;

public abstract class Carte {

	
	
	@Override
	public int hashCode() {
		return 31 * getClass().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return o != null && getClass().equals(o.getClass());
	}
	
	
	
}
