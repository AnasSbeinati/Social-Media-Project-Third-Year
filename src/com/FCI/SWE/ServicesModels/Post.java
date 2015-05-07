package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.cloud.sql.jdbc.internal.Observers;

/**
 * <h1>post class</h1>
 * <p>
 * This class will act as a model for post, it will holds post data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */



public abstract class Post {
	public String link;
	public String owner;
	public ArrayList<String> likers;
	public String content;
	public int sharNum;
	public ArrayList<String> CanSee;
	public ArrayList<String> hashs;
	public long ID;
	public Privacy privacy;

	public long getID() {
		return ID;
	}

	public Privacy getprivacy() {

		return privacy;

	}

	public void setID(long iD) {
		ID = iD;
	}

	// public void Attach(HashTagObserver hash) {
	// hashs.add(hash);
	// }

	public void notifyAllhash() {
	//	for (int i = 0; i < hashs.size(); i++) {
		//	new HashTagTimeLine().update(this, hashs.get(i));
		//}
	}

	/**
	 * 
	 * this method to like post
	 * 
	 * @author amal khaled
	 * @param postID
	 *            where the post will be
	 * @param liker
	 *            who likes post
	 * @return boolen
	 */

	public boolean likePost(long postID, String Liker) {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for (Entity entity : pq.asIterable()) {

			if (entity.getKey().getId() == (postID)) {

				likers = (ArrayList<String>) entity.getProperty("like");
				for (int i = 0; i < likers.size(); i++) {

					if (!likers.contains(Liker)) {
						likers.add(Liker);
						entity.setProperty("like", likers);
						datastore.put(entity);

						return true;
					}
				}

			}

		}

		return false;

	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public abstract void CreatePost(String link, String owner, String content,
			String feeling, long sharedpostId, String privacy,
			ArrayList<String> CanSee, String type)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException;

	public abstract long GetOriginalPostID(Long sharedpostId);

	public abstract void save();

	public abstract boolean setSeen(long postID, String user);

	public abstract long getPostID();

	public abstract void setPostID(long postID);

	public abstract void setHashTagID(String hashTagID);

}
