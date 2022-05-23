package tartanga.dami.equipoa.model;

public class Discount {
	private int idDiscount;
	private boolean withDiscount;
	private int discount;
	
	public Discount() {
		super();
	}

	public int getIdDiscount() {
		return idDiscount;
	}

	public void setIdDiscount(int idDiscount) {
		this.idDiscount = idDiscount;
	}

	public boolean isWithDiscount() {
		return withDiscount;
	}

	public void setWithDiscount(boolean withDiscount) {
		this.withDiscount = withDiscount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
