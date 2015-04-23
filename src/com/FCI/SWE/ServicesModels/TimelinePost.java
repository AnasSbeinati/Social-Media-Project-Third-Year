package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TimelinePost extends Post {

	String feeling;

	static {
		postFactory.getInstance().registerProduct("Timeline",
				new TimelinePost());
	}

	@Override
	public Privacy setprivacy(String pID ) {
		Privacy sample = privacy.get(pID);
		if (sample == null) {

			return null;
		}
		CanSee = sample.set();
		return sample;
	}

	@Override
	public void CreatePost(String link, String owner, String content,
			String feeling, long sharedpostId , String privacy) {
		setLink(link);
		setOwner(owner);
		setContent(content);
		likers = new ArrayList<String>();
		sharNum = 0;
		setFeeling(feeling);
		setprivacy(privacy);
		save();

	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

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

	public Post Create() {
		// TODO Auto-generated method stub
		return new TimelinePost();
	}

}
