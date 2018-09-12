package com.ifree.robot.salesrobotmarket.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommodityShowEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.CommodityShowPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.CommodityShowView;
import com.ifree.robot.salesrobotmarket.ui.adapter.CommodityShowAdapter;
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
public class CommodityShowActivity extends BaseActivity {
    private CommodityShowPresenter mCommodityShowPresenter;
    @BindView(R.id.rl_show)
    XRecyclerView xRecyclerView;
    private  int nowPage = 1;
    private List<CommodityShowEntity.DataBean.CarProductBean> showlist = new ArrayList<>();
    private CommodityShowAdapter showAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_show;
    }

    @Override
    protected void initView() {
        hideBack(3);
        setTitleText("产品展示");
        mCommodityShowPresenter = new CommodityShowPresenter(context);
        xRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                nowPage = 1;
                showlist.clear();
                showAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                nowPage ++ ;
                loadData();
                xRecyclerView.loadMoreComplete();
                showAdapter.notifyDataSetChanged();

            }
        });
        showAdapter = new CommodityShowAdapter(context,showlist);
        xRecyclerView.setAdapter(showAdapter);
        xRecyclerView.setRefreshing(true);
    }


    @Override
    protected void loadData() {
        mCommodityShowPresenter.onCreate();
        mCommodityShowPresenter.attachView(mCommodityShowView);
        mCommodityShowPresenter.getQuestCommodityShow(storeId,nowPage+"","加载中...");
    }


    private CommodityShowView mCommodityShowView = new CommodityShowView() {
        @Override
        public void onSuccess(CommodityShowEntity mCommodityShowEntity) {
            if (mCommodityShowEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCommodityShowEntity.getMsg());
                return;
            }
            List<CommodityShowEntity.DataBean.CarProductBean> carProduct = mCommodityShowEntity.getData().getCarProduct();
            if (carProduct != null && !showlist.isEmpty() && showlist.size() > 0){
                showlist.addAll(carProduct);
                showAdapter.notifyDataSetChanged();
            }
            if (carProduct.size() < 10) {
                xRecyclerView.setNoMore(true);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
