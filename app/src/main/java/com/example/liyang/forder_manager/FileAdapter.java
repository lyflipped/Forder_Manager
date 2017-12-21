package com.example.liyang.forder_manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by liyang on 2017/12/18.
 */

public class FileAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Bitmap mIcon;
    private List<String> paths;

    public FileAdapter(Context context, List<String> paths) {
        this.mInflater = LayoutInflater.from(context);
        this.paths = paths;
        this.mIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder_48);
    }

    public int getCount() {
        return paths.size();
    }

    public Object getItem(int position) {
        return paths.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.file_item, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.file_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.file_icon);
           // holder.IsSelected = (CheckBox) convertView.findViewById(R.id.file_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setImageBitmap(mIcon);
        holder.text.setText(new File(paths.get(position).toString()).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView text;
        ImageView icon;
        //CheckBox IsSelected;
    }
}
