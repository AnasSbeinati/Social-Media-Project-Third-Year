package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
/**
 * <h1>privacy class</h1>
 * <p>
 * This class will act as a model for privacy, it will holds privacy data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */

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
