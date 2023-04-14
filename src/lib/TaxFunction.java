package lib;

public class TaxFunction {

	/**
	 * Menghitung pajak berdasarkan gaji bulanan, penghasilan bulanan lainnya,
	 * jumlah
	 * bulan bekerja, pengurang, status perkawinan, dan jumlah anak.
	 * 
	 * @param monthlySalary        gaji bulanan
	 * @param otherMonthlyIncome   penghasilan bulanan lainnya
	 * @param numberOfMonthWorking jumlah bulan bekerja dalam setahun
	 * @param deductible           pengurang pajak
	 * @param isMarried            status perkawinan
	 * @param numberOfChildren     jumlah anak
	 * @return jumlah pajak yang dihitung
	 */

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;
		// Cek apakah jumlah bulan bekerja per tahun lebih dari 12
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		// Batasi jumlah anak maksimal hingga 3
		numberOfChildren = Math.min(numberOfChildren, 3);
		// Hitung penghasilan yang kena pajak
		double taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000
				- (isMarried ? 4500000 : 0) - (numberOfChildren * 1500000);
		// Hitung jumlah pajak
		tax = (int) Math.round(0.05 * taxableIncome);
		// Pastikan jumlah pajak tidak negatif
		return Math.max(tax, 0);

	}

}
