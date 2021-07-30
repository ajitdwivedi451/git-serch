package com.ajit.github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;

public class ProfileActivity extends AppCompatActivity {


    String user;
    ImageView profile;
    TextView bio, followers, following;
    String url = "https://api.github.com/users/";
    RecyclerView mrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Log.d("user",getIntent().getStringExtra("username"));
        user= getIntent().getStringExtra("username");
        url=url+user;
        profile = (ImageView) findViewById(R.id.Pimage);
        bio = (TextView) findViewById(R.id.pbio);
        followers = (TextView) findViewById(R.id.Pfollowers);
        following = (TextView) findViewById(R.id.Pfollowing);
        mrecyclerview = (RecyclerView) findViewById(R.id.repolist);
        loaddata(url);
        loadrespository(url);


    }

    public void loaddata(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageurl=response.getString("avatar_url");
                            String followingc=response.getString("following");
                            String followersc=response.getString("followers");
                            String bioc=response.getString("bio");
                            followers.setText("followers:"+followersc);
                            following.setText("Following :"+followingc);
                            bio.setText("BIO:"+bioc);
                            Glide.with(ProfileActivity.this).load(imageurl).into(profile);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "error occur..", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);
    }
    public void loadrespository(String url){
        url=url+"/repos?per_page=24&sort=created";
        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder=new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        Repository[] repoarray=gson.fromJson(response,Repository[].class);
                        mrecyclerview.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
                        mrecyclerview.setHasFixedSize(true);
                        mrecyclerview.setAdapter(new RepositoryAdaptor(ProfileActivity.this,repoarray));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "Errors found", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

}
