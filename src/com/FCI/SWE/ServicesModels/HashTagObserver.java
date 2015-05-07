package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;


public interface HashTagObserver {
	public void update(Post post,ArrayList<String> hashTag);
	public void check();
}
