package com.ifree.robot.salesrobotmarket.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.custom.FlowGroupView;
import com.ifree.robot.salesrobotmarket.service.entity.AfterSaleDecEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.AfterSaleDecPresenter;
import com.ifree.robot.salesrobotmarket.service.view.AfterSaleDecView;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class AfterSaleDetailsActivity extends BaseActivity {
    private AfterSaleDecPresenter mAfterSaleDecPresenter;
    @BindView(R.id.flow_view_group)
    FlowGroupView flowViewGroup;
    @BindView(R.id.lldeailsall)
    LinearLayout lldeailsall;
    @BindView(R.id.tv_indealis1)
    TextView tvIndealis1;
    @BindView(R.id.tv_indealis2)
    TextView tvIndealis2;
    @BindView(R.id.tv_indealis3)
    TextView tvIndealis3;
    private ArrayList<String> names;
    private int id=0;
    private int type=1;
    private int cid=1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sale_details;
    }

    @Override
    protected void initView() {
        hideBack(1);
        lldeailsall.setVisibility(View.GONE);
        mAfterSaleDecPresenter = new AfterSaleDecPresenter(context);
        names = new ArrayList<String>();
        int intExtra = getIntent().getIntExtra(Constant.INTENTINSPECTID, 1);
        id=intExtra;
        int intExtra1 = getIntent().getIntExtra(Constant.INTENTINSPECTTYPE, 1);
        type=intExtra1;
        int intExtra2 = getIntent().getIntExtra(Constant.CUSTOMERID, 1);
        cid=intExtra2;

    }

    @Override
    protected void loadData() {
        mAfterSaleDecPresenter.onCreate();
        mAfterSaleDecPresenter.attachView(mAfterSaleDecView);
        mAfterSaleDecPresenter.getQuestAfterSaleDec(storeId,""+cid,""+type,""+id,"加载中...");
    }

    private AfterSaleDecView mAfterSaleDecView = new AfterSaleDecView() {
        @Override
        public void onSuccess(AfterSaleDecEntity mAfterSaleDecEntity) {
            if (mAfterSaleDecEntity.getResultCode().equals("1")) {
                lldeailsall.setVisibility(View.GONE);
                ToastUtils.makeText(context, mAfterSaleDecEntity.getMsg());
                return;
            }
            AfterSaleDecEntity.DataBean data = mAfterSaleDecEntity.getData();
            AfterSaleDecEntity.DataBean.CommonBean common = data.getCommon();
            lldeailsall.setVisibility(View.VISIBLE);
            tvIndealis1.setText("车型：" + common.getCarName());
            tvIndealis2.setText("牌号：" + common.getCarPhone());
            tvIndealis3.setText("公里数：" + common.getCarName());
            List<String> itemList = data.getItemList();
            names.clear();
            names.addAll(itemList);
            setTwoFlowLayout();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };

    private void setTwoFlowLayout() {
        for (int i = 0; i < names.size(); i++) {
            addTextView(names.get(i));
        }
    }
    private void addTextView(String s) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.shape_sideline);
        child.setText(s);
        child.setTextColor(Color.BLACK);
        child.setTextSize(18);
        child.setPadding(15, 15, 15, 15);
        flowViewGroup.addView(child);
    }
}
