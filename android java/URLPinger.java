package com.example.survey;
import java.util.ArrayList;

//Auxiliary class to encode the EC2 server link with the URL parameter format

public class URLPinger {
	private static String baseUrl = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/";
	
	public String generateUrl(String file, String[] params, String[] val){
		String url = baseUrl+file+"/";
		for(int i = 0; i < params.length; i++){
			url += "&\""+params[i]+"\"=\""+val[i]+"\"";
		}
		return url;
	}
	
    public static Object[] parseMissing(String res){
        ArrayList sidList = new ArrayList();
        String[] lines = res.split(";");
        for(String l : lines){
                String[] p = l.split(",");
                String sid = p[0];
                String schoolName = p[1];
                sidList.add(sid);
                sidList.add(schoolName);
        }
        return sidList.toArray();
}
	
	public static void main(String args[]){
		parseMissing("1,boom;1,boom;1,boom;1,boom;1,boom;1,boom;1,boom;");
	}
}
