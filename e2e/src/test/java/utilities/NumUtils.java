package utilities;

public class NumUtils {

	public static Double dollarsToNumeric(String dollarsValue) {
		String withoutDollar = dollarsValue.replace("$", "").replace(",", "");
		Double numeric = new Double(withoutDollar);
		return numeric;
	}

}
