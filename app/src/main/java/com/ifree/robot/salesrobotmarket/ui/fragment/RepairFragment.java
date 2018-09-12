package com.ifree.robot.salesrobotmarket.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.RepairEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.RepairPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.RepairView;
import com.ifree.robot.salesrobotmarket.ui.adapter.RepairAdapter;
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

public class RepairFragment extends BaseFragment {
    private RepairPresenter mRepairPresenter;
    @BindView(R.id.rl_maintain)
    RecyclerView rlMaintian;
    @BindView(R.id.tv_name_maintain)
    TextView tvName;
    List<RepairEntity.DataBean.RepairListBean> mList = new ArrayList<>();
    private RepairAdapter repairAdapter;
    private int id=1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_maintain;
    }

    @Override
    protected void initView() {
        mRepairPresenter = new RepairPresenter(context);
        tvName.setVisibility(View.GONE);
        LoadEntity.DataBean data = (LoadEntity.DataBean)bundle.getSerializable(Constant.INSPECTDATA);
        id = data.getId();
        rlMaintian.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        repairAdapter = new RepairAdapter(context, mList,id,2);
        rlMaintian.setAdapter(repairAdapter);
    }

    @Override
    protected void initData() {
        mRepairPresenter.onCreate();
        mRepairPresenter.attachView(mRepairView);
        mRepairPresenter.getQuestRepair(storeId,id+"","2","加载中...");
    }

    private RepairView mRepairView = new RepairView() {
        @Override
        public void onSuccess(RepairEntity mRepairEntity) {
            if (mRepairEntity.getResultCode().equals("1")){
                tvName.setVisibility(View.GONE);
                ToastUtils.makeText(getActivity(),mRepairEntity.getMsg());
                return;
            }
            List<RepairEntity.DataBean.RepairListBean> maintainlist = mRepairEntity.getData().getRepairList();
            if(maintainlist != null && !maintainlist.isEmpty() && maintainlist.size() > 0){
                mList.addAll(maintainlist);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(maintainlist.get(0).getName().concat("维修记录"));
                repairAdapter.notifyDataSetChanged();
            }else {
                tvName.setVisibility(View.GONE);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(getActivity(),result);

        }
    };
}
