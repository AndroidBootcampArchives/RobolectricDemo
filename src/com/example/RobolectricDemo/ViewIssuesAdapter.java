package com.example.RobolectricDemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ViewIssuesAdapter extends ArrayAdapter<Issue>{


    private final Context context;

    public ViewIssuesAdapter(Context context, int resource, int textViewResourceId, List<Issue> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.issue_item, parent, false);
        } else {
            rowView = convertView;
        }
        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(getItem(position).getTitle());
        return rowView;
    }

}
