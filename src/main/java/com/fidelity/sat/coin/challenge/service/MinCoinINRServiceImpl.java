package com.fidelity.sat.coin.challenge.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyCore;
import com.fidelity.sat.coin.challenge.enums.CoinCurrency;
import com.fidelity.sat.coin.challenge.enums.InrCoin;
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger;

/**
 * @author souadhik
 *
 */
@Lazy
@Service
public class MinCoinINRServiceImpl implements MinCoinCurrencyService{

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
	public MinCoinINRServiceImpl(MinCoinCurrencyCore minCoinsCurrencyCore,MinCoincurrencyLogger logger) {
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
		return CoinCurrency.INR;
	}

	/**
	 * Denominations
	 */
	@Override
	public int[] denominations() {
		return new int[] {
				InrCoin.TWORUPEE.denomValue(),
				InrCoin.ONERUPEE.denomValue(), 
				InrCoin.FIFTYPAISE.denomValue(), 
				InrCoin.TWENTYFIVEPAISE.denomValue(), 
				InrCoin.TENPAISE.denomValue(),
				InrCoin.ONEPAISE.denomValue()
		};
	}

	/**
	 * DisplayCalculationResult
	 */
	@Override
	public void displayCalculationResult(Map<Integer, Integer> calculateMinCoins) {
		calculateMinCoins.forEach((denominator, noOfcoins) ->{
			logger.LogMessage("Denomination - " + InrCoin.getNameByCode(denominator) + " Count - " + noOfcoins);

		});
	} 
}
