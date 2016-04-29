package com.brownie.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.brownie.db.CoinDB;

public class Pieces2euros {

	private static final Logger logger = LogManager
			.getLogger(Pieces2euros.class);

	public static void main(String[] args) throws Exception {

		List<Coin> referenceCoins = new ArrayList<Coin>();

		CoinDB dataBase = new CoinDB();
		dataBase.createDB();
		dataBase.populateDB();
		dataBase.showReferenceCoins();

		referenceCoins = dataBase.getReferenceCoins();

		Coin singleCoin = new Coin(18, "ALLEMAGNE", "FE", 2013);

		referenceCoins.add(singleCoin);

		for (Coin coin : referenceCoins) {
			System.out.println(coin.toString());
		}

	}

}
