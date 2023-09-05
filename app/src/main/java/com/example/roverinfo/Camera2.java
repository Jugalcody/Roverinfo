package com.example.roverinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Camera2 extends AppCompatActivity {
TextView t,t2;
ImageView im;
SharedPreferences s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        t=findViewById(R.id.cam);
        t2=findViewById(R.id.dat);
        im=findViewById(R.id.imag);
        s=getSharedPreferences("rover",MODE_PRIVATE);
        String k=s.getString("Camera","");
        t.setText("Camera Name : "+k);
        t2.setText("Captured Date : "+s.getString("capdate",""));
        Picasso.get().load(s.getString("link","")).into(im);

    }
}