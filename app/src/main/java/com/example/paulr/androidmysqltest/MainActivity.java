package com.example.paulr.androidmysqltest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

public class MainActivity extends AppCompatActivity {
    private EditText wordEnglish;
    private EditText defenitionEnglish;
    private EditText wordRussian;
    private EditText defenitionRussian;
    private EditText wordChina;
    private EditText defenitionChina;
    private EditText wordJapan;
    private EditText defenition_Japan;

    private String wordEnglishText = "111";
    private String defenitionEnglishText= "111";
    private String wordRussianText = "111";
    private String defenitionRussianText = "111";
    private String wordChinaText = "111";
    private String defenitionChinaText = "111";
    private String wordJapanText = "111";
    private String defenition_JapanText = "111";

    InputStream is = null;
    String result = null;
    String line = null;

//    private Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       wordEnglish = findViewById(R.id.wordEnglish);
        defenitionEnglish = findViewById(R.id.defenitionEnglish);
        wordRussian = findViewById(R.id.wordRussian);
        defenitionRussian = findViewById(R.id.defenitionRussian);
        wordChina = findViewById(R.id.wordChina);
        defenitionChina = findViewById(R.id.defenitionChina);
        wordJapan = findViewById(R.id.wordJapan);
        defenition_Japan = findViewById(R.id.defenition_Japan);


    }

   public void Search(View view) {
       TestAsyncTask testAsyncTask = new TestAsyncTask();
       wordEnglishText = wordEnglish.getText().toString();
       testAsyncTask.execute(wordEnglishText);
   }
//        try {
//            search.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        defenitionEnglishText = search.getDefenitionEnglish();
//        defenitionEnglish.setText(defenitionEnglishText);
//
//        wordRussianText = search.getWordRussian();
//        wordRussian.setText(wordRussianText);
//        defenitionRussianText = search.getDefenitionRussian();
//        defenitionRussian.setText(defenitionRussianText);
//
//        wordChinaText = search.getWordChina();
//        wordChina.setText(wordChinaText);
//        defenitionChinaText = search.getDefenitionChina();
//        defenitionRussian.setText(defenitionChinaText);
//
//        wordJapanText = search.getWordJapan();
//        wordJapan.setText(wordJapanText);
//        defenition_JapanText = search.getDefenition_Japan();
//        defenition_Japan.setText(defenition_JapanText);

   // }

    class TestAsyncTask extends AsyncTask<String, String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }



        @Override
        protected String doInBackground(String... strings) {


           // wordEnglishText = wordEnglish.getText().toString();


                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("wordEnglish", strings[0]));

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
                   // wordEnglishText = (json_data.getString("wordEnglish"));
                    defenitionEnglishText = (json_data.getString("defenitionEnglish"));
                    wordRussianText = (json_data.getString("wordRussian"));
                    defenitionRussianText = (json_data.getString("defenitionRussian"));
                    wordChinaText = (json_data.getString("wordChina"));
                    defenitionChinaText = (json_data.getString("defenitionChina"));
                    wordJapanText = (json_data.getString("wordJapan"));
                    defenition_JapanText = (json_data.getString("defenition_Japan"));
                    Log.e("pass 3", wordEnglishText);

                } catch (Exception e) {
                    Log.e("Fail 3", e.toString());
                }

                String[] words = new String[7];

                words[0] = defenitionEnglishText;
            words[1] = wordRussianText;
            words[2] = defenitionRussianText;
            words[3] = wordChinaText;
            words[4] = defenitionChinaText;
            words[5] = wordJapanText;
            words[6] = defenition_JapanText;


                for(int i = 0; i < words.length;i++){
                    publishProgress(words[i]);
            }

//            publishProgress(defenitionEnglishText);
//            publishProgress(wordRussianText);
//            publishProgress(defenitionRussianText);
//            publishProgress(wordChinaText);
//            publishProgress(defenitionChinaText);
//            publishProgress(wordJapanText);
//            publishProgress(defenition_JapanText);


            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            System.out.println(values[0]);
            defenitionEnglish.setText(values[0]);
            wordRussian.setText(values[0]);
            defenitionRussian.setText(values[0]);
            wordChina.setText(values[0]);
            defenitionChina.setText(values[0]);
            wordJapan.setText(values[0]);
            defenition_Japan.setText(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            defenitionEnglish.setText(defenitionEnglishText);
            wordRussian.setText(wordRussianText);
            defenitionRussian.setText(defenitionRussianText);
            wordChina.setText(wordChinaText);
            defenitionChina.setText(defenitionChinaText);
            wordJapan.setText(wordJapanText);
            defenition_Japan.setText(defenitionChinaText);
        }

    }
}
