package br.com.fiap.precoauto.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

	public static Double currencyToDouble(String value) {
		String cleanString = value.replaceAll("[R|$|,|.]", "");
		try {
			BigDecimal parsed = new BigDecimal(cleanString).setScale(2,BigDecimal.ROUND_FLOOR);                
			return parsed.doubleValue();
		} catch (Exception e) {
			return null;
		}
	}

	public static String doubleToCurrency(Double value) {

		return NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(value);

	}

}
