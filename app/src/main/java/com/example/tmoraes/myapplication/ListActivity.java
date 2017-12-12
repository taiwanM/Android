package com.example.tmoraes.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private Chronometer chronometer;
    private final String URL = "https://taiappx.herokuapp.com/";
    private ArrayList<RequestData> items = new ArrayList<>();
    private MyAdapter myAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.list);
        chronometer = (Chronometer)findViewById(R.id.chronometer);

        items = getItensList();

        myAdapter = new MyAdapter(this,R.layout.list_view_items, items);

        chronometer.start();

    }

    private ArrayList<RequestData> getItensList() {

        String url = URL + "applist";
        try{
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // Do something with response
                            //mTextView.setText(response.toString());
                            Log.v("Infor","registroOK");
                            // Process the JSON
                            try{
                                // Loop through the array elements
                                for(int i=0;i<response.length();i++){
                                    // Get current json object
                                    JSONObject user = response.getJSONObject(i);
                                    String name = null;
                                    String type = null;
                                    String date = null;
                                    Object o = null;
                                    try {
                                        o = response.get(i);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    if(o instanceof JSONArray) {
                                        JSONArray a = (JSONArray)o;
                                        for(int j=0;i<a.length();i++) {
                                             name = user.getString("name");
                                             type = user.getString("type");
                                             date = user.getString("date");
                                        }

                                    } else if(o instanceof JSONObject) {
                                         name = user.getString("name");
                                         type = user.getString("type");
                                         date = user.getString("date");
                                    }

                                    // Get the current student (json object) data


                                    RequestData rd = new RequestData(name, Integer.parseInt(type), date);
                                    items.add(rd);
                                    listView.setAdapter(myAdapter);
                                    // Display the formatted json data in text view
                                    //mTextView.append(firstName +" " + lastName +"\nAge : " + age);
                                    //mTextView.append("\n\n");
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            // Do something when error occurred

                        }
                    }
            );

            Volley.newRequestQueue(this).add(jsonArrayRequest);
        }
        catch (Exception e){

        }

        return items;
    }
}
