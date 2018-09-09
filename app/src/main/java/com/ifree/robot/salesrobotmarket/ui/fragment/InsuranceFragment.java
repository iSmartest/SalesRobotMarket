package com.ifree.robot.salesrobotmarket.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.InsuranceEntity;
import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.InsurancePresenter;
import com.ifree.robot.salesrobotmarket.service.view.InsuranceView;
import com.ifree.robot.salesrobotmarket.ui.adapter.InsuranceAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseFragment;
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

public class InsuranceFragment extends BaseFragment {
    private InsurancePresenter mInsurancePresenter;
    @BindView(R.id.rl_maintain)
    RecyclerView rvInsurance;
    @BindView(R.id.tv_name_maintain)
    TextView tvName;
    private InsuranceAdapter insuranceAdapter;
    private int id=1;
    private List<InsuranceEntity.DataBean.RepairListBean> myList;
    @Override
    protected int getLayout() {
        return R.layout.fragment_maintain;
    }


    @Override
    protected void initView() {
        tvName.setVisibility(View.GONE);
        mInsurancePresenter = new InsurancePresenter(context);
        LoadEntity.DataBean data = (LoadEntity.DataBean)bundle.getSerializable(Constant.INSPECTDATA);
        id = data.getId();
        myList=new ArrayList<>();
        rvInsurance.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        insuranceAdapter = new InsuranceAdapter(context,myList);
        rvInsurance.setAdapter(insuranceAdapter);
    }

    @Override
    protected void initData() {
        mInsurancePresenter.onCreate();
        mInsurancePresenter.attachView(mInsuranceView);
        mInsurancePresenter.getQuestInsurance(storeId,""+id,"3","加载中...");
    }

    private InsuranceView mInsuranceView = new InsuranceView() {
        @Override
        public void onSuccess(InsuranceEntity mInsuranceEntity) {
            if (mInsuranceEntity.getResultCode().equals("1")){
                ToastUtils.makeText(getActivity(),mInsuranceEntity.getMsg());
                return;
            }
            List<InsuranceEntity.DataBean.RepairListBean> repairList = mInsuranceEntity.getData().getRepairList();
            if(repairList != null && !repairList.isEmpty() && repairList.size() > 0){
                myList.addAll(repairList);
                insuranceAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(getActivity(),result);

        }
    };
}
