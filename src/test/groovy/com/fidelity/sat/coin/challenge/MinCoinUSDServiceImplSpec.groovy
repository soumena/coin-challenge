package com.fidelity.sat.coin.challenge

import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyCore
import com.fidelity.sat.coin.challenge.logger.MinCoincurrencyLogger
import com.fidelity.sat.coin.challenge.service.MinCoinUSDServiceImpl

import spock.lang.Specification

@ContextConfiguration(classes = MinCoinCurrencyChallengeApplication.class)
@Category(SpockGroovyTest.class)
class MinCoinUSDServiceImplSpec extends Specification{
	
	
	/**
	 * Injected MinCoinCurrencyCore for invoking the 
	 * min coin challenge algorithm 
	 */
	@Autowired
	private MinCoinCurrencyCore minCoinsCurrencyCore	
	/**
	 * MinCoinUSDServiceImpl class object to be shared by all tests
	 */
	private MinCoinUSDServiceImpl minCoinUSDServiceImpl
	/**
	 * Logger 
	 */
	@MockBean
	private MinCoincurrencyLogger minCoincurrencyLogger;

									 
	/**
	 * Initialize minCoinUSDServiceImpl class
	 */
	def setup() {
		minCoinUSDServiceImpl = new MinCoinUSDServiceImpl(minCoinsCurrencyCore,minCoincurrencyLogger)
	}
	
	
	def "Given the balance 87, the USD function will return 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
			"The balance is 87"
			def balanceVal=87
		and:
		 	"We defined expected minimum USD denominations for the given balance"
			 def expectedDenominations = [25:3, 10:1, 1:2]
			 
		when:
			"We invoke the calculate method on MinCoinUSDServiceImpl class to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinUSDServiceImpl.calculate(balanceVal)

		then:
			"We expect the result of the function to be equal to expected denominations"
			expectedDenominations==actualDenominations
	}

	
	def "Given the balance 287, the USD function will return 2 Dollars, 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
			"The balance is 287"
			def balanceVal=287
		and:
			 "We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [100:2, 25:3, 10:1, 1:2]				 
			 
		when:
			"We invoke the calculate method on MinCoinUSDServiceImpl class to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinUSDServiceImpl.calculate(balanceVal)

		then:
			 "We expect the result of the function to be equal to expected denominations"
			expectedDenominations==actualDenominations
	}
	
	//Negative test
	def "Given invalid balance value the USD function returns gracefully with an empty list"(){
		given:
			"The balance is -1"
			def balanceVal=-1
		and:
			"We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [100:2, 25:3, 10:1, 1:2]
			 
		when:
			"We invoke the calculate method on MinCoinUSDServiceImpl class to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinUSDServiceImpl.calculate(balanceVal)

		then:
			"We expect the function to return gracefully with an empty list"
			expectedDenominations != actualDenominations
			actualDenominations.size()==0
	}
	
}
