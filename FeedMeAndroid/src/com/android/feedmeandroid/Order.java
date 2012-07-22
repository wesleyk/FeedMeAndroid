package com.android.feedmeandroid;

import java.util.ArrayList;

import org.json.JSONObject;

import android.util.Log;

public class Order {
	private ArrayList<Food> mOrder = new ArrayList<Food>();
	public void clear(){
		mOrder.clear();
	}
	public void addFood(Food food){
		mOrder.add(food);
	}
	public void remove(Food food){
		mOrder.remove(food);
	}
	public int size(){
		return mOrder.size();
	}
	public Food get(int index){
		return mOrder.get(index);
	}
	
	public void submitOrder() {
		int[] order_ids = new int[mOrder.size()];
		for(int i = 0; i < mOrder.size(); i++) {
			order_ids[i] = Integer.parseInt(mOrder.get(i).id);
		}
		
		final int[] thread_order_ids = order_ids;
		//submit order
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					
					JSONObject webRequest = new JSONObject();
					webRequest.put("user_id", Feed.fb_id);
					webRequest.put("order", thread_order_ids);
					Log.v("request", webRequest.toString());
					ArrayList<JSONObject> response = HTTPClient
							.SendHttpPost(
									Constants.WEB_CLIENT_REST_URL_ORDER,
									webRequest);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
