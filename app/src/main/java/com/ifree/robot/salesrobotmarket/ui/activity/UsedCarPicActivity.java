package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class UsedCarPicActivity extends BaseActivity {
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.banner_second)
    Banner bannerSecond;
    private ArrayList<? extends String> list_path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_used_car_pic;
    }

    @Override
    protected void loadData() {
        final int pic = getIntent().getIntExtra("position", 0);
        list_path = getIntent().getParcelableArrayListExtra("pic");
        bannerSecond.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new MyLoader())
                .setImages(list_path)
                .setBannerAnimation(Transformer.Default)
                .setDelayTime(1)
                .isAutoPlay(true);
        bannerSecond.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == pic + 1) {
                    bannerSecond.isAutoPlay(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置是否为自动轮播，默认是“是”。
        //banner.setOffscreenPageLimit(1);
        // banner.startAutoPlay();
        //设置指示器的位置，小点点，左中右。
        bannerSecond.setIndicatorGravity(BannerConfig.CENTER);
        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
        // .setOnBannerListener(this)
        //必须最后调用的方法，启动轮播图。
        bannerSecond.start();
    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.translucent));
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtils.imageLoader(context, (String) path, imageView);
        }
    }
}
