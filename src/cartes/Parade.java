package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		Type type = getType();
		return type.getParade();
	}
	
	

}
