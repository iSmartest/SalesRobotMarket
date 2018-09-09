package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.ui.activity.SellingShowActivity;
import com.ifree.robot.salesrobotmarket.ui.activity.VideoActivity;

import java.util.List;


/**
 * Created by za on 2018/7/25.
 */

public class ConSellingAdapter extends RecyclerView.Adapter<ConSellingAdapter.ConSellingViewHolder> {

    private Context context;
    private List<ConsultEntity.DataBean.BrightPointBean> brightPoint;

    public ConSellingAdapter(Context context,List<ConsultEntity.DataBean.BrightPointBean> brightPoint) {
        this.context = context;
        this.brightPoint = brightPoint;
    }

    @NonNull
    @Override
    public ConSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_conselling, parent, false);
        ConSellingViewHolder viewHolder = new ConSellingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConSellingViewHolder viewHolder, int position) {
        final ConsultEntity.DataBean.BrightPointBean brightPointBean = brightPoint.get(position);
        viewHolder.tvConselling.setText(brightPointBean.getSellPoint());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = brightPointBean.getType();
                Bundle bundle = new Bundle();
                switch (type){
                    case 1:
                        bundle.putSerializable(Constant.SELLING,brightPointBean);
                        MyApplication.openActivity(context,SellingShowActivity.class,bundle);
                        break;
                    case 2:
                        bundle.putSerializable(Constant.SELLING,brightPointBean);
                        MyApplication.openActivity(context,SellingShowActivity.class,bundle);
                        break;
                    case 3:
                        bundle.putString(Constant.VIDEO,brightPointBean.getSellPointvid());
                        MyApplication.openActivity(context,VideoActivity.class,bundle);
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (brightPoint != null && brightPoint.size() > 0) {
            return brightPoint.size();
        }
        return 0;
    }

    class ConSellingViewHolder extends RecyclerView.ViewHolder {
        TextView tvConselling;
        public ConSellingViewHolder(View itemView) {
            super(itemView);
            tvConselling = itemView.findViewById(R.id.tv_conselling);
        }
    }
}
