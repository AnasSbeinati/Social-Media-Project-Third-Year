package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Time Line class</h1>
 * <p>
 * This class is user Time line
 * </p>
 *
 * @author Anas sbeinati
 * @version 1.0
 * @since 2014-04-22
 */

public class UserTimeLine extends TimeLine {
	private String owner;

	public ArrayList<Post> get() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : list) {
			if (entity.getProperty("owner").equals(owner)) {
				TimelinePost post = new TimelinePost();
				post.setContent(entity.getProperty("content").toString());
				// post.setID(iD);
				post.setLink(entity.getProperty("link").toString());
				post.setOwner(owner);
				post.setprivacy(entity.getProperty("privacy").toString());
				String temp[] = entity.getProperty("like").toString()
						.split(" ");
				for (String string : temp) {
					post.likers.add(string);
				}
				post.sharNum = Integer.parseInt(entity.getProperty("sheredNum")
						.toString());
				posts.add(post);
			}
		}
		return posts;
	}

	public UserTimeLine(String owner) {
		super();
		this.owner = owner;
	}
}
