package com.fidelity.sat.coin.challenge.core;

import java.util.Map;

import com.fidelity.sat.coin.challenge.enums.CoinCurrency;

/**
 * @author souadhik
 *
 */
@FunctionalInterface
public interface MinCoinCurrencyFactory {
	/**
	 * @param balance
	 * @param currency
	 * @return
	 */
	public Map<Integer, Integer> calculateMinCoinsByCurrency(int balance,CoinCurrency currency);
}
