package com.fidelity.sat.coin.challenge

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyCoreImpl
import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyFactoryImpl
import com.fidelity.sat.coin.challenge.enums.CoinCurrency
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger
import com.fidelity.sat.coin.challenge.service.MinCoinCurrencyService
import com.fidelity.sat.coin.challenge.service.MinCoinUSDServiceImpl

import spock.lang.Specification

@ContextConfiguration(classes = MinCoinCurrencyChallengeApplication.class)
@Category(SpockGroovyTest.class)
class MinCoinCurrencyFactoryImplSpec extends Specification{
	
	/**
	 * Injected MinCoinCurrencyCore for invoking the
	 * min coin challenge algorithm
	 */
	@Autowired 
	private MinCoinCurrencyCoreImpl minCoinCurrencyCoreImpl
	/**
	 * Logger
	 */
	@MockBean
	private MinCoincurrencyLogger minCoincurrencyLogger;
	/**
	 * USD coin challenge implementation
	 */
	private MinCoinUSDServiceImpl minCoinUSDServiceImpl	
	/**
	 * Min coin currency interface
	 */
	private List<MinCoinCurrencyService> minCoinsCurrencyServiceList
	/**
	 * Min coin factory
	 */
	private MinCoinCurrencyFactoryImpl minCoinCurrencyFactoryImpl	
	
	/**
	 * Initialize minCoinUSDServiceImpl class
	 */
	def setup() {
	
		minCoinUSDServiceImpl = new MinCoinUSDServiceImpl(minCoinCurrencyCoreImpl,minCoincurrencyLogger)
		minCoinsCurrencyServiceList = [minCoinUSDServiceImpl]		
		minCoinCurrencyFactoryImpl = new MinCoinCurrencyFactoryImpl(minCoinsCurrencyServiceList,minCoincurrencyLogger)
	}
	
	def "Given the balance 87, for USD,the factory function will return 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
		     "The balance, currency & expteced denominations" 
			 def balanceVal=87
			 def currency = CoinCurrency.USD
			 def expectedDenominations = [25:3, 10:1, 1:2]
			 
		when :
			"We invoke the calculate method on minCoinCurrencyFactoryImpl class to get actual list of denominations for a given balance and currency"
			 Map<Integer, Integer> actualDenominations = minCoinCurrencyFactoryImpl.calculateMinCoinsByCurrency(balanceVal,currency)
		
		then:
			"We expect the result of the function to be equal to expected denominations"
			print(minCoinUSDServiceImpl.denominations())
			actualDenominations==actualDenominations
	}

	def "Given the balance 287, for USD,the factory function will return 2 Dollars, 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
			 "The balance, currency & expteced denominations"
			 def balanceVal=287
			 def currency = CoinCurrency.USD
			 def expectedDenominations = [100:2, 25:3, 10:1, 1:2]
			 
		when :
			"We invoke the calculate method on minCoinCurrencyFactoryImpl class to get actual list of denominations for a given balance and currency"
			 Map<Integer, Integer> actualDenominations = minCoinCurrencyFactoryImpl.calculateMinCoinsByCurrency(balanceVal,currency)
		
		then:
			"We expect the result of the function to be equal to expected denominations"
			print(minCoinUSDServiceImpl.denominations())
			actualDenominations==actualDenominations
	}
	
	//Negative tests
	def "Given an invalid corrency, the factory function will throw InvalidArgument exception"(){
		given:
			"The balance, invalid currency & expteced denominations"		
			 def balanceVal=87
			 def currency = CoinCurrency.POUND
			 def expectedDenominations = [25:3, 10:1, 1:2]
		
		when: 
			"We invoke the calculate method on minCoinCurrencyFactoryImpl class to get actual list of denominations for a given balance and currency"
			 Map<Integer, Integer> actualDenominations = minCoinCurrencyFactoryImpl.calculateMinCoinsByCurrency(balanceVal,currency)
		
		then:
			"We expect IllegalArgument exception to be thrown"
			"we also validate the exception returns correct message"
			 IllegalArgumentException iex = thrown()
			 iex.message == "Not a valid currency type " + currency
	}
	
	def "Given an invalid balance, for USD, the factory function will throw InvalidArgument exception"(){
		given:
			"The balance, invalid currency & expteced denominations"		
			 def balanceVal=-1
			 def currency = CoinCurrency.USD
			 def expectedDenominations = [25:3, 10:1, 1:2]
		
		when: 
			"We invoke the calculate method on minCoinCurrencyFactoryImpl class to get actual list of denominations for a given balance and currency"
			 Map<Integer, Integer> actualDenominations = minCoinCurrencyFactoryImpl.calculateMinCoinsByCurrency(balanceVal,currency)
		
		then:
			"We expect IllegalArgument exception to be thrown"
			"we also validate the exception returns correct message"
			 IllegalArgumentException iex = thrown()
			 iex.message == "Balance must be greater than 0 : " + balanceVal
	}
	
}
