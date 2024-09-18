package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		Type type = getType();
		return type.getBotte();
	}

}
