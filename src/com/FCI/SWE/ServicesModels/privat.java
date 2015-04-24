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

public class privat extends Privacy {

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
