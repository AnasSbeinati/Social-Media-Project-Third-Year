package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

/**
 * <h1>HashTagTimeLine class</h1>
 * <p>
 * This class is HashTagTimeLine Time line
 * </p>
 *
 * @author Anas sbeinati
 * @version 1.0
 * @since 2014-04-22
 */
public class HashTagTimeLine extends TimeLine implements HashTagObserver {
	private String name;

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Post> get() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("HashTag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Query gaeQuery1 = new Query("Post");
		PreparedQuery pq1 = datastore.prepare(gaeQuery);
		List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
		int count = 0;
		for (Entity entity : list) {
			if (entity.getProperty("name").equals(name)) {
				ArrayList<Long> ID = (ArrayList<Long>) entity
						.getProperty("posts");
				for (Long postId : ID) {
					for (Entity entity2 : list1) {
						if (entity2.getProperty("ID").equals(postId)) {
							TimelinePost post = new TimelinePost();
							post.setContent(entity2.getProperty("content")
									.toString());
							post.setID(postId);
							post.setLink(entity2.getProperty("link").toString());
							post.setOwner(entity2.getProperty("owner")
									.toString());
							if (entity.getProperty("privacy") != null)
								post.setprivacy(entity2.getProperty("privacy")
										.toString());
							else
								post.setprivacy("public");
							post.sharNum = Integer.parseInt(entity2
									.getProperty("sheredNum").toString());
							posts.add(post);
						}
					}
				}
			}
		}
		return posts;
	}

	@Override
	public void update(Post post, ArrayList<String> hashTag) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("HasTag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (String hash : hashTag) {
			try {
				ArrayList<Long> hashs;
				boolean b = false;
				for (Entity entity : list) {
					if (entity.getProperty("name").equals(hash)) {
						hashs = (ArrayList<Long>) entity.getProperty("hashs");
						hashs.add(post.ID);
						datastore.put(entity);
						b = true;
						break;
					}
				}
				if (b)
					break;
				Entity hashTagE = new Entity("HashTag", list.size() + 2);
				hashTagE.setProperty("name", hash);
				hashs = new ArrayList<>();
				hashs.add(post.ID);
				hashTagE.setProperty("posts", hashs);
				datastore.put(hashTagE);
				String temp = Integer.toString(list.size() + 2);
				post.setHashTagID(temp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
