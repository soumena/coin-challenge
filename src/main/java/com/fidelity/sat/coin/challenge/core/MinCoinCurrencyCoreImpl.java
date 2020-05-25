package com.fidelity.sat.coin.challenge.core;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

/**
 * @author souadhik
 *
 */
@Service
public class MinCoinCurrencyCoreImpl implements MinCoinCurrencyCore{

	/**
	 *	This class has just one responsibility, i.e calculate the minimum number
	 *  of denominations for a given balance 
	 */
	public Map<Integer, Integer> calculateMinCoins(int balance, int[] denominations) {
		int remainingBalance = balance;
		int noOfCoins=0;

		Map<Integer, Integer> minCoinCalculationResult 
		= new TreeMap<Integer, Integer>(Collections.reverseOrder());

		if(balance > 0) {
			for(int denomination:denominations) {
				int currentDinominator = denomination;

				if(remainingBalance > currentDinominator) {
					noOfCoins= remainingBalance/currentDinominator;
					minCoinCalculationResult.putIfAbsent(currentDinominator, noOfCoins);

					//remainingBalance = remainingBalance-currentDinominator*noOfCoins;
					remainingBalance = remainingBalance % currentDinominator;
				}
			}
		}
		return minCoinCalculationResult;
	}	

}