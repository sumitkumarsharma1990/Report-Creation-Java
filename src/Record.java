import java.util.Date;

public class Record {
	private String entity;
	private char buySell;
	private float agreedFx;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private double pricePerUnit;
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public char getBuySell() {
		return buySell;
	}
	public void setBuySell(char buySell) {
		this.buySell = buySell;
	}
	public float getAgreedFx() {
		return agreedFx;
	}
	public void setAgreedFx(float agreedFx) {
		this.agreedFx = agreedFx;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getInstructionDate() {
		return instructionDate;
	}
	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}