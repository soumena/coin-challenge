# Coin Challenge Application with tests using Spock Framework

Coin challenge app is a Java Spring project that takes in a balance and converts it into denominations (e.g - bills/coins for USD).
The purpose of the Coin challenge algorithm is to convert the input balance into the least number of denominations for a given currency type(USD, INR, EURO etc.).

## Requirements

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the Coin Challenge application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the following commands inside the projects /src directory.

- Build the project using maven clean install, this would build as well as run all the unit tests

```bash
mvn clean install
```

![mvn clean install](images/coin_challenge_mvn_clean_install.png?raw=true)

- One the app builds successfully, use spring boot run command to run the app

```bash
mvn spring-boot:run
```

![spring-boot:run](images/coin_challenge_mvn_springboot_run.png?raw=true)

- On successful run, you should see the output

![Spring execution output](images/coin_challenge_spring_boot_run_output.png?raw=true)

## Run unit tests

Use the following command to run unit tests

```bash
mvn test
```

## Project Structure

![Porject Structure](images/coin_challenge_project_structure.png?raw=true)

- ### **Min Coin challenge algorithm implementation**

```java
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

					remainingBalance = remainingBalance % currentDinominator;
				}
			}
		}
		return minCoinCalculationResult;
	}
}
```

- ### **Currency specific min coin implementation** (the screenshot below is for USD)

```java
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
```

## Spock Groovy tests

![Spock tests](images/coin_challenge_spock_tests.png?raw=true)

- ### **Unit test for Coin Challenge algorithm**

```groovy
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
}
```
