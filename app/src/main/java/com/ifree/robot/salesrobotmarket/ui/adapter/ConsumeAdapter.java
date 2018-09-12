package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsumeEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class ConsumeAdapter extends RecyclerView.Adapter<ConsumeAdapter.ConsumeViewHolder> {
    @BindView(R.id.tv_consume1)
    TextView tvConsume1;
    @BindView(R.id.tv_consume2)
    TextView tvConsume2;
    private List<ConsumeEntity.DataBean.ConsumptionListBean> list;

    public ConsumeAdapter(List<ConsumeEntity.DataBean.ConsumptionListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ConsumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consume, parent, false);
        ConsumeViewHolder viewHolder = new ConsumeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumeViewHolder viewHolder, int position) {
        ConsumeEntity.DataBean.ConsumptionListBean consumptionListBean = list.get(position);
        viewHolder.tvConsume1.setText(consumptionListBean.getContent());
        viewHolder.tvConsume2.setText(consumptionListBean.getMoney() + "");
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    class ConsumeViewHolder extends RecyclerView.ViewHolder {
        TextView tvConsume1;
        TextView tvConsume2;

        ConsumeViewHolder(View itemView) {
            super(itemView);
            tvConsume1 = itemView.findViewById(R.id.tv_consume1);
            tvConsume2 = itemView.findViewById(R.id.tv_consume2);

        }
    }
}
