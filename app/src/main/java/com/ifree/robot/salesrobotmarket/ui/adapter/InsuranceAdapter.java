package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.InsuranceEntity;
import com.ifree.robot.salesrobotmarket.utils.DateUtils;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.InsuranceViewHolder> {

    private List<InsuranceEntity.DataBean.RepairListBean> mList;
    private Context context;

    public InsuranceAdapter(Context context, List<InsuranceEntity.DataBean.RepairListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public InsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_insurance, parent, false);
        InsuranceViewHolder viewHolder = new InsuranceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceViewHolder viewHolder, int position) {
        /**
         * date : 1530062169000
         * records : ["张献忠进行了保险！！！"]
         * name : 中国人寿
         * items : 全险 强险 局部险
         */

        InsuranceEntity.DataBean.RepairListBean repairListBean = mList.get(position);
        viewHolder.tvInsurance1.setText("投保公司：  " + repairListBean.getName());
        long date = repairListBean.getDate();
        String time = DateUtils.getDateToString(date, "yyyy-MM-dd");
        viewHolder.tvInsurance2.setText("投保时间：  " + time);
        viewHolder.tvInsurance3.setText("投保种类：  " + repairListBean.getItems());
        List<String> records = repairListBean.getRecords();
        StringBuffer resultBuffer = new StringBuffer();
        for (int i = 0; i < records.size(); i++) {
            String s = records.get(i);
            resultBuffer.append(s + "\n");
        }
        viewHolder.tvInsurance4.setText(resultBuffer.toString());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class InsuranceViewHolder extends RecyclerView.ViewHolder {
        TextView tvInsurance1;
        TextView tvInsurance2;
        TextView tvInsurance3;
        TextView tvInsurance4;

        public InsuranceViewHolder(View itemView) {
            super(itemView);
            tvInsurance1 = itemView.findViewById(R.id.tv_insur1);
            tvInsurance2 = itemView.findViewById(R.id.tv_insur2);
            tvInsurance3 = itemView.findViewById(R.id.tv_insur3);
            tvInsurance4 = itemView.findViewById(R.id.tv_insur4);
        }
    }
}
