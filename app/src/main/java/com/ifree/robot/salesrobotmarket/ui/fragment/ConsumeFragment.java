package com.ifree.robot.salesrobotmarket.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsumeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.ConsumePresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.ConsumeView;
import com.ifree.robot.salesrobotmarket.ui.adapter.ConsumeAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseFragment;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class ConsumeFragment extends BaseFragment {
    private ConsumePresenter mConsumePresenter;
    @BindView(R.id.rl_consume)
    XRecyclerView rlConsume;
    private ConsumeAdapter consumeAdapter;
    private List<ConsumeEntity.DataBean.ConsumptionListBean> list;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.fragment_consume;
    }


    @Override
    protected void initView() {
        mConsumePresenter = new ConsumePresenter(context);
        LoadEntity.DataBean data = (LoadEntity.DataBean) bundle.getSerializable(Constant.VIPDATA);
        id = data.getId();
        list = new ArrayList<>();
        rlConsume.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        consumeAdapter = new ConsumeAdapter(list);
        rlConsume.setAdapter(consumeAdapter);
    }

    @Override
    protected void initData() {
        mConsumePresenter.onCreate();
        mConsumePresenter.attachView(mConsumeView);
        mConsumePresenter.getQuestConsumeInfo(storeId,id+"","2","1","加载中...");
    }

    private ConsumeView mConsumeView = new ConsumeView() {
        @Override
        public void onSuccess(ConsumeEntity mConsumeEntity) {
            if (mConsumeEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(getActivity(), mConsumeEntity.getMsg());
                return;
            }
            List<ConsumeEntity.DataBean.ConsumptionListBean> consumptionList = mConsumeEntity.getData().getConsumptionList();
            if (consumptionList != null && !consumptionList.isEmpty() && consumptionList.size() > 0) {
                list.addAll(consumptionList);
                consumeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(getActivity(), result);
        }
    };
}
