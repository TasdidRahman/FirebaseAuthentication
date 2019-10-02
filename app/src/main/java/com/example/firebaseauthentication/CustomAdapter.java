package com.example.firebaseauthentication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<HandleDatabase> {

    private Activity context;
    private List<HandleDatabase> handleDatabaseList;


    public CustomAdapter( Activity context, List<HandleDatabase> handleDatabaseList) {
        super(context, R.layout.sample_layout, handleDatabaseList);
        this.context = context;
        this.handleDatabaseList = handleDatabaseList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater =context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);

        HandleDatabase handleDatabase = handleDatabaseList.get(position);

        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.ageTextViewId);

        t1.setText("Name: "+handleDatabase.getName());
        t2.setText("Age: "+handleDatabase.getAge());

        return view;


    }
}
