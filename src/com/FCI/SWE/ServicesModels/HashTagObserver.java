package com.FCI.SWE.ServicesModels;


public interface HashTagObserver {
	public void update(Post post,String hashTag);
	public void check();
}
