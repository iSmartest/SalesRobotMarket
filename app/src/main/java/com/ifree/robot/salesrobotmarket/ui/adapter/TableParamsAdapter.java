package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

class TableParamsAdapter extends android.widget.BaseAdapter {

    private Context context;
    private List<String> listList;
    private String[] strings;

    public TableParamsAdapter(Context context, List<String> listList, String[] strings) {
        this.context = context;
        this.listList = listList;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return listList == null ? 0 : 10;
    }

    @Override
    public Object getItem(int position) {
        return listList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableParamsViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_textview, parent,false);
            viewHolder = new TableParamsViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_ttable1);
            viewHolder.params = convertView.findViewById(R.id.tv_ttable2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (TableParamsViewHolder) convertView.getTag();
        }
        if (listList.size() < 10 && position == 9) {
            viewHolder.name.setText(strings[position]);
            viewHolder.params.setText("");
        } else {
            viewHolder.name.setText(strings[position]);
            viewHolder.params.setText(listList.get(position));
        }
        return convertView;
    }

    class TableParamsViewHolder{
        TextView name;
        TextView params;
    }
}
