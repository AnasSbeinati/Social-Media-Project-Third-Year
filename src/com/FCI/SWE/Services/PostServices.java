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
import com.FCI.SWE.ServicesModels.postFactory;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PostServices {

	@POST
	@Path("/CreatePost")
	public String CreatePost(@FormParam("Type") String type,
			@FormParam("Link") String link, @FormParam("Owner") String owner,
			@FormParam("Content") String content,
			@FormParam("sharedpostId") long sharedpostId,
			@FormParam("felling") String feeling,
			@FormParam("privacy") String privacy,
			@FormParam("Custom") String Custom) throws ClassNotFoundException {

		Class.forName("com.FCI.SWE.ServicesModels.TimelinePost");
		Class.forName("com.FCI.SWE.ServicesModels.PagePost");
		Class.forName("com.FCI.SWE.ServicesModels.PageSharedpost");
		Class.forName("com.FCI.SWE.ServicesModels.TimelineSharedPost");
		Post post = postFactory.getInstance().createPost(type);
		post.registerprivacy();
		//if (post instanceof TimelinePost)
			//System.out.println("Here");
		String cansee[] = Custom.split(",");
		ArrayList<String> canSee = new ArrayList<>();
		for (int i = 0; i < cansee.length; i++)
			canSee.add(cansee[i]);

		JSONObject object = new JSONObject();
		if (post == null) {
			object.put("Status", "false");
		}else {
			System.out.println("Here");
			post.CreatePost(link, owner, content, feeling, sharedpostId,
					privacy, canSee, type);
			object.put("Status", "OK");
		}
		return object.toString();
	}

	@POST
	@Path("/likePost")
	public String likePost(@FormParam("Type") String type,
			@FormParam("postid") long postID, @FormParam("Liker") String Liker)
			throws ClassNotFoundException {
		JSONObject object = new JSONObject();
		Class.forName("com.FCI.SWE.ServicesModels.PageSharedpost");
		Class.forName("com.FCI.SWE.ServicesModels.TimelineSharedPost");
		Class.forName("com.FCI.SWE.ServicesModels.TimelinePost");
		Class.forName("com.FCI.SWE.ServicesModels.PagePost");
		Post post = postFactory.getInstance().createPost(type);
		ArrayList<String> array = new ArrayList<>();

		if (post.likePost(postID, Liker) == false)
			object.put("Status", "false");

		else
			object.put("Status", "OK");
		return object.toString();

	}

	@POST
	@Path("/SeePost")
	public String SeePost(@FormParam("Type") String type,
			@FormParam("postid") long postID, @FormParam("user") String user)
			throws ClassNotFoundException {
		JSONObject object = new JSONObject();
		Class.forName("com.FCI.SWE.ServicesModels.PageSharedpost");
		Class.forName("com.FCI.SWE.ServicesModels.PagePost");
		Post post = postFactory.getInstance().createPost(type);

		if (post.setSeen(postID, user) == false)
			object.put("Status", "false");

		else
			object.put("Status", "OK");
		return object.toString();

	}

	@POST
	@Path("/SharePost")
	public String SharePost(@FormParam("Type") String type,
			@FormParam("postid") long postID, @FormParam("user") String user)
			throws ClassNotFoundException {
		JSONObject object = new JSONObject();
		Class.forName("com.FCI.SWE.ServicesModels.PageSharedpost");
		Class.forName("com.FCI.SWE.ServicesModels.TimelineSharedPost");
		Class.forName("com.FCI.SWE.ServicesModels.TimelinePost");
		Class.forName("com.FCI.SWE.ServicesModels.PagePost");
		Post post = postFactory.getInstance().createPost(type);

		if (post.setSeen(postID, user) == false)
			object.put("Status", "false");

		else
			object.put("Status", "OK");
		return object.toString();

	}

	/*
	 * @POST
	 * 
	 * @Path("/GetOriginalPostId") public String
	 * GetOriginalPostId(@FormParam("Type") String type,
	 * 
	 * @FormParam("postid") long postID) throws ClassNotFoundException {
	 * JSONObject object = new JSONObject();
	 * Class.forName("com.FCI.SWE.ServicesModels.PageSharedpost");
	 * Class.forName("com.FCI.SWE.ServicesModels.TimelineSharedPost"); Post post
	 * = postFactory.getInstance().createPost(type); long Id =
	 * post.GetOriginalPostID(postID); if (Id == -1) object.put("Status",
	 * "false");
	 * 
	 * else object.put("id", Id); return object.toString();
	 * 
	 * }
	 */

}
