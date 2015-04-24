package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PageSharedpost extends Post {

	long sharedpostId;
	ArrayList<String> seen;

	static {
		postFactory.getInstance().registerProduct("PageShared",
				new PageSharedpost());
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
		sample.SetClass(type, this);
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
		seen = new ArrayList<>();
		seen.add("0");
		setPostID(sharedpostId);
		setprivacy(privacy , CanSee , type);
		save();
		ChangeSharedNum(sharedpostId);
	}

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

	@Override
	public long GetOriginalPostID(Long sharedpostId) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getKey().getId() == (sharedpostId)) {
				long SharedID = Long.parseLong(entity.getProperty("SharedPost").toString());
				if (SharedID == 0)
					return sharedpostId;
				else
					return SharedID;

			}
		}
		return -1;
	}

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

	public Post Create() {
		// TODO Auto-generated method stub

		return new PageSharedpost();
	}


}
