package com.example.paulr.androidmysqltest;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Search extends Thread {
    String wordEnglish;
    String defenitionEnglish = "1111";
    InputStream is = null;
    String result = null;
    String line = null;

    @Override
    public void run() {
        super.run();

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("wordEnglish", wordEnglish));

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://siteforproject.000webhostapp.com/get_products_details.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity enity = response.getEntity();
            is = enity.getContent();
            Log.e("pass 1", "connection success");

        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
        }


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 100);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            reader.close();
            Log.e("pass 2", "connection" + result);
        } catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }

        try {
            JSONObject json_data = new JSONObject(result);
            wordEnglish = (json_data.getString("wordEnglish"));
            defenitionEnglish = (json_data.getString("defenitionEnglish"));
            Log.e("pass 3", wordEnglish);

        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }


    }

    public void start(String wordEnglish)
    {
        this.wordEnglish = wordEnglish;
        this.start();
    }


    public String getDefenitionEnglish() {
        return defenitionEnglish;
    }
}




