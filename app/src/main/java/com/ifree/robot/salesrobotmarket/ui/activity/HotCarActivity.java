package com.ifree.robot.salesrobotmarket.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.HotCarEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.HotCarPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.HotCarView;
import com.ifree.robot.salesrobotmarket.ui.adapter.HotCarAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.refresh.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class HotCarActivity extends BaseActivity {
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.rb_tbl1)
    RadioButton rbTbl1;
    @BindView(R.id.rb_tbl2)
    RadioButton rbTbl2;
    @BindView(R.id.rb_tbl3)
    RadioButton rbTbl3;
    @BindView(R.id.rb_tbl4)
    RadioButton rbTbl4;
    @BindView(R.id.rg_hotone)
    RadioGroup rgHotone;
    @BindView(R.id.rb_tblt1)
    RadioButton rbTblt1;
    @BindView(R.id.rb_tblt2)
    RadioButton rbTblt2;
    @BindView(R.id.rb_tblt3)
    RadioButton rbTblt3;
    @BindView(R.id.rb_tblt4)
    RadioButton rbTblt4;
    @BindView(R.id.rg_hottwo)
    RadioGroup rgHottwo;
    @BindView(R.id.tablegridlayout)
    LinearLayout tablegridlayoutt;
    @BindView(R.id.rl_hotcar)
    XRecyclerView xRecyclerView;
    @BindView(R.id.tv_hjiaochevariety)
    TextView tvHjiaochevariety;
    @BindView(R.id.tv_hsuvvariety)
    TextView tvHsuvvariety;
    @BindView(R.id.tv_hmpvvariety)
    TextView tvHmpvvariety;
    @BindView(R.id.tv_hpaochevariety)
    TextView tvHpaochevariety;
    private HotCarPresenter mHotCarPresenter;
    private int carType = 0;
    private int priceType = 0;
    private HotCarAdapter hotcarAdapter;
    private int page = 1;
    private String search;
    private List<HotCarEntity.DataBean.CarBean> sedan;
    private List<HotCarEntity.DataBean.CarBean> suv;
    private List<HotCarEntity.DataBean.CarBean> mpv;
    private List<HotCarEntity.DataBean.CarBean> paoChe;
    private List<HotCarEntity.DataBean.CarBean> mList = new ArrayList<>();
    private int change = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_car;
    }

    @Override
    protected void initView() {
        hideBack(2);
        mHotCarPresenter = new HotCarPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();
                xRecyclerView.setNoMore(true);
            }
        });

        hotcarAdapter = new HotCarAdapter(context, mList);
        xRecyclerView.setAdapter(hotcarAdapter);
        xRecyclerView.setRefreshing(true);
        //两行条件选择器的动态监听
        rgHotone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tbl1:
                        carType = 0;
                        break;
                    case R.id.rb_tbl2:
                        carType = 1;
                        break;
                    case R.id.rb_tbl3:
                        carType = 2;
                        break;
                    case R.id.rb_tbl4:
                        carType = 3;
                        break;
                }
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
            }

        });
        rgHottwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tblt1:
                        priceType = 0;
                        break;
                    case R.id.rb_tblt2:
                        priceType = 7;
                        break;
                    case R.id.rb_tblt3:
                        priceType = 8;
                        break;
                    case R.id.rb_tblt4:
                        priceType = 9;
                        break;
                }
                mList.clear();
                hotcarAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }

    @Override
    protected void loadData() {
        //开启网络请求
        search = edtKeyword.getText().toString().trim();
        mHotCarPresenter.onCreate();
        mHotCarPresenter.attachView(mHotCarView);
        mHotCarPresenter.getQuestHotCar(carType,priceType,"1",storeId,page,"10",search,"加载中...");

    }

    private HotCarView mHotCarView = new HotCarView() {
        @Override
        public void onSuccess(HotCarEntity mHotCarEntity) {
            if (mHotCarEntity.getResultCode().equals("1")) {
                tvHjiaochevariety.setVisibility(View.GONE);
                tvHsuvvariety.setVisibility(View.GONE);
                tvHmpvvariety.setVisibility(View.GONE);
                tvHpaochevariety.setVisibility(View.GONE);
                ToastUtils.makeText(context, mHotCarEntity.getMsg());
                return;
            }
            HotCarEntity.DataBean data = mHotCarEntity.getData();
            if (data != null) {
                sedan = data.getJiaoChe();
                if (sedan != null && !sedan.isEmpty() && sedan.size() > 0) {
                    tvHjiaochevariety.setVisibility(View.VISIBLE);
                    tvHjiaochevariety.setText("轿车（共" + sedan.size() + "款）");
                } else {
                    tvHjiaochevariety.setVisibility(View.GONE);
                }
                suv = data.getSuv();
                if (suv != null && !suv.isEmpty() && suv.size() > 0) {
                    tvHsuvvariety.setVisibility(View.VISIBLE);
                    tvHsuvvariety.setText("SUV（共" + suv.size() + "款）");
                } else {
                    tvHsuvvariety.setVisibility(View.GONE);
                }
                mpv = data.getMpv();
                if (mpv != null && !mpv.isEmpty() && mpv.size() > 0) {
                    tvHmpvvariety.setVisibility(View.VISIBLE);
                    tvHmpvvariety.setText("MPV（共" + mpv.size() + "款）");
                } else {
                    tvHmpvvariety.setVisibility(View.GONE);
                }
                paoChe = data.getPaoChe();
                if (paoChe != null && !paoChe.isEmpty() && paoChe.size() > 0) {
                    tvHpaochevariety.setVisibility(View.VISIBLE);
                    tvHpaochevariety.setText("跑车（共" + paoChe.size() + "款）");
                } else {
                    tvHpaochevariety.setVisibility(View.GONE);
                }
            }
            mList.clear();
            if (sedan != null && sedan.size() > 0) {
                mList.addAll(sedan);
                change = 1;
                tvHjiaochevariety.setTextColor(Color.BLUE);
            } else if (suv != null && suv.size() > 0) {
                mList.addAll(suv);
                change = 2;
                tvHsuvvariety.setTextColor(Color.BLUE);
            } else if (mpv != null && mpv.size() > 0) {
                mList.addAll(mpv);
                change = 3;
                tvHmpvvariety.setTextColor(Color.BLUE);
                tvHjiaochevariety.setTextColor(Color.BLUE);
            } else if (paoChe != null && paoChe.size() > 0) {
                mList.addAll(paoChe);
                change = 4;
                tvHpaochevariety.setTextColor(Color.BLUE);
            }
            switch (change) {
                case 1:
                    tvHjiaochevariety.setTextColor(Color.BLUE);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    break;
                case 2:
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.BLUE);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    break;
                case 3:
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.BLUE);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    break;
                case 4:
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.BLUE);
                    break;
            }
            hotcarAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
    @OnClick({R.id.iv_search,R.id.tv_hjiaochevariety, R.id.tv_hsuvvariety, R.id.tv_hmpvvariety, R.id.tv_hpaochevariety})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                String trim = edtKeyword.getText().toString().trim();
                if (trim != null && trim != "") {
                    loadData();
                }
                break;
            case R.id.tv_hjiaochevariety:
                if (change != 1) {
                    change = 1;
                    tvHjiaochevariety.setTextColor(Color.BLUE);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(sedan);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hsuvvariety:
                if (change != 2) {
                    change = 2;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.BLUE);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(suv);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hmpvvariety:
                if (change != 3) {
                    change = 3;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.BLUE);
                    tvHpaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(mpv);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_hpaochevariety:
                if (change != 4) {
                    change = 4;
                    tvHjiaochevariety.setTextColor(Color.GRAY);
                    tvHsuvvariety.setTextColor(Color.GRAY);
                    tvHmpvvariety.setTextColor(Color.GRAY);
                    tvHpaochevariety.setTextColor(Color.BLUE);
                    mList.clear();
                    mList.addAll(paoChe);
                    hotcarAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
