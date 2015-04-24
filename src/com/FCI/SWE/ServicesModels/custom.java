package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import javax.ws.rs.core.NewCookie;

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

		if (PostType.equals("PageShared")
				|| PostType.equals("TimelineShared")) {
			this.canSee = canSeeOnShare();
		
		}
		return canSee;
	}

	@Override
	public void SetCanSee(ArrayList<String> canSee) {
		this.canSee = canSee;
		// return this.canSee;
	}

	public ArrayList<String> canSeeOnShare() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> oldCanSee = new ArrayList<String>();
		ArrayList<String> nowCanSee = new ArrayList<String>();
		Query gaeQuery = new Query("Posts");
		
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId()== post.getPostID()) {
				{
					oldCanSee = (ArrayList<String>) entity
							.getProperty("privacy");
					if (oldCanSee.contains("Privte")) {
						UserEntity user = new UserEntity(entity.getProperty(
								"owner").toString());
						if (entity.getProperty("Type").equals("Timeline")) {
							//oldCanSee = user.GetFriends(entity.getProperty(
									//"owner").toString());
						}
					} else if (oldCanSee.contains("Public")) {
						nowCanSee = this.canSee;
						return nowCanSee;
					}

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