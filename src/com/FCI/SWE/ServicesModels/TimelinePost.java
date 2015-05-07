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
 * <h1>timeline post class</h1>
 * <p>
 * This class will act as a model for timeline post, it will holds timeline post
 * data
 * </p>
 *
 * @author amal khaled
 * @version 1.c
 * @since 2015-4-25
 */

public class TimelinePost extends Post {

	String feeling;

	/**
	 * 
	 * this method to set privacy of an post
	 * 
	 * @author amal khaled
	 * @param privacy
	 *            who can see this post
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
		sharNum = 0;
		setFeeling(feeling);
		try {
			setprivacy(privacy, CanSee, type);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save();

	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	/**
	 * 
	 * this method to save post in database
	 * 
	 * @author amal khaled
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

		post.setProperty("link", link);
		post.setProperty("owner", owner);
		post.setProperty("like", likers);
		post.setProperty("content", content);
		post.setProperty("sheredNum", sharNum);
		post.setProperty("felling", feeling);
		post.setProperty("privacy", CanSee);
		datastore.put(post);
	}

	@Override
	public long getPostID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPostID(long postID) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean setSeen(long postID, String user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long GetOriginalPostID(Long sharedpostId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHashTagID(String hashTagID) {

	}

}
