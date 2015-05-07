package com.FCI.SWE.ServicesModels;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class MessageEntity {
	String sender;
	HashMap<String, Integer> receiver = new HashMap<>();
	int id;
	String body;
	String name;
	String time;

	/**
	 * 
	 * constructor , this will be called to set valuse of msge
	 * 
	 * @author amal khaled
	 * @param sender
	 *            sender email
	 * @param receiver
	 *            who will receive my msg
	 * @param name
	 *            conversation name when chat contain more than one friend
	 * @param msg
	 *            message that i want to send
	 * @param time
	 *            time of sending the message
	 * @return string
	 */

	public MessageEntity(String sender, String[] receiver, String name,
			String msg, String time) {

		this.sender = sender;
		for (int i = 0; i < receiver.length; i++) {
			this.receiver.put(receiver[i], 0);
		}
		this.name = name;
		this.body = msg;
		this.time = time;

	}

	public boolean Create() {
		save();
		return true;
	}

	public void save() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Message");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity Mesg = new Entity("Message", list.size() + 1);
		EmbeddedEntity ee = new EmbeddedEntity();

		for (String key : this.receiver.keySet()) {

			ee.setProperty(key, this.receiver.get(key));

		}
		Mesg.setProperty("Sender", this.sender);
		Mesg.setProperty("reciever", ee);
		Mesg.setProperty("Body", this.body);
		Mesg.setProperty("Name", this.name);
		Mesg.setProperty("Time", this.time);
		datastore.put(Mesg);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
