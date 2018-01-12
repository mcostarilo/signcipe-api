package com.st.signservice.push.service;

public interface PushNotificationsService {

	void sendNotification(int serviceId, int patients, String updateSchedule);
	
}
