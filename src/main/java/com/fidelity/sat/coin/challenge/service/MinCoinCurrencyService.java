package com.fidelity.sat.coin.challenge.service;

import java.util.Map;

import com.fidelity.sat.coin.challenge.enums.CoinCurrency;

/**
 * @author souadhik
 *
 */
public interface MinCoinCurrencyService {
	/**
	 * @param balance
	 * @return
	 */
	public Map<Integer, Integer> calculate(int balance);
	/**
	 * @return
	 */
	public CoinCurrency coinCurrency();
	
	/**
	 * @return
	 */
	public int[] denominations();
	
	/**
	 * @param calculateMinCoins
	 */
	public void displayCalculationResult(Map<Integer, Integer> calculateMinCoins);

}
