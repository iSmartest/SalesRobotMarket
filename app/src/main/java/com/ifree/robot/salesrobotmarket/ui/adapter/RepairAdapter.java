package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.RepairEntity;
import com.ifree.robot.salesrobotmarket.ui.activity.AfterSaleDetailsActivity;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {
    private Context context;
    private List<RepairEntity.DataBean.RepairListBean> mList;
    private int type;
    private int cid;

    public RepairAdapter(Context context, List<RepairEntity.DataBean.RepairListBean> mList, int cid, int type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
        this.cid = cid;
    }

    @NonNull
    @Override
    public RepairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_maintain, parent, false);
        RepairViewHolder viewHolder = new RepairViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepairViewHolder viewHolder, int position) {
        final RepairEntity.DataBean.RepairListBean maintainListBean = mList.get(position);
        viewHolder.tvContent.setText(maintainListBean.getContent());
        viewHolder.tvDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AfterSaleDetailsActivity.class);
                intent.putExtra(Constant.INTENTINSPECTID, maintainListBean.getId());
                intent.putExtra(Constant.INTENTINSPECTTYPE, type);
                intent.putExtra(Constant.CUSTOMERID, cid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class RepairViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        TextView tvDes;

        RepairViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_mtcontent);
            tvDes = itemView.findViewById(R.id.tv_mtdetails);
        }
    }
}
