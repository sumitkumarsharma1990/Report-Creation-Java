import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Report {
	private static List<Record> recordList;
	private static List<ReportModel> reportList;

	public static void main(String[] args) {
		// populate new records
		populateRecords();

		// Sort based on settlement date
		recordList.sort((a, b) -> a.getSettlementDate().compareTo(b.getSettlementDate()));

		// Calculate incoming and outgoing amounts
		generateReport();
		
		// Calculate Ranking
		calculateRankings();
		
		// Print report
		System.out.println("Entity \t SettlementDate \t\t Incoming Amount Rank \t Outgoing Amount Rank  \t Incoming Amount \t Outgoing Amount ");
		for(ReportModel rm : reportList) {
			System.out.println(rm.getEntity()+ " \t "+rm.getSettlementDate() +  " \t " + rm.getIncomingRank() + " \t "+ " \t "+ " \t " + rm.getOutgoingRank()+  " \t "+ " \t "+ " \t "+rm.getIncomingAmount()+  " \t "+ " \t "+ " \t "+rm.getOutgoingAmount());
		}
	}

	private static void populateRecords()  {
		recordList = new ArrayList<Record>();

		Record r1 = new Record();
		r1.setEntity("foo");
		r1.setBuySell('B');
		r1.setAgreedFx(0.50f);
		r1.setCurrency("SGP");
		r1.setInstructionDate(new Date(116, 0, 1));
		r1.setSettlementDate(new Date(116, 0, 2));
		r1.setUnits(200);
		r1.setPricePerUnit(100.25);
		recordList.add(r1);

		Record r2 = new Record();
		r2.setEntity("bar");
		r2.setBuySell('S');
		r2.setAgreedFx(0.22f);
		r2.setCurrency("AED");
		r2.setInstructionDate(new Date(116, 0, 5));
		r2.setSettlementDate(new Date(116, 0, 7));
		r2.setUnits(450);
		r2.setPricePerUnit(150.5);
		recordList.add(r2);

		Record r3 = new Record();
		r3.setEntity("foo");
		r3.setBuySell('B');
		r3.setAgreedFx(0.30f);
		r3.setCurrency("SGP");
		r3.setInstructionDate(new Date(116, 0, 3));
		r3.setSettlementDate(new Date(116, 0, 4));
		r3.setUnits(150);
		r3.setPricePerUnit(110.25);
		recordList.add(r3);

		Record r4 = new Record();
		r4.setEntity("bar");
		r4.setBuySell('S');
		r4.setAgreedFx(0.22f);
		r4.setCurrency("AED");
		r4.setInstructionDate(new Date(116, 0, 8));
		r4.setSettlementDate(new Date(116, 0, 10));
		r4.setUnits(300);
		r4.setPricePerUnit(130.25);
		recordList.add(r4);

		Record r5 = new Record();
		r5.setEntity("lorem");
		r5.setBuySell('S');
		r5.setAgreedFx(0.50f);
		r5.setCurrency("SGP");
		r5.setInstructionDate(new Date(116, 0, 3));
		r5.setSettlementDate(new Date(116, 0, 4));
		r5.setUnits(200);
		r5.setPricePerUnit(180.25);
		recordList.add(r5);

		Record r6 = new Record();
		r6.setEntity("ipsum");
		r6.setBuySell('B');
		r6.setAgreedFx(0.33f);
		r6.setCurrency("SAR");
		r6.setInstructionDate(new Date(116, 0, 12));
		r6.setSettlementDate(new Date(116, 0, 13));
		r6.setUnits(270);
		r6.setPricePerUnit(180.5);
		recordList.add(r6);
	}

	private static void generateReport() {
		reportList = new ArrayList<ReportModel>();
		Calendar calSettlement = Calendar.getInstance();
		Date settlementDate;

		for(Record record : recordList) {
			calSettlement.setTime(record.getSettlementDate());
			if(("AED".equalsIgnoreCase(record.getCurrency()) || "SAR".equalsIgnoreCase(record.getCurrency()))
					&& calSettlement.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				calSettlement.add(Calendar.DAY_OF_MONTH, 2);
				settlementDate = calSettlement.getTime();
			} else if(("AED".equalsIgnoreCase(record.getCurrency()) || "SAR".equalsIgnoreCase(record.getCurrency()))
					&& calSettlement.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				calSettlement.add(Calendar.DAY_OF_MONTH, 1);
				settlementDate = calSettlement.getTime();
			} else if(calSettlement.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				calSettlement.add(Calendar.DAY_OF_MONTH, 2);
				settlementDate = calSettlement.getTime();
			} else if(calSettlement.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				calSettlement.add(Calendar.DAY_OF_MONTH, 1);
				settlementDate = calSettlement.getTime();
			} else {
				settlementDate = record.getSettlementDate();
			}
			
			if(!reportList.isEmpty()) {
				ReportModel rm = reportList.get(reportList.size() - 1);
				if(0 == settlementDate.compareTo(rm.getSettlementDate())) {
					if(record.getBuySell() == 'B') {
						rm.setOutgoingAmount(rm.getOutgoingAmount() + (record.getPricePerUnit() * record.getUnits() * record.getAgreedFx()));
					rm.setEntity(record.getEntity());
					}
					else if(record.getBuySell() == 'S') {
						rm.setIncomingAmount(rm.getIncomingAmount() + (record.getPricePerUnit() * record.getUnits() * record.getAgreedFx()));
						rm.setEntity(record.getEntity());
					}
					continue;
				}
			}
			
			ReportModel rm = new ReportModel();
			rm.setSettlementDate(record.getSettlementDate());
			if(record.getBuySell() == 'B') {
				rm.setOutgoingAmount(record.getPricePerUnit() * record.getUnits() * record.getAgreedFx());
				rm.setEntity(record.getEntity());
			}else if(record.getBuySell() == 'S') {
				rm.setIncomingAmount(record.getPricePerUnit() * record.getUnits() * record.getAgreedFx());
				rm.setEntity(record.getEntity());
			}reportList.add(rm);
		}
	}
	
	private static void calculateRankings() {
		// reverse sort record list based on incoming amount
		reportList.sort((a, b) -> a.getIncomingAmount() > b.getIncomingAmount() ? -1 : a.getIncomingAmount() < b.getIncomingAmount() ? 1 : 0);
		
		int rank = 0;
		double lastvote = -1;
		
		for(ReportModel rm : reportList) {
			if(rm.getIncomingAmount() != lastvote)
				rm.setIncomingRank(++rank);
			
			lastvote = rm.getIncomingAmount();
		}
		
		// reverse sort record list based on outgoing amount
		reportList.sort((a, b) -> a.getOutgoingAmount() > b.getOutgoingAmount() ? -1 : a.getOutgoingAmount() < b.getOutgoingAmount() ? 1 : 0);
		
		rank = 0;
		lastvote = -1;
		
		for(ReportModel rm : reportList) {
			if(rm.getOutgoingAmount() != lastvote)
				rm.setOutgoingRank(++rank);
			
			lastvote = rm.getOutgoingAmount();
		}
		
		// sort the results back to settlement date
		reportList.sort((a, b) -> a.getSettlementDate().compareTo(b.getSettlementDate()));
	}
}