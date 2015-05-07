package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
/**
 * <h1>private privacy class</h1>
 * <p>
 * This class will act as a model for private privacy, it will holds private privacy data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */
public class privat extends Privacy {

	
	/**
	 * this method to set privacy
	 * @return arraylist
	 */
	@Override
	public ArrayList<String> set() {

		// TODO Auto-generated method stub
		ArrayList<String> canSee = new ArrayList<>();
        canSee.add("private");
		
		return canSee;
	}

	@Override
	public void SetCanSee(ArrayList<String> canSee) {
		// TODO Auto-generated method stub
		
	}

	
}
