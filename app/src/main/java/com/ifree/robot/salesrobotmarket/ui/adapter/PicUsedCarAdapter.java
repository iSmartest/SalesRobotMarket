package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.ui.activity.UsedCarPicActivity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class PicUsedCarAdapter extends RecyclerView.Adapter<PicUsedCarAdapter.PicUsedCarViewHolder> {

    private List<String> list;
    private Context context;

    public PicUsedCarAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PicUsedCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pic_used_car, parent, false);
        PicUsedCarViewHolder viewHolder = new PicUsedCarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PicUsedCarViewHolder viewHolder, final int position) {
        String uri = list.get(position);
        GlideUtils.imageLoader(context,uri,viewHolder.ivPicsd);
        viewHolder.ivPicsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UsedCarPicActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("pic", (Serializable) list);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class PicUsedCarViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicsd;
        public PicUsedCarViewHolder(View itemView) {
            super(itemView);
            ivPicsd = itemView.findViewById(R.id.iv_picsd);
        }
    }
}
