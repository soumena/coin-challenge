package com.fidelity.sat.coin.challenge.logger;

/**
 * @author souadhik
 *
 */
@FunctionalInterface
public interface MinCoincurrencyLogger {
	/**
	 * @param message
	 */
	public void LogMessage(String message);
}
