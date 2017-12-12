package com.example.tmoraes.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RequestActivity extends AppCompatActivity {
    private static String URL = "https://taiappx.herokuapp.com/";
    private String request;
    private String response;
    private static RequestData requestData = new RequestData();
    private TextView txtuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String user = b.getString("Usuario");
        requestData.setUser(user);

        txtuser = (TextView)findViewById(R.id.txtUser);

        txtuser.setText(user);


    }

    public void set1(View view) {
        requestData.setType(1);

        SendRequest();
    }

    public void set2(View view) {
        requestData.setType(1);
        SendRequest();
    }

    public void SendRequest(){
        String url = URL + "appnew";
        try{
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("ID", user.getID());
            jsonObject.put("name", requestData.getUser());
            //jsonObject.put("description" , "none teste");
            jsonObject.put("type", requestData.getType());
            //jsonObject.put("date" , "Amanha");


            final String mRequestBody = jsonObject.toString();

            JsonObjectRequest jor = new JsonObjectRequest(com.android.volley.Request.Method.POST, url,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try {
                        if(response.get("msg") == "OK"){
                            //call ListActiviity
                        }else{
                            //mensagem error
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try{
                        return mRequestBody == null ? null :
                                mRequestBody.getBytes("utf-8");
                    }catch (UnsupportedEncodingException e){
                        return null;
                    }
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                    if(response.statusCode == 200){

                    }
                    return super.parseNetworkResponse(response);
                }

            };

            Volley.newRequestQueue(this).add(jor);
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);

            startActivity(intent);
        }
        catch (JSONException e){
        }
    }
}

