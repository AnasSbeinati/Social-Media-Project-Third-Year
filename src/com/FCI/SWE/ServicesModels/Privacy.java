package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

public abstract class Privacy {
	String PostType;
	Post post ;
	public abstract ArrayList<String> set();
	public abstract void SetCanSee(ArrayList<String> canSee) ;
	public void SetClass(String PostType  , Post post){
		this.PostType = PostType;
		this.post = post;
		
	}

}
