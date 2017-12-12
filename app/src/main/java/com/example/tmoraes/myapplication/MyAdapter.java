package com.example.tmoraes.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tmoraes on 12/12/2017.
 */

public class MyAdapter extends ArrayAdapter<RequestData> {

    ArrayList<RequestData> arrayList = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<RequestData> objects) {
        super(context, textViewResourceId, objects);

        arrayList = objects;
    }

    @Override
    public int getCount() {
        int count=arrayList.size(); //counts the total number of elements from the arrayList.
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_view_items, null);
        TextView txtUser = (TextView) v.findViewById(R.id.txtUser);
        TextView txtType = (TextView) v.findViewById(R.id.txtType);
        TextView txtData = (TextView) v.findViewById(R.id.txtDate);
        //ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        txtUser.setText(arrayList.get(position).getUser());
        txtType.setText(Integer.toString(arrayList.get(position).getType()));
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dataString = df.format(Calendar.getInstance().getTime());
        txtData.setText(dataString);

        // imageView.setImageResource(animalList.get(position).getAnimalImage());
        return v;
    }
}
