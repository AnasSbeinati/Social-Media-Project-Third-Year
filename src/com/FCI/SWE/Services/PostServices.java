package com.FCI.SWE.Services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.PagePost;
import com.FCI.SWE.ServicesModels.PageSharedpost;
import com.FCI.SWE.ServicesModels.Post;
import com.FCI.SWE.ServicesModels.TimelinePost;
import com.FCI.SWE.ServicesModels.TimelineSharedPost;
import com.FCI.SWE.ServicesModels.UserEntity;

/**
 * This class contains REST services for post, also contains action function for
 * web application
 * 
 * @author amal kahled
 * @version 1.c
 * @since 2015-4-25
 *
 */

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PostServices {
	// privcy = (Pravicy)
	// Class.forName("com.FCI.SWE.ServicesModels."+p).newInstance();

	/**
	 * 
	 * CreatePost Rest Service, this service will be called to create post or
	 * share post
	 * 
	 * @author amal khaled
	 * @param Type
	 *            post type (page or sharedpage , timeline , or sharedtimeline)
	 * @param Link
	 *            where the post will be
	 * @param Owner
	 *            who create post
	 * @param contenet
	 *            content of the post
	 * @param sharedpostId
	 *            if it sharedpost this will the original post id
	 * @param felling
	 *            user felling
	 * @param privacy
	 *            privacy of the post
	 * @param custom
	 *            users that can see post on Custom case
	 * @return string
	 */

	@POST
	@Path("/CreatePost")
	public String CreatePost(@FormParam("Type") String type,
			@FormParam("Link") String link, @FormParam("Owner") String owner,
			@FormParam("Content") String content,
			@FormParam("sharedpostId") long sharedpostId,
			@FormParam("felling") String feeling,
			@FormParam("privacy") String privacy,
			@FormParam("Custom") String Custom) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		Post post = (Post) Class.forName("com.FCI.SWE.ServicesModels." + type)
				.newInstance();
		String cansee[] = Custom.split(",");
		ArrayList<String> canSee = new ArrayList<>();
		for (int i = 0; i < cansee.length; i++)
			canSee.add(cansee[i]);

		JSONObject object = new JSONObject();
		if (post == null) {
			object.put("Status", "false");
		} else {
			post.CreatePost(link, owner, content, feeling, sharedpostId,
					privacy, canSee, type);
			object.put("Status", "OK");
		}
		return object.toString();
	}

	/**
	 * 
	 * likePost Rest Service, this service will be called to like post
	 * 
	 * @author amal khaled
	 * @param Type
	 *            post type (page or sharedpage , timeline , or sharedtimeline)
	 * @param postid
	 *            post Id that user want to like
	 * @param liker
	 *            user that want to like post
	 * @return string
	 */

	@POST
	@Path("/likePost")
	public String likePost(@FormParam("Type") String type,
			@FormParam("postid") long postID, @FormParam("Liker") String Liker)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		JSONObject object = new JSONObject();

		Post post = (Post) Class.forName("com.FCI.SWE.ServicesModels." + type)
				.newInstance();
		ArrayList<String> array = new ArrayList<>();

		if (post.likePost(postID, Liker) == false)
			object.put("Status", "false");

		else
			object.put("Status", "OK");
		return object.toString();

	}

	/**
	 * 
	 * SeePost Rest Service, this service will be called to set user that saw
	 * post
	 *
	 * @author amal khaled
	 * @param Type
	 *            post type (page or sharedpage , timeline , or sharedtimeline)
	 * @param postid
	 *            post Id that user want to see
	 * @param user
	 *            user who saw the post
	 * @return string
	 */

	@POST
	@Path("/SeePost")
	public String SeePost(@FormParam("Type") String type,
			@FormParam("postid") long postID, @FormParam("user") String user)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		JSONObject object = new JSONObject();
		Post post = (Post) Class.forName("com.FCI.SWE.ServicesModels." + type)
				.newInstance();
		if (post.setSeen(postID, user) == false)
			object.put("Status", "false");

		else
			object.put("Status", "OK");
		return object.toString();

	}

	/**
	 * 
	 * GetOriginalPostId Rest Service, this service will be called to get
	 * original post id on sharing
	 * 
	 *
	 * @author amal khaled
	 * @param Type
	 *            post type (page or sharedpage , timeline , or sharedtimeline)
	 * @param postid
	 *            post Id that user want to see
	 * @param user
	 *            user who saw the post
	 * @return string
	 */

	@POST
	@Path("/GetOriginalPostId")
	public String GetOriginalPostId(@FormParam("Type") String type,
			@FormParam("postid") long postID) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		JSONObject object = new JSONObject();
		Post post = (Post) Class.forName("com.FCI.SWE.ServicesModels." + type)
				.newInstance();
		long Id = post.GetOriginalPostID(postID);
		if (Id == -1)
			object.put("Status", "false");

		else
			object.put("id", Id);
		return object.toString();
	}

}
