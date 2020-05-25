package com.fidelity.sat.coin.challenge.service;

import com.fidelity.sat.coin.challenge.enums.EuroCoin;

public class MinCoinEURServiceImpl {
	int[] euroDenominations = new int[] {
			EuroCoin.TWOEURO.denomValue(),
			EuroCoin.ONEEURO.denomValue(), 
			EuroCoin.FIFTYCENTS.denomValue(), 
			EuroCoin.TWENRTCENTS.denomValue(), 
			EuroCoin.TENCENTS.denomValue(),
			EuroCoin.FIVECENTS.denomValue(),
			EuroCoin.TWOCENTS.denomValue(),
			EuroCoin.ONECENT.denomValue()
	}; 

}
