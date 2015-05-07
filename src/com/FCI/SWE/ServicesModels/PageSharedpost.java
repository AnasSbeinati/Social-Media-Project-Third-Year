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
 * <h1>Page Shared post class</h1>
 * <p>
 * This class will act as a model for Page Shared post, it will holds Page
 * Shared post data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */

public class PageSharedpost extends Post {

	long sharedpostId;
	ArrayList<String> seen;

	public long getPostID() {
		return sharedpostId;
	}

	public void setPostID(long sharedpostId) {
		this.sharedpostId = sharedpostId;
	}

	/**
	 * 
	 * this method to set privacy of an post
	 * 
	 * @author amal khaled
	 * @param privacy
	 *            who can see this post
	 * @param Cansee
	 *            who can see the post
	 * @param type
	 *            class name
	 * @return string
	 */

	public Privacy setprivacy(String privacy, ArrayList<String> CanSee,
			String type) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		this.privacy = (Privacy) Class.forName(
				"com.FCI.SWE.ServicesModels." + privacy).newInstance();
		this.privacy.SetCanSee(CanSee);
		this.privacy.SetClass(type, this);
		this.CanSee = this.privacy.set();

		return this.privacy;
	}

	/**
	 * 
	 * this method to create post
	 * 
	 * @author amal khaled
	 * @param Link
	 *            where the post will be
	 * @param Owner
	 *            who create post
	 * @param contenet
	 *            content of the post
	 * @param felling
	 *            user felling
	 * @param sharedpostId
	 *            if it sharedpost this will the original post id
	 * @param privacy
	 *            privacy of the post
	 * @param cansee
	 *            who can see post on custom
	 * @param type
	 *            class type
	 * @return string
	 */

	@Override
	public void CreatePost(String link, String owner, String content,
			String feeling, long sharedpostId, String privacy,
			ArrayList<String> CanSee, String type) {
		setLink(link);
		setOwner(owner);
		setContent(content);
		likers = new ArrayList<String>();
		likers.add("0");
		sharNum = 0;
		seen = new ArrayList<>();
		seen.add("0");
		setPostID(sharedpostId);
		try {
			setprivacy(privacy, CanSee, type);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save();
		ChangeSharedNum(sharedpostId);
	}

	/**
	 * 
	 * this method to save post in database
	 * 
	 * @author amal khaled
	 * 
	 * @return string
	 */

	@Override
	public void save() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity post = new Entity("Posts", list.size() + 2);

		post.setProperty("Type", "PageShared");
		post.setProperty("link", link);
		post.setProperty("owner", owner);
		post.setProperty("like", likers);
		post.setProperty("content", content);
		post.setProperty("sheredNum", sharNum);
		post.setProperty("felling", null);
		post.setProperty("SharedPost", sharedpostId);
		post.setProperty("seen", seen);
		post.setProperty("privacy", CanSee);
		datastore.put(post);

	}

	/**
	 * 
	 * 
	 * this method to get the original post id
	 * 
	 * @author amal khaled
	 * 
	 * @param sharedpostid
	 *            shared post id
	 * @return string
	 */

	public void ChangeSharedNum(long sharedpostId) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (sharedpostId)) {
				int shrednumber = Integer.parseInt(entity.getProperty(
						"sheredNum").toString());
				shrednumber += 1;
				entity.setProperty("sheredNum", shrednumber);
				datastore.put(entity);
				return;
			}
		}

	}

	/**
	 * 
	 * 
	 * this method to get the original post id
	 * 
	 * @author amal khaled
	 * 
	 * @param sharedpostid
	 *            shared post id
	 * @return string
	 */

	@Override
	public long GetOriginalPostID(Long sharedpostId) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (sharedpostId)) {
				long SharedID = Long.parseLong(entity.getProperty("SharedPost")
						.toString());
				if (SharedID == 0)
					return sharedpostId;
				else
					return SharedID;

			}
		}
		return -1;
	}

	/**
	 * 
	 * this method to see post
	 * 
	 * @author amal khaled
	 * @param postID
	 *            this id ID of the post that user want to ssee
	 * @param user
	 *            who see post
	 * @return string
	 */
	@Override
	public boolean setSeen(long postID, String user) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (postID)) {
				seen = (ArrayList<String>) entity.getProperty("seen");

				for (int i = 0; i < seen.size(); i++) {
					if (!seen.contains(user)) {
						seen.add(user);
						entity.setProperty("seen", seen);
						datastore.put(entity);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void setHashTagID(String hashTagID) {
		// TODO Auto-generated method stub

	}

}
