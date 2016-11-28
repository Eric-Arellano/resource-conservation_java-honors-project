package features;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class HistoricalUsage {

	private ArrayList<Double> histUsage;
	private int count = 0;
	private double avg = 0;
	private int minIndex = 0;
	private int maxIndex = 0;

	String resourceName;
	double rate;
	String usageUnit;
	String inputUnit;

	private final DecimalFormat decimals = new DecimalFormat("0.##");

	public HistoricalUsage(String resourceName,
	                       double rate,
	                       String usageUnit,
	                       String inputUnit) {
		histUsage = new ArrayList<>();
		this.resourceName = resourceName;
		this.rate = rate;
		this.usageUnit = usageUnit;
		this.inputUnit = inputUnit;
	}


	// ================================================================================
	// Public Interface
	// ================================================================================

	public String displayHistorical(String usageUnit) {
		String histUsageResult = "Historical usage:\t";
		Iterator<Double> iterator = histUsage.iterator();
		if (iterator.hasNext()) {
			String amount = decimals.format(iterator.next());
			histUsageResult += amount + " " + usageUnit;
		}
		while (iterator.hasNext()) {
			String amount = decimals.format(iterator.next());
			histUsageResult += ", " + amount + " " + usageUnit;
		}
		return histUsageResult;
	}

	public String compareHistorical(double usageAmt) {
		ComparisonHelper comparer = new ComparisonHelper(resourceName, rate, usageUnit, inputUnit, usageAmt,
				getAvg(), getMinVal(), getMaxVal());
		String comparison = comparer.compareHistorical();
		return comparison;
	}


	// ================================================================================
	// Add data to Array
	// ================================================================================

	public void addHistorical(double usageAmt) {
		histUsage.add(usageAmt);
		count++;
		// if enough values, update min/max/avg
		if (count <= 2)
			updateValues();
	}

	/**
	 * @param rate  - should be same rate as in constructor of object ResourceUsage
	 * @param input - note in input amount (e.g. minute), not usage amount (e.g. gallons)
	 */
	// TODO: Refactor preFill function to be constructor instead
	public void preFillData(double rate, double... input) // uses VarArg
	{
		for (double value : input) {
			double usage = calcUsageFromInput(rate, value);
			addHistorical(usage);
		}
		updateValues();
	}

	private double calcUsageFromInput(double rate, double inputAmt) {
		return inputAmt * rate;
	}


	// ================================================================================
	// Update values
	// ================================================================================

	/**
	 * Updates min, max, and avg values.
	 */
	private void updateValues() {
		updateAvg();
		updateMin();
		updateMax();
	}

	private void updateAvg() {
		double sum = 0;
		for (Double element : histUsage)
			sum += element;
		this.avg = sum / count;
	}

	private void updateMin() {
		// because array is chronological, just check new values
		for (int index = minIndex + 1; index < count; index++) {
			if (histUsage.get(index) < histUsage.get(minIndex))
				minIndex = index;
		}
	}

	private void updateMax() {
		/// because array is chronological, just check new values
		for (int index = maxIndex + 1; index < count; index++) {
			if (histUsage.get(index) > histUsage.get(maxIndex))
				maxIndex = index;
		}
	}


	// ================================================================================
	// Internal Accessor Methods
	// ================================================================================

	private double getMinVal() {
		return this.histUsage.get(minIndex);
	}

	private double getMaxVal() {
		return this.histUsage.get(maxIndex);
	}

	private double getAvg() {
		return this.avg;
	}

}
