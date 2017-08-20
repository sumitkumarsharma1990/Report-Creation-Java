import java.util.Date;

public class ReportModel {
	private Date settlementDate;
	private double incomingAmount;
	private double outgoingAmount;
	private int incomingRank;
	private int outgoingRank;
	private String entity;
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public double getIncomingAmount() {
		return incomingAmount;
	}
	public void setIncomingAmount(double incomingAmount) {
		this.incomingAmount = incomingAmount;
	}
	public double getOutgoingAmount() {
		return outgoingAmount;
	}
	public void setOutgoingAmount(double outgoingAmount) {
		this.outgoingAmount = outgoingAmount;
	}
	public int getIncomingRank() {
		return incomingRank;
	}
	public void setIncomingRank(int incomingRank) {
		this.incomingRank = incomingRank;
	}
	public int getOutgoingRank() {
		return outgoingRank;
	}
	public void setOutgoingRank(int outgoingRank) {
		this.outgoingRank = outgoingRank;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
}