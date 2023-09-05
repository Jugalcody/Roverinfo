package com.example.roverinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

public class Home extends AppCompatActivity {
ImageView sicon;
EditText sbar;
String url="https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=";
String date="";
String temp="";
String api="&api_key=ZEGMbvHl76CJSZPncEMgy2H1uVwFudNwULeXPJjK";
RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sicon=findViewById(R.id.sicon);
        sbar=findViewById(R.id.sbar);
        rec=findViewById(R.id.rec);
        sicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = sbar.getText().toString();
                if(!date.equals("")) {
                    temp=url+date+api;


                }
                JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, temp, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray r=response.getJSONArray("photos");
                            String[][] arr=new String[r.length()][5];

                            for (int i=0;i<r.length();i++){
                                String[] arr2=new String[9];
                                JSONObject rov=r.getJSONObject(i).getJSONObject("rover");
                                JSONObject cam=r.getJSONObject(i).getJSONObject("camera");

                                arr2[0]=rov.getString("name");
                                arr2[1]=rov.getString("status");
                                arr2[2]=rov.getString("launch_date");
                                arr2[3]=rov.getString("landing_date");
                                arr2[4]=rov.getString("total_photos");
                                arr2[5]=cam.getString("full_name");
                               arr2[6]=r.getJSONObject(i).getString("img_src");
                                arr2[7]=r.getJSONObject(i).getString("earth_date");
                             arr[i]=arr2;
                                rec.setAdapter(new Adapter1(Home.this,arr));

                            }


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }





                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue q= Volley.newRequestQueue(Home.this);

                q.add(j);
            }}


        );



            }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finishAffinity();
    }
}

