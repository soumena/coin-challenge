package com.fidelity.sat.coin.challenge.enums;

/**
 * @author souadhik
 * USD enum
 */
public enum UsdCoin
{
	PENNY(1),
	NICKEL(5),
	DIME(10),
	QUARTER(25),
	DOLLAR(100);

	final int denomValue;

	UsdCoin(int denomValue)
	{
		this.denomValue = denomValue;
	}
	public int denomValue()
	{
		return denomValue;
	}
	public static String getNameByCode(int code){
		for(UsdCoin e : UsdCoin.values()){
			if(code == e.denomValue) return e.name();
		}
		return null;
	}
}