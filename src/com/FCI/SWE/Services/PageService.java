package com.FCI.SWE.Services;
import com.FCI.SWE.ServicesModels.Page;
import com.FCI.SWE.ServicesModels.Post;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;





@Path("/")
@Produces(MediaType.TEXT_PLAIN)

	
public class PageService {
	@POST
	@Path("/CreatePageService")
	public String createPage(@FormParam("Type") String type,
			@FormParam("Name") String pageName,
			@FormParam("Owner") String ownerId,
			@FormParam("Category") String category
			
			) {
		
		Page pageEntity = new Page();
		pageEntity.setPageName(pageName);
		pageEntity.setPageOwner(ownerId);
		pageEntity.setCategory(category);
		pageEntity.setType(type);
		
		JSONObject json = new JSONObject();
		if(pageEntity.savePage())
			json.put("Status", "OK");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}

	@POST
	@Path("/LikePage")
public void LikePage(@FormParam("id") long pageID,
				@FormParam("Liker") String likerEmail) {
				Page pageEntity = new Page();
				pageEntity.countingLikes(pageID);
				pageEntity.addingLiker(pageID, likerEmail);
		

}
	@POST
	@Path("/getPage")
public  String getPage (@FormParam("Name") String pageName){
	Page pageEntity = new Page();
	JSONObject json = new JSONObject();
	json.put("posts", pageEntity.getPage(pageName));
	return json.toString();
}
}


