package tartanga.dami.equipoa.model;

public class Partner extends User{
	@Override
	public String toString() {
		return "Partner [numAccount=" + numAccount + ", toString()=" + super.toString() + "]";
	}

	private int numAccount;

	public Partner() {
		super();
	}

	public int getNumAccount() {
		return numAccount;
	}

	public void setNumAccount(int numAccount) {
		this.numAccount = numAccount;
	}
	
}