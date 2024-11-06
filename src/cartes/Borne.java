package cartes;

public class Borne extends Carte {

	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	public int getKm() {
		return km;
	}
	
	@Override
	public String toString() {
		return km + " Km";
	}
	
	@Override
	public int hashCode() {
		return 31 * getClass().hashCode();
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Borne bo) {
			return super.equals(obj) && this.km == bo.getKm();
		}
		return false;
	}
	
}
