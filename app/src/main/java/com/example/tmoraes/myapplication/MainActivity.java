package com.example.tmoraes.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private User user;
     EditText usuario ;
     EditText senha ;
    private static String URL = "https://taiappx.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText)findViewById(R.id.edtxtUser);
        senha = (EditText)findViewById(R.id.edtxtPass);

        Button cadastrar = (Button)findViewById(R.id.btnSigin);
        Button logar = (Button)findViewById(R.id.btnLogin);

        user = new User();
    }

    public void Cadastrar(View view) {
        String url = URL + "cadastrar";

        String name = usuario.getText().toString();
        String pass  = senha.getText().toString();

        try{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("email" , name);
            jsonObject.put("password",pass);
            final String mRequestBody = jsonObject.toString();

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try {
                        if(response.get("status") == "OK"){
                            //call RequestActivity
                           // user.setID(Integer.parseInt(response.getString("ID")));
                            //user.setNome(response.getString("name"));

                            Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                            intent.putExtra("Usuario", user);
                            startActivity(intent);
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
        }
        catch (JSONException e){

        }
    }

    public void Logar(View view) {
        String url = URL + "logar";
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email" , usuario.getText().toString());
            jsonObject.put("password", senha.getText().toString());
            final String mRequestBody = jsonObject.toString();

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try {
                        if(!response.getString("msg").equals(null)){
                            //call RequestActivity
                            //user.setID(Integer.parseInt(response.getString("ID")));
                            //user.setNome(response.getString("name"));

                            Intent intent = new Intent(getApplicationContext(), RequestActivity.class);
                            intent.putExtra("Usuario", user);
                            startActivity(intent);
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
        }
        catch (JSONException e){

        }
    }
}
