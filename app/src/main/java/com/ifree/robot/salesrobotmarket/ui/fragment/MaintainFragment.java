package com.ifree.robot.salesrobotmarket.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.service.entity.MaintainEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.MaintainPresenter;
import com.ifree.robot.salesrobotmarket.service.presenter.RepairPresenter;
import com.ifree.robot.salesrobotmarket.service.view.MaintainView;
import com.ifree.robot.salesrobotmarket.ui.adapter.MaintainAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseFragment;
import com.ifree.robot.salesrobotmarket.utils.SPUtil;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import retrofit2.http.Url;

/**
 * Created by za on 2018/6/20.
 */

public class MaintainFragment extends BaseFragment {
    private MaintainPresenter mMaintainPresenter;
    @BindView(R.id.rl_maintain)
    RecyclerView rlMaintian;
    @BindView(R.id.tv_name_maintain)
    TextView tvName;
    private List<MaintainEntity.DataBean.MaintainListBean> mList = new ArrayList<>();
    private MaintainAdapter maintainAdapter;
    private int id = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_maintain;
    }

    @Override
    protected void initView() {
        tvName.setVisibility(View.GONE);
        mMaintainPresenter = new MaintainPresenter(context);
        LoadEntity.DataBean data = (LoadEntity.DataBean)bundle.getSerializable(Constant.INSPECTDATA);
        id = data.getId();
        rlMaintian.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        maintainAdapter = new MaintainAdapter(context, mList,id,1);
        rlMaintian.setAdapter(maintainAdapter);
    }

    @Override
    protected void initData() {
        mMaintainPresenter.onCreate();
        mMaintainPresenter.attachView(mMaintainView);
        mMaintainPresenter.getQuestMaintain(storeId,""+id,"1","加载中...");
    }

    private MaintainView mMaintainView = new MaintainView() {
        @Override
        public void onSuccess(MaintainEntity mMaintainEntity) {
            if (mMaintainEntity.getResultCode().equals("1")){
                ToastUtils.makeText(getActivity(),mMaintainEntity.getMsg());
                tvName.setVisibility(View.GONE);
                return;
            }
            List<MaintainEntity.DataBean.MaintainListBean> maintainlist = mMaintainEntity.getData().getMaintainList();
            if(maintainlist!=null && !maintainlist.isEmpty() && maintainlist.size() > 0){
                mList.addAll(maintainlist);
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(maintainlist.get(0).getName().concat("保养记录"));
                maintainAdapter.notifyDataSetChanged();
            }else {
                tvName.setVisibility(View.GONE);
            }
        }

        @Override
        public void onError(String result) {

        }
    };
}
