package com.ifree.robot.salesrobotmarket.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.service.entity.CommodityIntroduceEntity;
import com.ifree.robot.salesrobotmarket.service.presenter.CommodityIntroducePresenter;
import com.ifree.robot.salesrobotmarket.service.view.CommodityIntroduceView;
import com.ifree.robot.salesrobotmarket.ui.adapter.CommodityIntroduceAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.refresh.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */
public class CommodityIntroduceActivity extends BaseActivity {
    private CommodityIntroducePresenter mCommodityIntroducePresenter;
    @BindView(R.id.xr_commodity_introduce)
    XRecyclerView xRecyclerView;
    private int nowPage = 1;
    private List<CommodityIntroduceEntity.DataBean.CarVideoBean> mList = new ArrayList<>();
    private CommodityIntroduceAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_introduce;
    }

    @Override
    protected void initView() {
        hideBack(3);
        setTitleText("产品介绍");
        mCommodityIntroducePresenter = new CommodityIntroducePresenter(context);
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                nowPage++;
                loadData();
                xRecyclerView.loadMoreComplete();
            }
        });

        mAdapter = new CommodityIntroduceAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);

    }


    @Override
    protected void loadData() {
        mCommodityIntroducePresenter.onCreate();
        mCommodityIntroducePresenter.attachView(mCommodityIntroduceView);
        mCommodityIntroducePresenter.getQuestCommodityIntroduce(storeId, nowPage + "", "加载中...");
    }

    private CommodityIntroduceView mCommodityIntroduceView = new CommodityIntroduceView() {
        @Override
        public void onSuccess(CommodityIntroduceEntity mCommodityIntroduceEntity) {
            if (mCommodityIntroduceEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCommodityIntroduceEntity.getMsg());
                return;
            }

            List<CommodityIntroduceEntity.DataBean.CarVideoBean> carVideoBeans = mCommodityIntroduceEntity.getData().getCarVideo();
            if (carVideoBeans != null && !carVideoBeans.isEmpty() && carVideoBeans.size() > 0) {
                mList.addAll(carVideoBeans);
                mAdapter.notifyDataSetChanged();
                xRecyclerView.refreshComplete();
            }
            if (carVideoBeans.size() < 10) {
                xRecyclerView.setNoMore(true);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }

    };
}
