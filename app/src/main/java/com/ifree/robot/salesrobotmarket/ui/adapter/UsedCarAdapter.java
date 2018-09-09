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
import com.ifree.robot.salesrobotmarket.service.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.ui.activity.UsedCarInfoActivity;
import com.ifree.robot.salesrobotmarket.utils.DateUtils;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;
import com.ifree.robot.salesrobotmarket.utils.PriceUtils;

import java.util.List;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class UsedCarAdapter extends RecyclerView.Adapter<UsedCarAdapter.UsedCarViewHolder> {
    private Context context;
    private List<UsedCarEntity.DataBean.CarBean> data;

    public UsedCarAdapter(Context context, List<UsedCarEntity.DataBean.CarBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public UsedCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_used_car, parent, false);
        UsedCarViewHolder viewHolder = new UsedCarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsedCarViewHolder viewHolder, int position) {
        final UsedCarEntity.DataBean.CarBean carListBean = data.get(position);
        GlideUtils.imageLoader(context,carListBean.getImage(),viewHolder.ivTseond);
        viewHolder.tvUsedCar1.setText(carListBean.getName());
        long time = carListBean.getTime();
        String dateToString = DateUtils.getDateToString(time, "yyy-MM-dd");
        viewHolder.tvUsedCar2.setText("上牌时间    "+dateToString);
        viewHolder.tvUsedCar3.setText("公里表显里程  "+carListBean.getMileage()+"km");
        viewHolder.tvUsedCar4.setText("车主报价： "+ PriceUtils.getprice(carListBean.getPrice()));
        int isNew = carListBean.getIsNew();
        if(isNew==1){
            viewHolder.tvUsedCar5.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tvUsedCar5.setVisibility(View.GONE);
       }
      int mold = carListBean.getMold();
        switch (mold){
           case 2:
               viewHolder.ivUsedCarright.setImageResource(R.drawable.label2);
               viewHolder.tvItemDown.setText("急降");
               viewHolder.tvItemMoney.setVisibility(View.VISIBLE);
               String getprice = PriceUtils.getprice(carListBean.getMoney());
               viewHolder.tvItemMoney.setText(getprice);
               break;
           case 1:
               viewHolder.ivUsedCarright.setImageResource(R.drawable.label1);
               viewHolder.tvItemDown.setText("新上架");
               viewHolder.tvItemMoney.setVisibility(View.GONE);
               break;
       }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UsedCarInfoActivity.class);
                intent.putExtra(Constant.INTENTINSPECTID,carListBean);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    class UsedCarViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTseond;
        TextView tvUsedCar1;
        TextView tvUsedCar2;
        TextView tvUsedCar3;
        TextView tvUsedCar4;
        ImageView ivUsedCarright;
        TextView tvItemDown;
        TextView tvItemMoney;
        TextView tvUsedCar5;
        UsedCarViewHolder(View itemView) {
            super(itemView);
            ivTseond = itemView.findViewById(R.id.iv_tseond);
            tvUsedCar1 = itemView.findViewById(R.id.tv_second1);
            tvUsedCar2 = itemView.findViewById(R.id.tv_second2);
            tvUsedCar3 = itemView.findViewById(R.id.tv_second3);
            tvUsedCar4 = itemView.findViewById(R.id.tv_second4);
            tvUsedCar5 = itemView.findViewById(R.id.tv_second5);
            ivUsedCarright = itemView.findViewById(R.id.iv_secondright);
            tvItemDown = itemView.findViewById(R.id.tv_item_down);
            tvItemMoney = itemView.findViewById(R.id.tv_item_money);
        }
    }
}
