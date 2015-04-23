package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

public class publik extends Privacy {

	@Override
	public ArrayList<String> set() {
	   // TODO Auto-generated method stub
		ArrayList<String> canSee = new ArrayList<>();
		canSee.add("public");
		return canSee;
		
	} 

}
