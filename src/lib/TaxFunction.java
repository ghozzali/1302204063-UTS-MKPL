package lib;

public class TaxFunction {

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		numberOfChildren = Math.min(numberOfChildren, 3);

		double taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000
				- (isMarried ? 4500000 : 0) - (numberOfChildren * 1500000);
		tax = (int) Math.round(0.05 * taxableIncome);

		return Math.max(tax, 0);

	}

}
