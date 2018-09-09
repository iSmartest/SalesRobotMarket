package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.CommodityIntroduceEntity;
import com.ifree.robot.salesrobotmarket.ui.activity.VideoActivity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;

import java.util.List;

/**
 * Created by za on 2018/6/19.
 */

public class CommodityIntroduceAdapter extends RecyclerView.Adapter<CommodityIntroduceAdapter.CommodityIntroduceViewHolder> {
    private Context context;
    private List<CommodityIntroduceEntity.DataBean.CarVideoBean> mList;

    public CommodityIntroduceAdapter(Context context, List<CommodityIntroduceEntity.DataBean.CarVideoBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CommodityIntroduceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_introduce, parent, false);
        CommodityIntroduceViewHolder viewHolder = new CommodityIntroduceViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CommodityIntroduceViewHolder holder, int position) {
        final CommodityIntroduceEntity.DataBean.CarVideoBean carVideoBean = mList.get(position);
        GlideUtils.imageLoader(context,carVideoBean.getImg(),holder.ivBrief);
        holder.tvBrief.setText(carVideoBean.getName()+carVideoBean.getIdea());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra(Constant.VIDEO,carVideoBean.getAddress());
                context.startActivity(intent);
            }
        });
    }

    class CommodityIntroduceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBrief;
        TextView tvBrief;
        CommodityIntroduceViewHolder(View itemView) {
            super(itemView);
            ivBrief = itemView.findViewById(R.id.iv_brief);
            tvBrief = itemView.findViewById(R.id.tv_brief);
        }
    }
}
