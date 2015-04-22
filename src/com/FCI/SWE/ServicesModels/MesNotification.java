package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>Message Notification</h1>
 * <p>
 * 
 * </p>
 * @author Anoos
 * @version 1.0
 * @since 4-2-2015
 */

public class MesNotification implements Notification {
	MessageEntity msg;
	public String ID;
	public MesNotification(MessageEntity msg) {
		super();
		this.msg = msg;
	}

	public void setID(String ID1) {
		ID=ID1;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void excute() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Notifications");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity not = new Entity("Notifications", list.size() + 1);
		not.setProperty("Sender","Msg");
		not.setProperty("Type","Msg");
		not.setProperty("ID",msg.id);
		not.setProperty("Seen","0");
		datastore.put(not);
	}

}
