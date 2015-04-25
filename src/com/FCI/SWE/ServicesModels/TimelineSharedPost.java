package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class TimelineSharedPost extends Post {

	long sharedpostId;
	String feeling;

	static {
		postFactory.getInstance().registerProduct("TimelineShared", new TimelineSharedPost());
	}

	public long getPostID() {
		return sharedpostId;
	}

	public void setPostID(long sharedpostId) {
		this.sharedpostId = sharedpostId;
	}

	@Override
	public Privacy setprivacy(String pID, ArrayList<String> CanSee, String type) {
		Privacy sample = privacy.get(pID);
		if (sample == null) {

			return null;
		}
		sample.SetCanSee(CanSee);
		sample.SetClass(type , this);
		this.CanSee = sample.set();

		return sample;
	}

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
		setFeeling(feeling);
		setPostID(sharedpostId);
		setprivacy(privacy , CanSee , type);
		save();
		ChangeSharedNum(sharedpostId);
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
		
		post.setProperty("Type", "TimelineShared");
		post.setProperty("link", link);
		post.setProperty("owner", owner);
		post.setProperty("like", likers);
		post.setProperty("content", content);
		post.setProperty("sheredNum", sharNum);
		post.setProperty("felling", feeling);
		post.setProperty("SharedPost", sharedpostId);
		post.setProperty("seen", null);
		post.setProperty("privacy", CanSee);
		datastore.put(post);

	}

	@Override
	public boolean setSeen(long postID, String user) {
		// TODO Auto-generated method stub
		return false;
	}

	public Post Create() {
		// TODO Auto-generated method stub
		
		
		return new TimelineSharedPost();
	}
	@Override
	public long GetOriginalPostID(Long sharedpostId) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (sharedpostId)) {
				long SharedID = Integer.parseInt(entity.getProperty(
						"SharedPost").toString());
				if (SharedID == 0)
					return sharedpostId;
				else
					return SharedID;

			}
		}
		return -1;
	}


	
	
	public void ChangeSharedNum(long sharedpostId) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (sharedpostId)) {
				int shrednumber = Integer.parseInt( entity.getProperty("sheredNum").toString());
				shrednumber += 1;
				entity.setProperty("sheredNum", shrednumber);
				datastore.put(entity);
				return;
			}
		}

	}

	@Override
	public void setHashTagID(String hashTagID) {
		// TODO Auto-generated method stub
		
	}

}
