package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.HotCarEntity;


import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class BrightPointAdapter extends RecyclerView.Adapter<BrightPointAdapter.BrightPointViewHolder> {

    private List<HotCarEntity.DataBean.CarBean.BrightPointBean> mList;
    private Context context;

    public BrightPointAdapter(Context context, List<HotCarEntity.DataBean.CarBean.BrightPointBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @NonNull
    @Override
    public BrightPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brightpoint, parent, false);
        BrightPointViewHolder viewHolder = new BrightPointViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrightPointViewHolder viewHolder, int position) {
        HotCarEntity.DataBean.CarBean.BrightPointBean brightPointBean = mList.get(position);
        viewHolder.tvHotCarBrigh.setText(brightPointBean.getSellPoint());
    }


    class BrightPointViewHolder extends RecyclerView.ViewHolder {
        TextView tvHotCarBrigh;
        public BrightPointViewHolder(View itemView) {
            super(itemView);
            tvHotCarBrigh = itemView.findViewById(R.id.tv_hotcarbrigh);
        }
    }
}
