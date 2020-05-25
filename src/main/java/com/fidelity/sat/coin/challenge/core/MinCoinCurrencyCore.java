package com.fidelity.sat.coin.challenge.core;

import java.util.Map;

/**
 * @author souadhik
 * Functional interface for the min coin alogorithm
 */
@FunctionalInterface
public interface MinCoinCurrencyCore {
	/**
	 * @param balance
	 * @param denominations
	 * @return
	 */
	public Map<Integer, Integer> calculateMinCoins(int balance, int[] denominations);	
	
}
