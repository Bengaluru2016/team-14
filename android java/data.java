package com.example.vinay.sqllogin;

/**
 * Created by Vinay on 09-07-2016.
 */
public class data {
    private static String baseUrl = "http://ec2-54-169-131-166.ap-southeast-1.compute.amazonaws.com/";

    public static String generateUrl(String file, String[] params, String[] val){
        String url = baseUrl+file+"/?";
        for(int i = 0; i < params.length; i++){
            url += "&\""+params[i]+"\"=\""+val[i]+"\"";
        }
        return url;
    }
}
