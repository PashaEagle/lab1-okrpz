package io.crypto.beer.telegram.bot.transformer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class is for transforming the string-type representation of balance to
 * the double-type by cutting it from whole string.
 * 
 * @author pasha
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EosTransformer {

	/**
	 * This method transforms the representation of balance with square braces.
	 * 
	 * @param balance with amount of balance with braces, example: "["88.2215 EOS"]"
	 * @return double value of the balance, example: 88.2215
	 */
	public static final Double eosBalanceTransformWithBraces(String balance) {
		return Double.parseDouble(balance.substring(2, balance.length() - 6));
	}

	/**
	 * This method transforms the representation of balance without square braces.
	 * 
	 * @param balance with amount of balance without braces, example: "88.2215 EOS"
	 * @return double value of the balance, example: 88.2215
	 */
	public static final Double eosBalanceTransformWithoutBraces(String balance) {
		return Double.parseDouble(balance.substring(0, balance.length() - 4));
	}

}
