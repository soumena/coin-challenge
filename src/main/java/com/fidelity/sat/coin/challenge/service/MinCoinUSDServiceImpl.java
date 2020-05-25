package com.fidelity.sat.coin.challenge.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyCore;
import com.fidelity.sat.coin.challenge.enums.CoinCurrency;
import com.fidelity.sat.coin.challenge.enums.UsdCoin;
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger;


/**
 * @author souadhik
 */
@Lazy
@Service
public class MinCoinUSDServiceImpl implements MinCoinCurrencyService{

	/**
	 * Currency core 
	 */
	private MinCoinCurrencyCore minCoinsCurrencyCore;
	
	/**
	 * Logger
	 */
	private MinCoincurrencyLogger logger;

	/**
	 * @param minCoinsCurrencyCore
	 * @param logger
	 */
	@Autowired
	public MinCoinUSDServiceImpl(MinCoinCurrencyCore minCoinsCurrencyCore,MinCoincurrencyLogger logger) {
		super();
		this.minCoinsCurrencyCore = minCoinsCurrencyCore;
		this.logger = logger;
	}
	
	/**
	 * Calculate 
	 */
	@Override
	public Map<Integer, Integer> calculate(int balance) {
		System.out.format("Balance : %s, CoinCurrency: %s\n", balance,coinCurrency().toString());
		Map<Integer, Integer> calculateMinCoins = minCoinsCurrencyCore.calculateMinCoins(balance, this.denominations());
		displayCalculationResult(calculateMinCoins); 

		return calculateMinCoins;
	}

	/**
	 * Coin Currency
	 */
	@Override
	public CoinCurrency coinCurrency() {
		return CoinCurrency.USD;
	}

	/**
	 * Denominations
	 */
	@Override
	public int[] denominations() {
		int[] usdDenominations = new int[] {
				UsdCoin.DOLLAR.denomValue(),
				UsdCoin.QUARTER.denomValue(), 
				UsdCoin.DIME.denomValue(), 
				UsdCoin.NICKEL.denomValue(), 
				UsdCoin.PENNY.denomValue()
		};
		return usdDenominations;
	}
	
	/**
	 * DisplayCalculationResult
	 */
	public void displayCalculationResult(Map<Integer, Integer> calculateMinCoins) {
		calculateMinCoins.forEach((denominator, noOfcoins) ->{
			logger.LogMessage("Denomination - " + UsdCoin.getNameByCode(denominator) + " Count - " + noOfcoins);

		});
	}
}
