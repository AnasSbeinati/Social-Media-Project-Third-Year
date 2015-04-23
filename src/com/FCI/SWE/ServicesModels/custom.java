package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.bcel.internal.generic.ClassObserver;

public class custom extends Privacy {

	ArrayList<String> canSee;

	@Override
	public ArrayList<String> set() {

		if (post.getClass().getName() == "SharedPost") {
			canSee = canSeeOnShare();
		}
		return canSee;
	}

	public ArrayList<String> getCanSee(ArrayList<String> canSee) {
		this.canSee = canSee;
		return this.canSee;
	}

	public ArrayList<String> canSeeOnShare() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> oldCanSee = new ArrayList<String>();
		ArrayList<String> nowCanSee = new ArrayList<String>();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("SharedPost").toString()
					.equals((Long.toString(post.getPostID())))) {
				{
					oldCanSee = (ArrayList<String>) entity
							.getProperty("privacy");
					for (int i = 0; i < this.canSee.size(); i++) {
						if (oldCanSee.contains(this.canSee.get(i))) {
							nowCanSee.add(this.canSee.get(i));
						}
					}
				}
			}
		}

		return nowCanSee;
	}
}