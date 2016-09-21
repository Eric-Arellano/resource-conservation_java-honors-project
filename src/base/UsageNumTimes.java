package base;

import features.AverageUsage;
import features.HistoricalUsage;
import features.Tips;

public class UsageNumTimes extends ResourceUsage {

	// ================================================================================
	// Constructor
	// ================================================================================

	public UsageNumTimes(String name,
	                     double rate,
	                     String usageUnit,
	                     Tips tips,
	                     AverageUsage avg,
	                     HistoricalUsage historical) {
		super(name, rate, usageUnit, tips, avg, historical);
		setInputUnit("times");
	}

	// ================================================================================
	// Prompt Input
	// ================================================================================

	public void promptInput() {
		System.out.print("How many " + this.getInputUnit() + " did you use the " + getName() + "? ");
		double input = in.nextDouble(); // double so that it can include things like halves
		setInputAmt(input);
	}

}
