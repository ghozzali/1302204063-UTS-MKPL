package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee { // Package ini berisi kelas Employee yang merepresentasikan seorang pegawai.

	private String employeeId; // Kelas ini memiliki beberapa atribut seperti employeeId, firstName, lastName,
															// idNumber, address,
	private String firstName; // yearJoined, monthJoined, dayJoined, monthWorkingInYear, isForeigner, gender,
														// monthlySalary,
	private String lastName; // otherMonthlyIncome, annualDeductible, spouseName, spouseIdNumber, childNames,
														// dan childIdNumbers.
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(Employee data) {
		this.employeeId = data.employeeId;
		this.firstName = data.firstName;
		this.lastName = data.lastName;
		this.idNumber = data.idNumber;
		this.address = data.address;
		this.yearJoined = data.yearJoined;
		this.monthJoined = data.monthJoined;
		this.dayJoined = data.dayJoined;
		this.isForeigner = data.isForeigner;
		this.gender = data.gender;

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) { // Kelas ini memiliki beberapa metode untuk mengatur nilai atribut seperti
																						// setMonthlySalary(),
		switch (grade) {
			case 1:
				monthlySalary = 3000000;
				break;
			case 2:
				monthlySalary = 5000000;
				break;
			case 3:
				monthlySalary = 7000000;
				break;
			default:
				break;
		}

		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * 1.5);
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) { // Kelas ini juga memiliki metode getAnnualIncomeTax() yang
																								// mengembalikan pajak penghasilan tahunan yang harus dibayar oleh
																								// pegawai berdasarkan gaji bulanan, penghasilan tambahan, durasi kerja
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
