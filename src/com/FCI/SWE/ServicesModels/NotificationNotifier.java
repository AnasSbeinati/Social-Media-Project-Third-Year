package com.FCI.SWE.ServicesModels;

/**
 * <h1>Notification Notifier Class</h1>
 * <p>
 * This class will notify an update the Notification table
 * </p>
 * 
 * @author Anoos
 * @version 1.0
 * @since 4-2-2015
 *
 */

public class NotificationNotifier implements Notifier {
	Notification notification;
	String ID = "";
	boolean seen = false;

	public NotificationNotifier(Notification notification) {
		super();
		this.notification = notification;
	}

	@Override
	public void update() {
		notification.excute();

	}

	public void seen() {

	}
}
