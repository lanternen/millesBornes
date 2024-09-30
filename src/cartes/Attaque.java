package cartes;

public class Attaque extends Bataille {
	
	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		Type type = getType();
		return type.getAttaque();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Attaque a)
		{
			return this.getType() == a.getType();
		}
		
		
		return false;
	}
	
	
	
}
