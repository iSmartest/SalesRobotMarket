package com.ifree.robot.salesrobotmarket.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.custom.MyRecyclerView;
import com.ifree.robot.salesrobotmarket.mvp.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.UsedCarInfoEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.UsedCarInfoPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.UsedCarInfoView;
import com.ifree.robot.salesrobotmarket.ui.adapter.PicUsedCarAdapter;
import com.ifree.robot.salesrobotmarket.ui.adapter.RedSellingAdapter;
import com.ifree.robot.salesrobotmarket.ui.adapter.TableAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.DateUtils;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;
import com.ifree.robot.salesrobotmarket.utils.PriceUtils;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class UsedCarInfoActivity extends BaseActivity {
    private UsedCarInfoPresenter mUsedCarInfoPresenter;
    @BindView(R.id.iv_seconddeatial)
    ImageView carPic;
    @BindView(R.id.rv_second)
    GridView rvSecond;
    @BindView(R.id.tv_sdname)
    TextView tvSdname;
    @BindView(R.id.tv_sd11)
    TextView tvSd11;
    @BindView(R.id.tv_sd12)
    TextView tvSd12;
    @BindView(R.id.tv_sd21)
    TextView tvSd21;
    @BindView(R.id.tv_sd22)
    TextView tvSd22;
    @BindView(R.id.tv_sd23)
    TextView tvSd23;
    @BindView(R.id.tv_sd24)
    TextView tvSd24;
    @BindView(R.id.tv_sd41)
    TextView tvSd41;
    @BindView(R.id.tv_sd42)
    TextView tvSd42;
    @BindView(R.id.tv_sd43)
    TextView tvSd43;
    @BindView(R.id.rv_picseconddea)
    MyRecyclerView rvPicseconddea;
    @BindView(R.id.rv_redselling)
    RecyclerView rvRedselling;
    @BindView(R.id.tv_sd31)
    TextView tvSd31;
    @BindView(R.id.tv_sd32)
    TextView tvSd32;
    private List<List<String>> listList = new ArrayList<>();
    private int id = 0;
    private TableAdapter tableAdapter;
    private List<String> list;
    private PicUsedCarAdapter picSecondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_used_car_info;

    }
    @Override
    protected void initView() {
        hideBack(1);
        mUsedCarInfoPresenter = new UsedCarInfoPresenter(context);
        UsedCarEntity.DataBean.CarBean carBean = (UsedCarEntity.DataBean.CarBean) getIntent().getSerializableExtra(Constant.INTENTINSPECTID);
        id = carBean.getId();
        List<String> brightPoint = carBean.getBrightPoint();
        list = new ArrayList<>();
        rvPicseconddea.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        picSecondAdapter = new PicUsedCarAdapter(context,list);
        rvPicseconddea.setAdapter(picSecondAdapter);
        rvRedselling.setLayoutManager(new LinearLayoutManager(context));
        RedSellingAdapter redsellingAdapter = new RedSellingAdapter(context,brightPoint);
        rvRedselling.setAdapter(redsellingAdapter);
    }

    @Override
    protected void loadData() {
        mUsedCarInfoPresenter.onCreate();
        mUsedCarInfoPresenter.attachView(mUsedCarInfoView);
        mUsedCarInfoPresenter.getQuestUsedCarInfo(storeId,id+"","加载中...");
    }

    private UsedCarInfoView mUsedCarInfoView = new UsedCarInfoView() {
        @Override
        public void onSuccess(UsedCarInfoEntity mUsedCarInfoEntity) {
            if (mUsedCarInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUsedCarInfoEntity.getMsg());
                return;
            }
            UsedCarInfoEntity.DataBean data = mUsedCarInfoEntity.getData();
            UsedCarInfoEntity.DataBean.CommonBean common = data.getCommon();
            if (common == null) {
                return;
            }
            tvSdname.setText(common.getCarName());
            if (common.getIsmaintain() == 0) {
                tvSd11.setVisibility(View.VISIBLE);
            } else {
                tvSd11.setVisibility(View.GONE);
            }
            if (common.getIstransfer() == 0) {
                tvSd12.setVisibility(View.VISIBLE);
            } else {
                tvSd12.setVisibility(View.GONE);
            }
            long date = common.getDate();
            String time = DateUtils.getDateToString(date, "yyyy-MM-dd");
            tvSd21.setText(time);
            tvSd22.setText(common.getMileage() + "万");
            tvSd23.setText(common.getCity());
            tvSd24.setText(common.getOutPut() + "T");
            tvSd31.setText(PriceUtils.getprice(common.getPrice()));
            tvSd32.setText(PriceUtils.getprice(common.getGuideprice()));
            tvSd41.setText(common.getIsAccident());
            tvSd42.setText(common.getIsFire());
            tvSd43.setText(common.getIsWater());
            GlideUtils.imageLoader(context,common.getPic(),carPic);
            List<String> pics = common.getPics();
            list.clear();
            list.addAll(pics);
            picSecondAdapter.notifyDataSetChanged();
            if (data.getBasic() != null && !data.getBasic().isEmpty() && data.getBasic().size() > 0){
                listList.add(data.getBasic());
            }
            if (data.getMontor() != null && !data.getMontor().isEmpty() && data.getMontor().size() > 0){
                listList.add(data.getMontor());
            }
            if (data.getUnderPan() != null && !data.getUnderPan().isEmpty() && data.getUnderPan().size() > 0){
                listList.add(data.getUnderPan());
            }
            if (data.getSecure() != null && !data.getSecure().isEmpty() && data.getSecure().size() > 0){
                listList.add(data.getSecure());
            }
            if (data.getOuter() != null && !data.getOuter().isEmpty() && data.getOuter().size() > 0){
                listList.add(data.getOuter());
            }
            if (data.getInner() != null && !data.getInner().isEmpty() && data.getInner().size() > 0){
                listList.add(data.getInner());
            }
            tableAdapter = new TableAdapter(context, listList);
            rvSecond.setAdapter(tableAdapter);
        }

        @Override
        public void onError(String result) {

        }
    };
}
