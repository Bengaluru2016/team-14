package com.example.survey;

public class URLPinger {
	private static String baseUrl = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/";
	
	public String generateUrl(String file, String[] params, String[] val){
		String url = baseUrl;
		for(int i = 0; i < params.length; i++){
			url += "&"+params[i]+"="+val[i];
		}
		return url;
	}
}
