package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.custom.PhotoView;
import com.ifree.robot.salesrobotmarket.service.entity.RobotConsultEntity;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;

public class RobotDetailsActivity extends BaseActivity {
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
        final RobotConsultEntity.DataBean.CarListBean.AnswersBean answersBean = (RobotConsultEntity.DataBean.CarListBean.AnswersBean) getIntent().getSerializableExtra("robot");
        int consultint = answersBean.getType();
        switch (consultint) {
            case 1:
                mPlay.setVisibility(View.VISIBLE);
                break;
            case 2:
                mPlay.setVisibility(View.GONE);
                break;
        }

        mTotalItem.setText(1 + "/" + answersBean.getPics().size());
        mPicInfo.setText(answersBean.getPics().get(0).getDes());
        mPicInfo.setMaxHeight(600);
        mPicInfo.setMovementMethod(ScrollingMovementMethod.getInstance());
        mPager.setPageMargin((int) (context.getResources().getDisplayMetrics().density * 15));
        mImageAdapter = new ImageAdapter(context, answersBean.getPics());
        mPager.setAdapter(mImageAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTotalItem.setText((position + 1) + "/" + answersBean.getPics().size());
                mPicInfo.setText(answersBean.getPics().get(position).getDes());
            }
        });

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answersBean != null) {
                    videoAddress = answersBean.getVideoAddress();
                }
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra(Constant.VIDEO, videoAddress);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }


    public class ImageAdapter extends PagerAdapter {
        private PhotoView photo_view;
        private Context mContext;
        private List<RobotConsultEntity.DataBean.CarListBean.AnswersBean.PicsBean> mShopImgList;

        public ImageAdapter(Context mContext, List<RobotConsultEntity.DataBean.CarListBean.AnswersBean.PicsBean> mShopImgList) {
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
            GlideUtils.imageLoader(context,mShopImgList.get(position).getPic(),photo_view);
            photo_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isVISIBLE) {
                        mLlPicture.setVisibility(View.GONE);
                        lay_bg.setVisibility(View.GONE);
                        isVISIBLE = false;
                    } else {
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
