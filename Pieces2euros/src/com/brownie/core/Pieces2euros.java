package com.brownie.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.brownie.db.SQLiteJDBC;

public class Pieces2euros {

	private static final Logger logger = LogManager
			.getLogger(Pieces2euros.class);

	public Pieces2euros() throws Exception {

		SQLiteJDBC WebStore = new SQLiteJDBC();
//		WebStore.createDB();
//		WebStore.populateDB();
		WebStore.showItems();
		logger.info("Test");

	}

	public static void main(String[] args) throws Exception {

		new Pieces2euros();

	}

}
