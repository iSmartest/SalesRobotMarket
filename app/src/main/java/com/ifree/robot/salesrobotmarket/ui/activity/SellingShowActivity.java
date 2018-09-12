package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.custom.PhotoView;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;
import java.util.List;
import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class SellingShowActivity extends BaseActivity {
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.see_pager)
    ViewPager mPager;
    @BindView(R.id.ll_see_picture)
    LinearLayout mLlPicture;
    @BindView(R.id.text_total_item)
    TextView mTotalItem;
    @BindView(R.id.text_pic_info)
    TextView mPicInfo;
    @BindView(R.id.iv_play_video)
    ImageView mPlay;
    private ImageAdapter mImageAdapter;
    private boolean isVISIBLE = true;
    private String videoAddress;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_slide_show;
    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.translucent));
        final ConsultEntity.DataBean.BrightPointBean answersBean = (ConsultEntity.DataBean.BrightPointBean) getIntent().getSerializableExtra("selling");
        int consultint = answersBean.getType();
        switch (consultint){
            case 1:
                mPlay.setVisibility(View.VISIBLE);
                break;
            case 2:
                mPlay.setVisibility(View.GONE);
                break;
        }

        mTotalItem.setText(1 + "/" + answersBean.getSellPointdsc().size());
        mPicInfo.setText(answersBean.getSellPointdsc().get(0).getDsc());
        mPicInfo.setMaxHeight(600);
        mPicInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        mPager.setPageMargin((int) (context.getResources().getDisplayMetrics().density * 15));
        mImageAdapter = new ImageAdapter(context,answersBean.getSellPointdsc());
        mPager.setAdapter(mImageAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTotalItem.setText((position + 1)+"/" + answersBean.getSellPointdsc().size());
                mPicInfo.setText(answersBean.getSellPointdsc().get(position).getDsc());
            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answersBean!=null){
                    videoAddress = answersBean.getSellPointvid();
                }
                Bundle bundle = new Bundle();
                bundle.putString(Constant.VIDEO, videoAddress);
                MyApplication.openActivity(context,VideoActivity.class,bundle);
            }
        });
    }
    @Override
    protected void loadData() {

    }


    public class ImageAdapter extends PagerAdapter {
        private PhotoView photo_view;
        private Context mContext;
        private List<ConsultEntity.DataBean.BrightPointBean.SellPointdscBean> mShopImgList;

        public ImageAdapter(Context mContext, List<ConsultEntity.DataBean.BrightPointBean.SellPointdscBean> mShopImgList) {
            this.mContext = mContext;
            this.mShopImgList = mShopImgList;
        }

        @Override
        public int getCount() {
            return mShopImgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_see_picture, null);
            photo_view = view.findViewById(R.id.iv_see_picture);
            photo_view.enable();
            photo_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            GlideUtils.imageLoader(context,mShopImgList.get(position).getImg(),photo_view);
            photo_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isVISIBLE){
                        mLlPicture.setVisibility(View.GONE);
                        lay_bg.setVisibility(View.GONE);
                        isVISIBLE = false;
                    }else {
                        mLlPicture.setVisibility(View.VISIBLE);
                        lay_bg.setVisibility(View.VISIBLE);
                        isVISIBLE = true;
                    }
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
