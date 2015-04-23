package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

/**
 * <h1>TimeLineabstract</h1>
 * <p>
 * This class Abstract of multi timelines
 * </p>
 *
 * @author Anas sbeinati
 * @version 1.0
 * @since 2014-04-22
 */
public abstract class TimeLine {
	public ArrayList<Post>posts=new ArrayList<>();
	public ArrayList<HashTagTimeLine>hashTagsTrends;
	public ArrayList<Post>get() {
		return null;
	}
}
