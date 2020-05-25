package com.fidelity.sat.coin.challenge.enums;

/**
 * @author souadhik
 * EURO enum
 */
public enum EuroCoin
{
	ONECENT(1),
	TWOCENTS(2),
	FIVECENTS(5),
	TENCENTS(10),
	TWENRTCENTS(20),
	FIFTYCENTS(50),
	ONEEURO(100),
	TWOEURO(200);

	final int denomValue;

	EuroCoin(int denomValue)
	{
		this.denomValue = denomValue;
	}
	public int denomValue()
	{
		return denomValue;
	}
	public static String getNameByCode(int code){
		for(EuroCoin e : EuroCoin.values()){
			if(code == e.denomValue) return e.name();
		}
		return null;
	}
}