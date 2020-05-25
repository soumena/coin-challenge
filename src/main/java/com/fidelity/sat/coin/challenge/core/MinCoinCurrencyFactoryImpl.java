package com.fidelity.sat.coin.challenge.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fidelity.sat.coin.challenge.enums.CoinCurrency;
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger;
import com.fidelity.sat.coin.challenge.service.MinCoinCurrencyService;

/**
 * @author souadhik
 *
 */
@Service
public class MinCoinCurrencyFactoryImpl implements MinCoinCurrencyFactory{

	/**
	 * List of currency based implementations
	 */
	private List<MinCoinCurrencyService> minCoinsCurrencyServiceList;
	/**
	 * Logger
	 */
	private MinCoincurrencyLogger logger;

	/**
	 * @param minCoinsCurrencyService
	 * @param logger
	 */
	@Autowired
	public MinCoinCurrencyFactoryImpl(@Lazy List<MinCoinCurrencyService> minCoinsCurrencyService, MinCoincurrencyLogger logger) {
		super();
		this.minCoinsCurrencyServiceList = minCoinsCurrencyService;
		this.logger=logger;	
	}

	/**
	 * factory method encapsulates the currency service implementation
	 */
	@Override
	public Map<Integer, Integer> calculateMinCoinsByCurrency(int balance, CoinCurrency currency) {
		if(balance < 0) {
			
			String balanceErrorMessage = "Balance must be greater than 0 : " + balance;
			throw new IllegalArgumentException(balanceErrorMessage);
		}

		Optional<MinCoinCurrencyService> minCoinsCurrencyService = minCoinsCurrencyServiceList.stream()
				.filter(curr->curr.coinCurrency().equals(currency))
				.findFirst();

		if(minCoinsCurrencyService.isPresent()) {
			return minCoinsCurrencyService.get().calculate(balance);
		}
		// In real world, this would just exception and return null
		String correncyErrorMessage = "Not a valid currency type " + currency;
		throw new IllegalArgumentException(correncyErrorMessage);
	}
}
