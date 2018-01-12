package com.st.signservice.push.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushNotificationsServiceImpl implements PushNotificationsService {

	private static final String FIREBASE_SERVER_KEY = "AAAARHqdotE:APA91bGfcZ8A7UMs01VS2rY4fbOZkeMT_M-t3gKOPSPhahDf1vf7mIdzdgZtGLwmXMtMQ4O_5vjph7j5UxJ1C_EeJZ2RZ_LHl9kx11fN1uU-Rnf0uNHCUe9fv23R09raOH_HnBjNuLdW";
	
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	private CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		/**
		https://fcm.googleapis.com/fcm/send
		Content-Type:application/json
		Authorization:key=FIREBASE_SERVER_KEY*/

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}

	@Override
	public void sendNotification(int serviceId, int patients, String updateSchedule) {
		JSONObject body = new JSONObject();
		try {
			body.put("to", "/topics/" + serviceId);
			body.put("priority", "high");

			JSONObject notification = new JSONObject();
			notification.put("title", "Salud 3.0");

			notification.put("body", "Nuevos pacientes esperando...");
			
			JSONObject data = new JSONObject();
			data.put("title", "Salud 3.0");
			data.put("body", "Nuevos pacientes esperando...");
			data.put("body_big", "Nuevos pacientes esperando...");
			data.put("title_big", "Salud 3.0");
			data.put("count", patients);
			data.put("count_description", "Total en espera");
			data.put("update_schedule", updateSchedule);
			//body.put("notification", notification);
			body.put("data", data);
			
			HttpEntity<String> request = new HttpEntity<>(body.toString());

			CompletableFuture<String> pushNotification = send(request);
			CompletableFuture.allOf(pushNotification).join();

			
			String firebaseResponse = pushNotification.get();
				
			System.out.println("PUSH NOTIFICATION RESPONSE: " + firebaseResponse);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}