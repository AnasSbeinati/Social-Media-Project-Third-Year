package com.FCI.SWE.ServicesModels;

/**
 * <h1>Page class</h1>
 * <p>
 * This class is PAge Time line
 * </p>
 *
 * @author Habeeba Baioumy Ahmed
 * @version 1.0
 * @since 2014-04-22
 */

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class Page extends TimeLine{
	long id;
	String pageName;
	String pageOwner;
	String category;
	long like;
	long reach;
	String type;
	ArrayList <String>likers;
	
	
	
	

	/**
	 * 
	 * constructor , this  will be called to create page 
	 * 
	 * @author Habeeba Baioumy
	 * @param Name
	 *             page name
	 * @param owner
	 *             the one who created the page
	 * @param category
	 *            the category of the page (eg:sports)
	 * @param like
	 *             number of likes
	 * @param reach
	 *             number of people reached the page
	 * @param likers
	 *            people who like this page
	 *                                                
	 * @return string
	 */

/*public PageEntity(int id, String pageName, String pageOwner, String category, int like, int reach, ArrayList <String>likers) {
		
		this.id = id;
		this.pageName = pageName;
		this.pageOwner = pageOwner;
		this.category = category;
		this.like = like;
		this.reach = reach;
		for ( int i = 0; i < likers.size(); i++){
			this.likers.add(likers.get(i));
		}
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Page");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity page = new Entity("Page", list.size() + 1);
		

		
		page.setProperty("Name", this.pageName);
		page.setProperty("Owner", this.pageOwner);
		page.setProperty("Category", this.category);
		page.setProperty("Likes", this.like);
		page.setProperty("Reach", this.reach);
		page.setProperty("Likers", this.likers);
		datastore.put(page);

	}*/

	
	public String getCategory() {
		return category;
	}
	
	public long getId() {
		return id;
	}
	
	public long getLike() {
		return like;
	}
	
	public ArrayList<String> getLikers() {
		return likers;
	}
	
	public String getPageName() {
		return pageName;
	}
	
	public String getPageOwner() {
		return pageOwner;
	}
	
	public long getReach() {
		return reach;
	}
	
	public String getType() {
		return type;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setLike(long like) {
		this.like = like;
	}
	
	public void setLikers(ArrayList<String> likers) {
		this.likers = likers;
	}
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	public void setPageOwner(String pageOwner) {
		this.pageOwner = pageOwner;
	}
	
	public void setReach(long reach) {
		this.reach = reach;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public boolean savePage() {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity page = new Entity("Pages", list.size() + 1);
		

		
		page.setProperty("Name", this.pageName);
		page.setProperty("Owner", this.pageOwner);
		page.setProperty("Category", this.category);
		page.setProperty("Likes", this.like);
		page.setProperty("Reach", this.reach);
		page.setProperty("Likers", this.likers);
		page.setProperty("Type", this.type);
		if(datastore.put(page).isComplete())
			return true;
		else return false;

	}
	
	public void countingLikes(long ID) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pqs = datastore.prepare(gaeQuery);
		for (Entity entity : pqs.asIterable()) {
			if (entity.getProperty("id").equals(ID))
					 {
				
				long countLikes = (long) entity.getProperty("Likes");
				entity.setProperty("likes" ,countLikes+1);
				datastore.put(entity);
			}
		}

	}
	
	public void addingLiker(long pageID, String likerEmail ) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("id").equals(pageID))
					 {
				this.likers.add(likerEmail);
				entity.setProperty("Likers", this.likers);
				datastore.put(entity);
			}
		}

	}
	
	
	public ArrayList<Post> getPage(String name) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : list) {
			if (entity.getProperty("owner").equals(name)) {
				PagePost post = new PagePost();
				post.setContent(entity.getProperty("content").toString());
				// post.setID(iD);
				post.setLink(entity.getProperty("link").toString());
				post.setOwner(name);
				post.setprivacy(entity.getProperty("public").toString());
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


}

