package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

/**
 * <h1>public privacy class</h1>
 * <p>
 * This class will act as a model for public privacy, it will holds public privacy data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */

public class publik extends Privacy {
	
	
	/**
	 * this method to set privacy
	 * @return arraylist
	 */

	@Override
	public ArrayList<String> set() {
	   // TODO Auto-generated method stub
		ArrayList<String> canSee = new ArrayList<>();
		canSee.add("public");
		return canSee;
		
	}

	@Override
	public void SetCanSee(ArrayList<String> canSee) {
		// TODO Auto-generated method stub
		
	}


}
