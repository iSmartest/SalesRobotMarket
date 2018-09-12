package com.ifree.robot.salesrobotmarket.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.app.MyApplication;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.listener.OnReceiverListenner;
import com.ifree.robot.salesrobotmarket.mvp.entity.RobotConsultEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.RobotConsultPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.RobotConsultView;
import com.ifree.robot.salesrobotmarket.receiver.AIUIReceiver;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.robot.performlib.action.AIUIAction;
import com.robot.performlib.action.SpeakAction;
import com.robot.performlib.action.WakeupAction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.bumptech.glide.request.RequestOptions.diskCacheStrategyOf;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class RobotConsultActivity extends BaseActivity {
    private RobotConsultPresenter mRobotConsultPresenter;
    @BindView(R.id.lay_bg)
    RelativeLayout lay_bg;
    @BindView(R.id.tv_this1)
    public TextView tvThis1;
    @BindView(R.id.tv_robotan2)
    public TextView tvRobotan2;
    @BindView(R.id.iv_mic)
    ImageView ivMic;
    @BindView(R.id.tv_rbcon3)
    TextView tvRbcon3;
    @BindView(R.id.iv_mic2)
    ImageView ivMic2;
    @BindView(R.id.iv_yinbo)
    ImageView ivYinbo;
    @BindView(R.id.tv_ifly)
    TextView tvIfly;
    private AIUIReceiver mReceiver;
    List<RobotConsultEntity.DataBean.CarListBean.AnswersBean> myanswers = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_robot_consult;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        hideBack(1);
        lay_bg.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        WakeupAction.AIUIWakeUp(this, 0);
        AIUIAction.changeScene(context, AIUIAction.Scene.asr);
        mRobotConsultPresenter = new RobotConsultPresenter(context);
        tvThis1.setVisibility(View.GONE);
        tvRobotan2.setVisibility(View.GONE);
        ivMic2.setVisibility(View.GONE);
        ivYinbo.setVisibility(View.GONE);
        Glide.with(context).load(R.drawable.yinbo).apply(diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(ivYinbo);
        mReceiver = new AIUIReceiver(new OnReceiverListenner() {
            @Override
            public void onReceiver(int type, String content) {
                switch (type){
                    case 0:
                        tvThis1.setText(content);
                        starinternet(content);
                        break;
                    case 1:
                        tvRobotan2.setText(content);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_mic, R.id.iv_mic2, R.id.tv_robotan2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mic:
                ivMic.setVisibility(View.GONE);
                tvRbcon3.setVisibility(View.GONE);
                ivYinbo.setVisibility(View.VISIBLE);
                onclick();
                break;
            case R.id.iv_mic2:
                ivMic2.setVisibility(View.GONE);
                ivYinbo.setVisibility(View.VISIBLE);
                onclick();
                break;
            case R.id.tv_robotan2:
                if (myanswers != null && myanswers.size() > 0) {
                    RobotConsultEntity.DataBean.CarListBean.AnswersBean answersBean = myanswers.get(0);
                    int type = answersBean.getType();
                    Bundle bundle = new Bundle();
                    switch (type) {
                        case 1:
                            //都有
                            bundle.putSerializable("robot", answersBean);
                            MyApplication.openActivity(context, RobotDetailsActivity.class, bundle);
                            break;
                        case 2:
                            //图文
                            bundle.putSerializable("robot", answersBean);
                            MyApplication.openActivity(context, RobotDetailsActivity.class, bundle);
                            break;
                        case 3:
                            //视频
                            Intent intent2 = new Intent(this, VideoActivity.class);
                            intent2.putExtra(Constant.VIDEO, answersBean.getVideoAddress());
                            startActivity(intent2);
                            break;
                    }
                }
                break;
        }
    }

    private void onclick() {
        SpeakAction.getInstance().speak(context, "您好，很高兴为您服务", "wakeUp");
        tvRobotan2.setText("");
    }

    private void starinternet(String s) {
        mRobotConsultPresenter.onCreate();
        mRobotConsultPresenter.attachView(mRobotConsultView);
        mRobotConsultPresenter.getQuestRobotConsult(storeId, s, "1", "");
    }

    private RobotConsultView mRobotConsultView = new RobotConsultView() {
        @Override
        public void onSuccess(RobotConsultEntity mRobotConsultEntity) {
            if (mRobotConsultEntity == null) {
                tvThis1.setVisibility(View.VISIBLE);
                tvRobotan2.setVisibility(View.VISIBLE);
                ivMic2.setVisibility(View.VISIBLE);
                SpeakAction.getInstance().speak(context, "我没有找到答案，请您换个问题", "wakeUp");
                tvRobotan2.setText("我没有找到答案，请您换个问题");
            }
            switch (mRobotConsultEntity.getResultCode()) {
                case 0:
                    tvThis1.setVisibility(View.VISIBLE);
                    tvRobotan2.setVisibility(View.VISIBLE);
                    ivYinbo.setVisibility(View.GONE);
                    ivMic2.setVisibility(View.VISIBLE);
                    SpeakAction.getInstance().speak(context, mRobotConsultEntity.getMsg(), "wakeUp");
                    RobotConsultEntity.DataBean data = mRobotConsultEntity.getData();
                    List<RobotConsultEntity.DataBean.CarListBean> carList = data.getCarList();
                    if (carList == null || carList.size() == 0) {
                        tvRobotan2.setText("null");
                    } else {
                        RobotConsultEntity.DataBean.CarListBean carListBean = carList.get(0);

                        if (!TextUtils.isEmpty(carListBean.getName()) && carListBean.getAnswers() != null && carListBean.getAnswers().size() > 0) {
                            if (!TextUtils.isEmpty(carListBean.getAnswers().get(0).getQuestion())) {
                                tvRobotan2.setText(carListBean.getName() + carListBean.getAnswers().get(0).getQuestion());
                                List<RobotConsultEntity.DataBean.CarListBean.AnswersBean> answers = carListBean.getAnswers();
                                myanswers.clear();
                                myanswers.addAll(answers);
                            }
                        }
                    }
                    break;
                case 1:
                    ivYinbo.setVisibility(View.GONE);
                    ivMic2.setVisibility(View.VISIBLE);
                    tvRobotan2.setVisibility(View.VISIBLE);
                    tvThis1.setVisibility(View.VISIBLE);
                    SpeakAction.getInstance().speak(context, mRobotConsultEntity.getMsg(), "wakeUp");
                    myanswers.clear();
                    tvRobotan2.setText(mRobotConsultEntity.getMsg());
                    break;
            }
        }

        @Override
        public void onError(String result) {
            SpeakAction.getInstance().speak(context, "我没有找到答案，请您换个问题", "wakeUp");
            tvRobotan2.setText("我没有找到答案，请您换个问题");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WakeupAction.AIUISleep(this);
    }

}
