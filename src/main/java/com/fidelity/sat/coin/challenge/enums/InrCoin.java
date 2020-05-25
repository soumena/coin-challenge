package com.fidelity.sat.coin.challenge.enums;

/**
 * @author souadhik
 * INR enum
 */
public enum InrCoin
{
	ONEPAISE(1),
	TENPAISE(10),
	TWENTYFIVEPAISE(25),
	FIFTYPAISE(50),
	ONERUPEE(100),
	TWORUPEE(200);

	private final int denomValue;

	InrCoin(int denomValue)
	{
		this.denomValue = denomValue;
	}
	public int denomValue()
	{
		return denomValue;
	}
	public static String getNameByCode(int code){
		for(InrCoin e : InrCoin.values()){
			if(code == e.denomValue) return e.name();
		}
		return null;
	}
}