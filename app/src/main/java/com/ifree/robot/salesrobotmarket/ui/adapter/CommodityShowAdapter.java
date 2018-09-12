package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommodityShowEntity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;


import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class CommodityShowAdapter extends RecyclerView.Adapter<CommodityShowAdapter.CommodityShowViewHolder> {

    private Context context;
    private List<CommodityShowEntity.DataBean.CarProductBean> showlist;

    public CommodityShowAdapter(Context context, List<CommodityShowEntity.DataBean.CarProductBean> showlist) {
        this.context = context;
        this.showlist = showlist;
    }

    @NonNull
    @Override
    public CommodityShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_show, parent, false);
        CommodityShowViewHolder viewHolder = new CommodityShowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommodityShowViewHolder viewHolder, int position) {
        CommodityShowEntity.DataBean.CarProductBean carProductBean = showlist.get(position);
        GlideUtils.imageLoader(context,carProductBean.getPic(),viewHolder.ivShow);
        viewHolder.tvShowmo.setText(carProductBean.getPrice()+"元");
        viewHolder.tvShowname.setText(carProductBean.getIntroduce());
    }

    @Override
    public int getItemCount() {
        return showlist == null ? 0 : showlist.size();
    }

    class CommodityShowViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShow;
        TextView tvShowmo;
        TextView tvShowname;
        CommodityShowViewHolder(View itemView) {
            super(itemView);
            ivShow = itemView.findViewById(R.id.iv_show);
            tvShowmo = itemView.findViewById(R.id.tv_showmo);
            tvShowname = itemView.findViewById(R.id.tv_showname);
        }
    }
}
