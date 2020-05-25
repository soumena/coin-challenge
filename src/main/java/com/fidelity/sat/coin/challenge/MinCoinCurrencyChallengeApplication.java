package com.fidelity.sat.coin.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyFactory;
import com.fidelity.sat.coin.challenge.enums.CoinCurrency;
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger;

/**
 * @author souadhik
 *
 */
@SpringBootApplication
public class MinCoinCurrencyChallengeApplication implements CommandLineRunner{

	/**
	 * Mincoin factory
	 */
	@Autowired
	private MinCoinCurrencyFactory minCoinsServiceFactory;
	
	/**
	 * Logger
	 */
	@Autowired
	private MinCoincurrencyLogger logger;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MinCoinCurrencyChallengeApplication.class, args);
	}

	/**
	 * CommandLine runner to execute the program from CMD
	 */
	@Override
	public void run(String... args) throws Exception {

		int balanceVal=287;
		CoinCurrency currency = CoinCurrency.USD;
		try {

			minCoinsServiceFactory.calculateMinCoinsByCurrency(balanceVal, currency);	
		} 
		catch (IllegalArgumentException iex) {
			logger.LogMessage(iex.getMessage());
		}
		catch (Exception ex) {
			logger.LogMessage(ex.getMessage());
		}
		
	}
}
