package cartes;

public class Borne extends Carte {

	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + " Km";
	}

//	@Override
//	public boolean equals (Object obj) {
//		if (obj instanceof Borne bo) {
//			return this.km == bo.km;
//		}
//		return false;
//	}
	
}
