package com.ifree.robot.salesrobotmarket.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.mvp.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.UsedCarPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.UsedCarView;
import com.ifree.robot.salesrobotmarket.ui.adapter.UsedCarAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.refresh.ProgressStyle;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class UsedCarActivity extends BaseActivity {
    private UsedCarPresenter mUsedCarPresenter;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.rl_second)
    XRecyclerView xRecyclerView;
    @BindView(R.id.rb_tbl1se)
    RadioButton rbTbl1se;
    @BindView(R.id.rb_tbl2se)
    RadioButton rbTbl2se;
    @BindView(R.id.rb_tbl3se)
    RadioButton rbTbl3se;
    @BindView(R.id.rb_tbl4se)
    RadioButton rbTbl4se;
    @BindView(R.id.rg_hotonese)
    RadioGroup rgHotonese;
    @BindView(R.id.rb_tblt1se)
    RadioButton rbTblt1se;
    @BindView(R.id.rb_tblt2se)
    RadioButton rbTblt2se;
    @BindView(R.id.rb_tblt3se)
    RadioButton rbTblt3se;
    @BindView(R.id.rb_tblt4se)
    RadioButton rbTblt4se;
    @BindView(R.id.rb_tblt5se)
    RadioButton rbTblt5se;
    @BindView(R.id.rb_tblt6se)
    RadioButton rbTblt6se;
    @BindView(R.id.rb_tblt7se)
    RadioButton rbTblt7se;
    @BindView(R.id.rg_hottwose)
    RadioGroup rgHottwose;
    @BindView(R.id.tablegridlayout)
    LinearLayout tablegridlayout;
    @BindView(R.id.ll_second_car)
    LinearLayout mSecondCar;
    @BindView(R.id.tv_jiaochevariety)
    TextView tvJiaochevariety;
    @BindView(R.id.tv_suvvariety)
    TextView tvSuvvariety;
    @BindView(R.id.tv_mpvvariety)
    TextView tvMpvvariety;
    @BindView(R.id.tv_paochevariety)
    TextView tvPaochevariety;
    private List<UsedCarEntity.DataBean.CarBean> mList = new ArrayList<>();
    private int carType = 0;
    private int priceType = 0;
    private UsedCarAdapter mAdapter;
    private String search;
    private List<UsedCarEntity.DataBean.CarBean> jiaoChe = new ArrayList<>();
    private List<UsedCarEntity.DataBean.CarBean> suv = new ArrayList<>();
    private List<UsedCarEntity.DataBean.CarBean> mpv = new ArrayList<>();
    private List<UsedCarEntity.DataBean.CarBean> paoChe = new ArrayList<>();
    private int chage=1;
    private int nowPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_used_car;
    }


    @Override
    protected void initView() {
        hideBack(2);
        mUsedCarPresenter = new UsedCarPresenter(context);
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
                mAdapter.notifyDataSetChanged();
                loadData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.loadMoreComplete();
                xRecyclerView.setNoMore(true);
            }
        });
        mAdapter = new UsedCarAdapter(context, mList);
        xRecyclerView.setAdapter(mAdapter);
        rgHotonese.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tbl1se:
                        carType = 0;
                        break;
                    case R.id.rb_tbl2se:
                        carType = 1;
                        break;
                    case R.id.rb_tbl3se:
                        carType = 2;
                        break;
                    case R.id.rb_tbl4se:
                        carType = 3;
                        break;
                }
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
            }

        });
        rgHottwose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_tblt1se:
                        priceType = 0;
                        break;
                    case R.id.rb_tblt2se:
                        priceType = 1;
                        break;
                    case R.id.rb_tblt3se:
                        priceType = 2;
                        break;
                    case R.id.rb_tblt4se:
                        priceType = 3;
                        break;
                    case R.id.rb_tblt5se:
                        priceType = 4;
                        break;
                    case R.id.rb_tblt6se:
                        priceType = 5;
                        break;
                    case R.id.rb_tblt7se:
                        priceType = 6;
                        break;
                }
                mList.clear();
                mAdapter.notifyDataSetChanged();
                loadData();
            }
        });
    }

    @Override
    protected void loadData() {
        search = edtKeyword.getText().toString().trim();
        mUsedCarPresenter.onCreate();
        mUsedCarPresenter.attachView(mUsedCarView);
        mUsedCarPresenter.getQuestUsedCar(carType,priceType,"2",storeId,nowPage,"10",search,"加载中...");
    }

    private UsedCarView mUsedCarView = new UsedCarView() {
        @Override
        public void onSuccess(UsedCarEntity mUsedCarEntity) {
            if (mUsedCarEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mUsedCarEntity.getMsg());
                tvJiaochevariety.setVisibility(View.GONE);
                tvSuvvariety.setVisibility(View.GONE);
                tvMpvvariety.setVisibility(View.GONE);
                tvPaochevariety.setVisibility(View.GONE);
                return;
            }
            UsedCarEntity.DataBean data = mUsedCarEntity.getData();
            if(data!=null){
                jiaoChe = data.getJiaoChe();
                if(jiaoChe !=null&& jiaoChe.size()>0){
                    tvJiaochevariety.setVisibility(View.VISIBLE);
                    tvJiaochevariety.setText("轿车（共"+ jiaoChe.size()+"款）");
                }else {
                    tvJiaochevariety.setVisibility(View.GONE);
                }
                suv =data.getSuv();
                if(suv !=null&& suv.size()>0){
                    tvSuvvariety.setVisibility(View.VISIBLE);
                    tvSuvvariety.setText("SUV（共"+ suv.size()+"款）");
                }else {
                    tvSuvvariety.setVisibility(View.GONE);
                }
                mpv = data.getMpv();
                if(mpv !=null&& mpv.size()>0){
                    tvMpvvariety.setVisibility(View.VISIBLE);
                    tvMpvvariety.setText("MPV（共"+ mpv.size()+"款）");
                }
                else {
                    tvMpvvariety.setVisibility(View.GONE);
                }
                paoChe = data.getPaoChe();
                if(paoChe !=null&& paoChe.size()>0){
                    tvPaochevariety.setVisibility(View.VISIBLE);
                    tvPaochevariety.setText("跑车（共"+ paoChe.size() +"款）");
                }else {
                    tvPaochevariety.setVisibility(View.GONE);
                }
            }
            mList.clear();

            if(jiaoChe!=null&&jiaoChe.size()>0){
                mList.addAll(jiaoChe);
                chage=1;
                tvJiaochevariety.setTextColor(Color.BLUE);
            }else if(suv!=null&&suv.size()>0){
                mList.addAll(suv);
                chage=2;
                tvSuvvariety.setTextColor(Color.BLUE);
            }else if(mpv!=null&&mpv.size()>0){
                mList.addAll(mpv);
                chage=3;
                tvMpvvariety.setTextColor(Color.BLUE);
                tvJiaochevariety.setTextColor(Color.BLUE);
            }else if(paoChe!=null&&paoChe.size()>0){
                mList.addAll(paoChe);
                chage=4;
                tvPaochevariety.setTextColor(Color.BLUE);
            }
            switch (chage){
                case 1:
                    tvJiaochevariety.setTextColor(Color.BLUE);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    break;
                case 2:
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.BLUE);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    break;
                case 3:
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.BLUE);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    break;
                case 4:
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.BLUE);
                    break;
            }
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {

        }
    };

    @OnClick({R.id.iv_search,R.id.tv_jiaochevariety, R.id.tv_suvvariety, R.id.tv_mpvvariety, R.id.tv_paochevariety})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_search:
                String trim = edtKeyword.getText().toString().trim();
                if(!TextUtils.isEmpty(trim)){
                    search=trim;
                    loadData();
                }
                break;
            case R.id.tv_jiaochevariety:
               if(chage!=1){
                   chage=1;
                  tvJiaochevariety.setTextColor(Color.BLUE);
                  tvSuvvariety.setTextColor(Color.GRAY);
                  tvMpvvariety.setTextColor(Color.GRAY);
                  tvPaochevariety.setTextColor(Color.GRAY);
                  mList.clear();
                   mList.addAll(jiaoChe);
                  mAdapter.notifyDataSetChanged();
               }
                break;
            case R.id.tv_suvvariety:
                if(chage!=2){
                    chage=2;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.BLUE);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(suv);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_mpvvariety:
                if(chage!=3){
                    chage=3;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.BLUE);
                    tvPaochevariety.setTextColor(Color.GRAY);
                    mList.clear();
                    mList.addAll(mpv);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.tv_paochevariety:
                if(chage!=4){
                    chage=4;
                    tvJiaochevariety.setTextColor(Color.GRAY);
                    tvSuvvariety.setTextColor(Color.GRAY);
                    tvMpvvariety.setTextColor(Color.GRAY);
                    tvPaochevariety.setTextColor(Color.BLUE);
                    mList.clear();
                    mList.addAll(paoChe);
                    mAdapter.notifyDataSetChanged();
                }
                break;

        }
    }
}
