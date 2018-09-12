package com.ifree.robot.salesrobotmarket.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.mvp.entity.IntegralEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.IntegralPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.IntegralView;
import com.ifree.robot.salesrobotmarket.ui.adapter.IntegralAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseFragment;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class IntegralFragment extends BaseFragment {
    private IntegralPresenter mIntegralPresenter;
    @BindView(R.id.rl_integral)
    RecyclerView rlIntegral;
    Unbinder unbinder;
    @BindView(R.id.tv_interphone)
    TextView tvInterphone;
    @BindView(R.id.tv_intersor)
    TextView tvIntersor;
    private List<IntegralEntity.DataBean.ScoreListBean> list = new ArrayList<>();
    private IntegralAdapter integralAdapter;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.fragment_integral;
    }

    @Override
    protected void initView() {
        mIntegralPresenter = new IntegralPresenter(context);
        LoadEntity.DataBean data = (LoadEntity.DataBean) bundle.getSerializable(Constant.VIPDATA);
        id = data.getId();
        String name = data.getName();
        String phone = data.getPhone();
        int score = data.getScore();
        tvInterphone.setText(phone+"");
        tvIntersor.setText(score+"");
        rlIntegral.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        integralAdapter = new IntegralAdapter(context,list);
        rlIntegral.setAdapter(integralAdapter);
        integralAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        mIntegralPresenter.onCreate();
        mIntegralPresenter.attachView(mIntegralView);
        mIntegralPresenter.getQuestIntegralInfo(storeId,id+"","1","1","加载中...");
    }

    private IntegralView mIntegralView = new IntegralView() {
        @Override
        public void onSuccess(IntegralEntity mIntegralEntity) {
            if (mIntegralEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mIntegralEntity.getMsg());
                return;
            }
            List<IntegralEntity.DataBean.ScoreListBean> scoreList = mIntegralEntity.getData().getScoreList();
            if (scoreList != null && !scoreList.isEmpty() && scoreList.size()>0){
                list.addAll(scoreList);
                integralAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
