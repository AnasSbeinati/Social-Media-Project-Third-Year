package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.cloud.sql.jdbc.internal.Observers;

public abstract class Post {
	public String link;
	public String owner;
	public ArrayList<String> likers;
	public String content;
	public int sharNum;
	public ArrayList<String> CanSee;
	public ArrayList<HashTagObserver> hashs;
	public long ID;
	public HashMap<String, Privacy> privacy = new HashMap<>();

	public abstract Privacy setprivacy(String pID);

	public long getID() {
		return ID;
	}

	public void registerprivacy() {
		privacy.put("Privte", new privat());
		privacy.put("Public", new publik());
		privacy.put("Custom", new custom());
	}

	public void setID(long iD) {
		ID = iD;
	}

	public void Attach(HashTagObserver hash) {

		hashs.add(hash);
	}

	public void notifyAllhash() {
		for (int i = 0; i < hashs.size(); i++) {
			hashs.get(i).update();
		}
	}

	public abstract Post Create();

	public abstract void CreatePost(String link, String owner, String content,
			String feeling, long sharedpostId, String privacy);

	public boolean likePost(long postID, String Liker) {
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

	public void sharePost() {

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

	public abstract void save();

	public abstract boolean setSeen(long postID, String user);

	public abstract long getPostID();

	public abstract void setPostID(long postID);

}