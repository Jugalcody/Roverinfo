package com.example.roverinfo;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    private String infoarr[][];
    Context con;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rover_row,parent,false);
        return new ViewHolder(view);
    }
public Adapter1(Context c, String[][] infoarr2){
        infoarr=infoarr2;
        con=c;
}
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

holder.name.setText("Rover name : "+infoarr[position][0]);
        holder.status.setText("Status : "+infoarr[position][1]);
        holder.launchd.setText("Launch date : "+infoarr[position][2]);
        holder.landd.setText("Landing date : "+infoarr[position][3]);
        holder.tphoto.setText("Total photos taken : "+infoarr[position][4]);
        int pos=position;
        holder.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(con, Camera2.class);
                SharedPreferences s=con.getSharedPreferences("rover",con.MODE_PRIVATE);
               s.edit().putString("Camera",infoarr[pos][5]).apply();
               s.edit().putString("link",infoarr[pos][6]).apply();
             s.edit().putString("capdate",infoarr[pos][7]).apply();
                startActivity(con,i,null);


            }
        });
    }

    @Override
    public int getItemCount() {
        return infoarr.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,status,launchd,landd,tphoto,camera;
        public ViewHolder(View view){

            super(view);

         name=(TextView) view.findViewById(R.id.name);
         status=(TextView) view.findViewById(R.id.status);
            launchd=(TextView) view.findViewById(R.id.launchd);
            landd=(TextView) view.findViewById(R.id.landd);
            tphoto=(TextView) view.findViewById(R.id.tphoto);
            camera=(TextView) view.findViewById(R.id.cinfo);
        }
    }

}

