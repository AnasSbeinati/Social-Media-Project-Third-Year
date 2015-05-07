package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import javax.ws.rs.core.NewCookie;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.bcel.internal.generic.ClassObserver;

/**
 * <h1>custom privacy class</h1>
 * <p>
 * This class will act as a model for custom privacy, it will holds custom
 * privacy data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */

public class custom extends Privacy {

	ArrayList<String> canSee;

	/**
	 * this method to set privacy
	 * 
	 * @return arraylist
	 */

	@Override
	public ArrayList<String> set() {

		if (PostType.equals("PageSharedpost")
				|| PostType.equals("TimelineShared")) {
			this.canSee = canSeeOnShare();

		}
		return canSee;
	}

	/**
	 * this method to set who can see this post
	 * 
	 * @param canSee
	 *            how can see this post
	 * @return arraylist
	 */

	@Override
	public void SetCanSee(ArrayList<String> canSee) {
		this.canSee = canSee;
		// return this.canSee;
	}

	/**
	 * this method to set who can see this post in case of sharing
	 * 
	 * @return arraylist
	 */
	public ArrayList<String> canSeeOnShare() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<String> oldCanSee = new ArrayList<String>();
		ArrayList<String> nowCanSee = new ArrayList<String>();
		Query gaeQuery = new Query("Posts");

		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == post.getPostID()) {
				{
					oldCanSee = (ArrayList<String>) entity
							.getProperty("privacy");
					if (oldCanSee.contains("Privte")) {
						UserEntity user = new UserEntity(entity.getProperty(
								"owner").toString());
						if (entity.getProperty("Type").equals("TimelineShared")) {
							oldCanSee = user.showallfriends(entity.getProperty(
									"owner").toString());
						} else if (entity.getProperty("Type").equals(
								"PageSharedpost")) {
							// oldCanSee =
							// user.showallfriends(entity.getProperty("owner").toString());
							// change all friends to all likers
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