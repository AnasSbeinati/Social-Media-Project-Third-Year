package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Anas sbeinati
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	private long id;
	public ArrayList<Notification> notifications;
	Notifier notifier;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		notifications = new ArrayList<>();
	}

	public UserEntity(String email) {
		this.email = email;

	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				returnedUser.setId(entity.getKey().getId());
				return returnedUser;
			}
		}

		return null;
	}

	/**
	 * 
	 * 
	 * This mwthod will check if user has any friend request from other users
	 * and will accept all requests
	 * 
	 * @author amal khaled
	 * @param emails
	 *            String represents active user email
	 * @return boolean
	 */
	public boolean ShowRequest(String Rec, String sender) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("reciever").toString().equals(Rec)

			&& entity.getProperty("sender").toString().equals(sender)
					&& entity.getProperty("friend").toString().equals("0")) {

				entity.setProperty("friend", "1");
				datastore.put(entity);
				return true;
			}
		}
		return false;
	}

	public boolean deleteRequest(String Rec, String sender) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("reciever").toString().equals(Rec)
					&& entity.getProperty("sender").toString().equals(sender)
					&& entity.getProperty("friend").toString().equals("0")) {

				Key UserKey = KeyFactory.createKey("reciver", Rec);
				datastore.delete(UserKey);

				return true;
			}
		}
		return false;
	}

	public ArrayList showall(String Rec) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		ArrayList senders = new ArrayList<>();
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("reciever").toString().equals(Rec)
					&& entity.getProperty("friend").toString().equals("0")) {

				senders.add(entity.getProperty("sender").toString());
			}
		}
		return senders;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("users", list.size() + 2);

			employee.setProperty("name", this.name);
			employee.setProperty("email", this.email);
			employee.setProperty("password", this.password);

			datastore.put(employee);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	/**
	 * 
	 * 
	 * This method will check if user exist oe not
	 * 
	 * @author Anas Sbeinati
	 * @param username
	 *            String represents user name who's wanted
	 * @return boolean
	 */
	public static boolean isUser(String username) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : list) {
			if (entity.getProperty("name").equals(username)) {
				return true;
			}
		}
		return false;
	}

	public static boolean applyRequest(String nameFrom, String nameTo) {
		if (nameFrom.equals(nameTo))
			return false;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		// boolean con=true;
		for (Entity entity : list) {
			if (entity.getProperty("reciever").equals(nameFrom)
					&& entity.getProperty("sender").equals(nameTo))
				return false;
			if (entity.getProperty("sender").equals(nameFrom)
					&& entity.getProperty("reciever").equals(nameTo))
				return false;
		}
		Entity employee = new Entity("friends", list.size() + 1);

		// employee.setPr);
		employee.setProperty("friend", "0");
		employee.setProperty("reciever", nameTo);
		employee.setProperty("sender", nameFrom);
		datastore.put(employee);
		return true;
	}

	public void getNotifications() {
		notifications = new ArrayList<Notification>();
		try {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("Notifications");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			Query gaeQuery1 = new Query("Message");
			PreparedQuery pq1 = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			List<Entity> list1 = pq1
					.asList(FetchOptions.Builder.withDefaults());
			for (Entity entity : list) {
				if (entity.getProperty("Seen").equals("0")) {
					if (entity.getProperty("Type").equals("Msg")) {
						for (Entity entity2 : list1) {
							if (entity.getProperty("ID").equals(
									entity2.getProperty("ID"))) {
								MessageEntity msg = new MessageEntity(entity
										.getProperty("Sender").toString(),
										null, entity2.getProperty("name")
												.toString(), entity2
												.getProperty("Msg").toString(),
										null);
								msg.setId(Integer.parseInt(entity.getProperty("ID").toString()));
								MesNotification not = new MesNotification(msg);
								not.setID(entity.getProperty("ID").toString());
								notifications.add(not);
							}
						}
					} else {
						// request notification
					}
				}
			}
		} catch (Exception e) {
			System.out.println("here");
		}
	}
}
