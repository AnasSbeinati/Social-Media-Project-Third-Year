package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

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
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/
	
	/*@POST
	@Path("/SearchService")
	public String searchFriend(@FormParam("uname") String uname){
		
	}*/


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
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
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
		if (UserEntity.isUser(From)
				&& UserEntity.isUser(To)) {
			if (UserEntity.applyRequest(From, To)) {
				JSONObject obj = new JSONObject();
				obj.put("Status", "OK");
				return obj.toString();
			}
			else
			{
				JSONObject obj = new JSONObject();
				obj.put("Status", "request already has been applied");
				return obj.toString();
			}
		}
		else{
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

      for ( int i = 0 ; i < senders.size() ; i++){
    	  obj.put("user", senders.get(i));
    	  arr.put(obj);
      }
		

		return obj.toString();

	}
	
	

}