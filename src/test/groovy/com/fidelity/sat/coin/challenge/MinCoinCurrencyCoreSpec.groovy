package com.fidelity.sat.coin.challenge

import org.junit.experimental.categories.Category

import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyCoreImpl
import com.fidelity.sat.coin.challenge.enums.CoinCurrency
import com.fidelity.sat.coin.challenge.enums.EuroCoin
import com.fidelity.sat.coin.challenge.enums.InrCoin
import com.fidelity.sat.coin.challenge.enums.UsdCoin
import com.fidelity.sat.coin.challenge.core.MinCoinCurrencyFactoryImpl
import spock.lang.Specification

@Category(SpockGroovyTest.class)
class MinCoinCurrencyCoreSpec extends Specification{

	MinCoinCurrencyCoreImpl minCoinsCurrencyCoreImpl
	
	def setup() {
		minCoinsCurrencyCoreImpl = new MinCoinCurrencyCoreImpl()
	}
	
	def "Given the balance 87, for USD, the function will return 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
			"The balance is 87"
			def balanceVal=87
		and:
			"We defined a list of USD denominations"
			int[] usdDenominations = [
										UsdCoin.DOLLAR.denomValue(),
										UsdCoin.QUARTER.denomValue(),
										UsdCoin.DIME.denomValue(),
										UsdCoin.NICKEL.denomValue(),
										UsdCoin.PENNY.denomValue()
									 ]
		and: 
		 	"We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [25:3, 10:1, 1:2]
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, usdDenominations)
			
		then:
			 "We expect the result of the function to be equal to expected denominations"
			 expectedDenominations == actualDenominations
		
	}
	
	def "Given the balance 287,for USD, the function will return 2 Dollars, 3 Quarters, 1 Dime, 2 Pennies"(){
		given:
			"The balance is 287"
			def balanceVal=287
	
		and:
			"We defined a list of USD denominations"
			int[] usdDenominations = [
										UsdCoin.DOLLAR.denomValue(),
										UsdCoin.QUARTER.denomValue(),
										UsdCoin.DIME.denomValue(),
										UsdCoin.NICKEL.denomValue(),
										UsdCoin.PENNY.denomValue()
									 ]
		and:
			 "We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [100:2, 25:3, 10:1, 1:2]				 
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, usdDenominations)
			
		then:
			 "We expect the result of the function to be equal to expected denominations"
			 expectedDenominations == actualDenominations
		
	}	
	
	def "Given the balance 287, for INR,the function will return 1 two rupee coin, 1 fifty paise coin"(){
		given:
			"The balance is 287"
			def balanceVal=287
		and:
			"We defined a list of INR denominations"
				int[] inrDenominations = [
										InrCoin.TWORUPEE.denomValue(),
										InrCoin.ONERUPEE.denomValue(), 
										InrCoin.FIFTYPAISE.denomValue(), 
										InrCoin.TWENTYFIVEPAISE.denomValue(), 
										InrCoin.TENPAISE.denomValue(),
										InrCoin.ONEPAISE.denomValue()
									 ]
		 and:
		 	"We defined expected minimum INR denominations for the given balance"
			def expectedDenominations = [200:1, 50:1, 25:1, 10:1, 1:2]
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, inrDenominations)
			
		then:
			 "We expect the result of the function to be equal to expected denominations"
			 expectedDenominations == actualDenominations
		
	}
	
	def "Given the balance 287, for EURO, the function will return 1 two euro coin, 1 50c coin, 1 20c coin, 1 10c coin, 1 5c coin, and 2 1c coins"(){
		given:
			"The balance is 287"
			def balanceVal=287	
		and:
			"We defined a list of EUR denominations"
			int[] eurDenominations = [
										EuroCoin.TWOEURO.denomValue(),
										EuroCoin.ONEEURO.denomValue(), 
										EuroCoin.FIFTYCENTS.denomValue(), 
										EuroCoin.TWENRTCENTS.denomValue(), 
										EuroCoin.TENCENTS.denomValue(),
										EuroCoin.FIVECENTS.denomValue(),
										EuroCoin.TWOCENTS.denomValue(),
										EuroCoin.ONECENT.denomValue()
									 ]
		and:
		 	"We defined expected minimum EUR denominations for the given balance"
			def expectedDenominations = [200:1, 50:1, 20:1, 10:1,5:1, 1:2]
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, eurDenominations)
			
		then:
			 "We expect the result of the function to be equal to expected denominations"
			 expectedDenominations == actualDenominations
	}

	
	//Negative tests
	def "Given the balance 87, with no currency denominations provided, the function will return gracefully with an empty list"(){
		given:
			"The balance is 87"
			def balanceVal=87
		and:
			"We provided an empty list of USD denominations"
			int[] usdDenominations = []
		and:
			 "We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [25:3, 10:1, 1:2]
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, usdDenominations)
			
		then:
			"We expect the function to return gracefully with an empty list"
			 expectedDenominations != actualDenominations
			 actualDenominations.size()==0
		
	}
	
	def "Given an invalid balance, for USD, the function will return gracefully with an empty list"(){
		given:
			"An invalid balance"
			def balanceVal=-1
		and:
			"We defined a list of USD denominations"
			int[] usdDenominations = [
										UsdCoin.DOLLAR.denomValue(),
										UsdCoin.QUARTER.denomValue(),
										UsdCoin.DIME.denomValue(),
										UsdCoin.NICKEL.denomValue(),
										UsdCoin.PENNY.denomValue()
									 ]
		and:
			 "We defined expected minimum USD denominations for the given balance"
			def expectedDenominations = [25:3, 10:1, 1:2]
		
		when:
			"We invoke the calculate method to get actual list of denominations for a given balance and currency"
			Map<Integer, Integer> actualDenominations = minCoinsCurrencyCoreImpl.calculateMinCoins(balanceVal, usdDenominations)
			
		then:
			"We expect the function to return gracefully with an empty list"
			 expectedDenominations != actualDenominations
			 actualDenominations.size()==0
	}

}
