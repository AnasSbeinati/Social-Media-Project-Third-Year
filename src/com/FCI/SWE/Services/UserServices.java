package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
//import com.google.appengine.repackaged.com.google.common.base.Receiver;
import com.sun.org.apache.bcel.internal.generic.ISUB;
import com.sun.org.glassfish.gmbal.ParameterNames;
import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.HashTagTimeLine;
import com.FCI.SWE.ServicesModels.Home;
import com.FCI.SWE.ServicesModels.MesNotification;
import com.FCI.SWE.ServicesModels.MessageEntity;
import com.FCI.SWE.ServicesModels.Notification;
import com.FCI.SWE.ServicesModels.Post;
import com.FCI.SWE.ServicesModels.ReqNotification;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.FCI.SWE.ServicesModels.UserTimeLine;

import org.json.simple.JSONArray;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {

	/*
	 * @GET
	 * 
	 * @Path("/index") public Response index() { return Response.ok(new
	 * Viewable("/jsp/entryPoint")).build(); }
	 */

	/*
	 * @POST
	 * 
	 * @Path("/SearchService") public String searchFriend(@FormParam("uname")
	 * String uname){
	 */

	/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		System.out.println("Hey");
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}

		return object.toString();

	}

	/**
	 * 
	 * AddFriend Rest Service, this service will be called to make add friend
	 * 
	 * @author amal khaled
	 * @param email
	 *            active user email
	 * @return string
	 */
	@POST
	@Path("/AddFriend")
	public String addFriend(@FormParam("RecEamil") String Rec,
			@FormParam("SenderEmail") String sender) {
		JSONObject obj = new JSONObject();
		UserEntity user = new UserEntity(Rec);

		if (user.ShowRequest(Rec, sender))
			obj.put("Status", "OK");
		else
			obj.put("Status", "Failed");

		return obj.toString();

	}

	/**
	 * 
	 * SignOut Rest Service, this service will be called to make Signout
	 * 
	 * @author amal khaled
	 * @param email
	 *            active user email
	 * @return string
	 */

	@POST
	@Path("/SignOut")
	public String SignOut() {

		User user = User.getCurrentActiveUser();
		JSONObject object = new JSONObject();
		user.SetUnactive();
		object.put("Status", "ok");
		return object.toString();
	}

	/**
	 * Send user friend request service this service will be called when auser
	 * want to add a new friend to its own list friends
	 * 
	 * @param
	 */
	@POST
	@Path("/SendRequest")
	public String sendRequest(@FormParam("fromName") String From,
			@FormParam("toName") String To) {
		if (UserEntity.isUser(From) && UserEntity.isUser(To)) {
			if (UserEntity.applyRequest(From, To)) {
				JSONObject obj = new JSONObject();
				obj.put("Status", "OK");
				return obj.toString();
			} else {
				JSONObject obj = new JSONObject();
				obj.put("Status", "request already has been applied");
				return obj.toString();
			}
		} else {
			JSONObject obj = new JSONObject();
			obj.put("Status", "one is not a user");
			return obj.toString();
		}
	}

	@POST
	@Path("/DeletRequest")
	public String DeletRequest(@FormParam("RecEamil") String Rec,
			@FormParam("SenderEmail") String sender) {
		JSONObject obj = new JSONObject();
		UserEntity user = new UserEntity(Rec);

		if (user.deleteRequest(Rec, sender))
			obj.put("Status", "OK");
		else
			obj.put("Status", "Failed");

		return obj.toString();

	}

	@POST
	@Path("/Show")
	public String Show(@FormParam("RecEamil") String Rec) {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		UserEntity user = new UserEntity(Rec);
		ArrayList senders = new ArrayList<>();

		senders = user.showall(Rec);

		for (int i = 0; i < senders.size(); i++) {
			obj.put("user", senders.get(i));
			arr.add(obj);
		}

		return arr.toJSONString();

	}

	/**
	 * 
	 * sendmessage Rest Service, this service will be called to send message to
	 * onr or more friend
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

	@POST
	@Path("/SendMessage")
	public String SendMessage(@FormParam("Sender") String sender,
			@FormParam("Receiver") String reciver,
			@FormParam("Name") String name, @FormParam("Msg") String msg,
			@FormParam("Time") Date time) {
		String Receiver[] = reciver.split(",");
		MessageEntity m = new MessageEntity(sender, Receiver, name, msg, time);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/getNotification")
	public String getNotification(@FormParam("user") String user,

	@FormParam("pass") String pass) {
		if (UserEntity.isUser(user)) {
			UserEntity userE = UserEntity.getUser(user, pass);
			userE.getNotifications();
			JSONObject object = new JSONObject();
			for (Notification not : userE.notifications) {
				if (not instanceof MesNotification)
					object.put(((MesNotification) not).getID(), "Msg");
				else if (not instanceof ReqNotification) {
					object.put(((ReqNotification) not).getID(), "Req");
				}
			}
			if (object.isEmpty()) {
				object.put("Status", "No Nofications");
				return object.toString();
			}
			return object.toString();
		} else {
			JSONObject object = new JSONObject();
			object.put("Status", "It's not a user");
			return object.toString();
		}
	}

	/*
	 * get user Time line service
	 * 
	 * @author Anas
	 * 
	 * @param user
	 */
	@POST
	@Path("/getUserTimeLine")
	public String getUserTimeLine(@FormParam("user") String user,
			@FormParam("pass") String pass) {
		if (UserEntity.isUser(user)) {
			UserEntity userE = UserEntity.getUser(user, pass);
			UserTimeLine userTimeLine = new UserTimeLine(user);
			ArrayList<Post> posts = userTimeLine.get();
			JSONObject object = new JSONObject();
			for (Post post : posts) {
				object.put("owner", post.owner);
				object.put("link", post.link);
				object.put("likers", post.likers);
				object.put("content", post.content);
				object.put("sharenum", post.sharNum);
				object.put("ID", post.ID);
				object.put("privacy", post.privacy.toString());
			}
			return object.toString();
		} else {
			JSONObject object = new JSONObject();
			object.put("Status", "It's not a user");
			return object.toString();
		}
	}

	/*
	 * get Hash Time line service
	 * 
	 * @author Anas
	 * 
	 * @param user 20-4-2015
	 */
	@POST
	@Path("/getHashTag")
	public String getHashTag(@FormParam("hashName") String name) {
		HashTagTimeLine hashTag = new HashTagTimeLine();
		ArrayList<Post> posts = hashTag.get();
		JSONObject object = new JSONObject();
		for (Post post : posts) {
			object.put("owner", post.owner);
			object.put("link", post.link);
			object.put("likers", post.likers.toString());
			object.put("content", post.content);
			object.put("sharenum", post.sharNum);
			object.put("ID", post.ID);
			object.put("privacy", post.privacy.toString());
		}
		return object.toString();
	}

	/*
	 * get user Home service
	 * 
	 * @author Anal
	 * 
	 * @param user 20-4-2015
	 */
	@POST
	@Path("/getHome")
	public String getHome(@FormParam("user") String name) {
		if (UserEntity.isUser(name)) {
			Home home = new Home(name);
			ArrayList<Post> posts = home.get();
			JSONObject object = new JSONObject();
			for (Post post : posts) {
				object.put("owner", post.owner);
				object.put("link", post.link);
				if (post.likers != null)
					object.put("likers", post.likers.toString());
				else
					object.put("likers", "0");
				object.put("content", post.content);
				object.put("sharenum", post.sharNum);
				object.put("ID", post.ID);
				object.put("privacy", post.privacy.toString());
			}
			return object.toString();
		} else {
			JSONObject object = new JSONObject();
			object.put("Status", "It's not a user");
			return object.toString();
		}
	}
}