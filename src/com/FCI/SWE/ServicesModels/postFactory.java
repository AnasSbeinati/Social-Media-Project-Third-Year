package com.FCI.SWE.ServicesModels;

import java.util.HashMap;

public class postFactory {

	private HashMap<String, Post> posts = new HashMap<>();
	private static postFactory instance = new postFactory();

	private postFactory() {

	}

	public static postFactory getInstance() {
		return instance;
	}

	public void registerProduct(String pID, Post pSample) {
		posts.put(pID, pSample);
	}

	public Post createPost(String pID) {
		Post sample = posts.get(pID);
		if (sample == null) {

			return null;
		}
		return sample.Create();
	}
}