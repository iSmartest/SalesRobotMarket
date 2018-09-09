package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.ui.fragment.ConsumeFragment;
import com.ifree.robot.salesrobotmarket.ui.fragment.IntegralFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/4.
 * Description:
 */

public class VipActivity extends BaseActivity {

    @BindView(R.id.rb_vip1)
    RadioButton rbVip1;
    @BindView(R.id.rb_vip2)
    RadioButton rbVip2;
    @BindView(R.id.rg_vip)
    RadioGroup rgVip;
    private ColorStateList csl1, csl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //默认选中第一个按键

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(1);
        final Bundle bundle = new Bundle();
        LoadEntity.DataBean data = (LoadEntity.DataBean) getIntent().getSerializableExtra(Constant.LOADDATA);
        bundle.putSerializable(Constant.VIPDATA, data);
        Resources resource = context.getResources();
        csl1 = resource.getColorStateList(R.color.app_main_default);
        csl2 = resource.getColorStateList(R.color.black_color);
        changeFragment(IntegralFragment.class, R.id.fl_vip, false, bundle, false);
        //设置选中事件
        rgVip.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_vip1:
                        rbVip1.setTextColor(csl1);
                        rbVip2.setTextColor(csl2);
                        changeFragment(IntegralFragment.class, R.id.fl_vip, false, bundle, false);
                        break;
                    case R.id.rb_vip2:
                        rbVip1.setTextColor(csl2);
                        rbVip2.setTextColor(csl1);
                        changeFragment(ConsumeFragment.class, R.id.fl_vip, false, bundle, false);
                        break;
                }
            }
        });
    }

}
