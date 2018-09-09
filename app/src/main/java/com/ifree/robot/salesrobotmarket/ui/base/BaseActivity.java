package com.ifree.robot.salesrobotmarket.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelrobot.common.utils.RkOperationUtil;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.ui.activity.ConsultActivity;
import com.ifree.robot.salesrobotmarket.ui.activity.TrailerVideoActivity;
import com.ifree.robot.salesrobotmarket.ui.activity.VideoActivity;
import com.ifree.robot.salesrobotmarket.utils.AppManager;
import com.ifree.robot.salesrobotmarket.utils.SPUtil;
import com.ifree.robot.salesrobotmarket.utils.StatusBarUtil;
import com.robot.performlib.action.SpeakAction;
import com.robot.performlib.action.WakeupAction;
import com.robot.performlib.callback.PerformFaceDetectCallBack;
import com.yunjichina.callback.FaceDetectCallback;
import com.yunjichina.constant.FaceDetectConstant;
import com.yunjichina.facedetect.demo.FaceDetectAction;
import com.yunjichina.vision.facedetect.FaceRect;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/15.
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    private BaseFragment lastFragment;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.ly_base_left)
    LinearLayout mLeft;
    @BindView(R.id.iv_base_back)
    ImageView ivBack;
    @BindView(R.id.ly_base_center)
    LinearLayout mCenter;
    @BindView(R.id.tv_base_titleText)
    TextView mTitleText;
    @BindView(R.id.ly_base_search)
    LinearLayout mBaseSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.edt_a_key_search)
    EditText edtKeyword;
    @BindView(R.id.ly_base_right)
    LinearLayout mRight;
    @BindView(R.id.tv_base_rightText)
    TextView mRightText;
    protected String storeId;
    private boolean isStart;
    static Handler myHandler = new Handler();
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(BaseActivity.this, TrailerVideoActivity.class);
            startActivity(intent);
            AppManager.finishAllActivity();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = this;
        ButterKnife.bind(this);
        StatusBarUtil.transparencyBar(this); //设置状态栏全透明
        StatusBarUtil.StatusBarLightMode(this); //设置白底黑字
        AppManager.addActivity(this);
        RkOperationUtil.showTab (context);
        storeId =  SPUtil.getString(context,"storeId");
        initView();//实例化
        loadData();//加载数据
    }

    private FaceDetectCallback call = new FaceDetectCallback() {
        @Override
        public void findFaceHandler(List<FaceRect> featureList, int imageWidth, int imageHeight, List<String> nameList) {
            super.findFaceHandler(featureList, imageWidth, imageHeight, nameList);
            myHandler.removeCallbacks(myRunnable);
            isStart = true;
        }

        @Override
        public void nofindFaceHandler() {
            super.nofindFaceHandler();
            BaseActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startAD();
                }
            });
        }
    };

    protected abstract int getLayoutId();

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FaceDetectAction.init(context).setTime(10000).setCallback(call);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract void loadData();

    protected abstract void initView();


    /**
     * 切换Fragment的方法
     *
     * @param fragmentClass 要跳转的Fragment
     * @param containId     容器ID
     * @param isHidden      是否隐藏
     * @param bundle        参数
     * @param isBack        是否添加到回退栈
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //获取Fragment的类名，用类名当做Tag
        String fragmentName = fragmentClass.getSimpleName();
        //根据tag来查找当前Fragment，如果不为null 就代表当前Fragment已经被加载过至少一次
        BaseFragment currentFragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
        if (currentFragment == null) {
            //如果Fragment为null 就创建Fragment对象，添加到FragmentManager中
            try {
                //通过Java动态代理创建的对象
                currentFragment = fragmentClass.newInstance();
                //添加到FragmentManager中
                transaction.add(containId, currentFragment, fragmentName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isHidden) {
            //隐藏上一个Fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);
            //显示当前Fragment
            transaction.show(currentFragment);
        } else {
            //替换上一个Fragment
            transaction.replace(containId, currentFragment, fragmentName);
        }
        //传递参数
        if (bundle != null) {
            Objects.requireNonNull(currentFragment).setBundle(bundle);
        }

        if (isBack) {
            transaction.addToBackStack(fragmentName);
        }

        transaction.commit();

        lastFragment = currentFragment;

        return lastFragment;

    }

    public void hideBack(int position) {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (position) {
            case 1://返回
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.GONE);
                mRight.setVisibility(View.GONE);
                break;
            case 2://返回、搜索
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.GONE);
                mBaseSearch.setVisibility(View.VISIBLE);
                mRight.setVisibility(View.GONE);
                break;
            case 3://返回、标题
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.GONE);
                break;
            case 4://返回、标题、右文字
                mLeft.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.VISIBLE);
                mCenter.setVisibility(View.VISIBLE);
                mTitleText.setVisibility(View.VISIBLE);
                mBaseSearch.setVisibility(View.GONE);
                mRight.setVisibility(View.VISIBLE);
                mRightText.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setTitleText(String string) {
        TextView titleTv = findViewById(R.id.tv_base_titleText);
        titleTv.setText(string);
    }

    public void setRightText(String string) {
        TextView tvRightText = findViewById(R.id.tv_base_rightText);
        tvRightText.setText(string);
    }

    public void startAD() {
        if (isStart){
            myHandler.removeCallbacks(myRunnable);
            myHandler.postDelayed(myRunnable, 20000);
            isStart = false;
        }
    }
}
