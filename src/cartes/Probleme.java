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
	public boolean equals(Object o) {
		if (o != null) {
			return this.getClass().equals(o.getClass());
		}
		return false;
	}
	
}
