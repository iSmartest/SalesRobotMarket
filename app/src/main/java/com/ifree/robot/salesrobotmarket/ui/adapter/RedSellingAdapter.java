package com.ifree.robot.salesrobotmarket.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class RedSellingAdapter extends RecyclerView.Adapter<RedSellingAdapter.RedSellingViewHolder> {
    private Context context;
    private List<String> brightPoint;

    public RedSellingAdapter(Context context, List<String> brightPoint) {
        this.context = context;
        this.brightPoint = brightPoint;
    }

    @NonNull
    @Override
    public RedSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_red_selling, parent, false);
        RedSellingViewHolder viewHolder = new RedSellingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RedSellingViewHolder viewHolder, int position) {
        viewHolder.tvSelling.setText((position+1) + "、"+brightPoint.get(position));
    }

    @Override
    public int getItemCount() {

        return brightPoint == null ? 0 : brightPoint.size();
    }

    class RedSellingViewHolder extends RecyclerView.ViewHolder {
        TextView tvSelling;
        public RedSellingViewHolder(View itemView) {
            super(itemView);
            tvSelling = itemView.findViewById(R.id.tv_adapter_selling);
        }
    }
}
