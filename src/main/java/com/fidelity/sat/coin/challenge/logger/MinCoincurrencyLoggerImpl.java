package com.fidelity.sat.coin.challenge.logger;

import org.springframework.stereotype.Component;

/**
 * @author souadhik
 * Logger implemenation
 */
@Component
public class MinCoincurrencyLoggerImpl implements MinCoincurrencyLogger{	
	/**
	 *
	 */
	public void LogMessage(String message)
	{
		System.out.println(message);
	}
}
