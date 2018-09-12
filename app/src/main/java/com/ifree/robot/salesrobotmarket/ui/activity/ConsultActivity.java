package com.ifree.robot.salesrobotmarket.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
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
import com.ifree.robot.salesrobotmarket.custom.FlowGroupView;
import com.ifree.robot.salesrobotmarket.dialog.SelectCarStyleDialog;
import com.ifree.robot.salesrobotmarket.mvp.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.mvp.presenter.CarTypePresenter;
import com.ifree.robot.salesrobotmarket.mvp.presenter.ConsultPresenter;
import com.ifree.robot.salesrobotmarket.mvp.view.CarTypeView;
import com.ifree.robot.salesrobotmarket.mvp.view.ConsultView;
import com.ifree.robot.salesrobotmarket.ui.adapter.ConSellingAdapter;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.AppManager;
import com.ifree.robot.salesrobotmarket.utils.GlideUtils;
import com.ifree.robot.salesrobotmarket.utils.PriceUtils;
import com.ifree.robot.salesrobotmarket.utils.ToastUtils;

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

public class ConsultActivity extends BaseActivity {
    private CarTypePresenter mCarTypePresenter;
    private ConsultPresenter mConsultPresenter;
    @BindView(R.id.iv_consult)
    ImageView ivConsult;
    @BindView(R.id.tv_conprice1)
    TextView tvConprice1;
    @BindView(R.id.tv_conprice2)
    TextView tvConprice2;
    @BindView(R.id.tv_dondisplacement)
    TextView tvDondisplacement;
    @BindView(R.id.tv_congearbox)
    TextView tvCongearbox;
    @BindView(R.id.tv_concarsturcture)
    TextView tvConcarsturcture;
    @BindView(R.id.rl_hotcardeails)
    RelativeLayout rlHotcardeails;
    @BindView(R.id.flv_consult)
    FlowGroupView flvConsult;
    @BindView(R.id.iv_consultqus)
    ImageView ivConsultqus;
    @BindView(R.id.rv_consultselling)
    RecyclerView rvConsultselling;
    @BindView(R.id.ll_consult)
    LinearLayout llConsult;
    private ArrayList<ConsultEntity.DataBean.AnswersBean> datas = new ArrayList<>();
    private String carId;
    private int intExtra = 2;
    private List<CarTypeEntity.DataBean.CarTypeListBean> mList = new ArrayList<>();
    private ConSellingAdapter conSellingAdapter;
    private List<ConsultEntity.DataBean.BrightPointBean> brightPoint = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consult;

    }

    @Override
    protected void initView() {
        hideBack(4);
        mCarTypePresenter = new CarTypePresenter(context);
        mConsultPresenter = new ConsultPresenter(context);
        intExtra = getIntent().getIntExtra(Constant.CONSULT, 2);
        carId = getIntent().getStringExtra(Constant.INTENTHOTCAR);
        final Dialog dialog = new Dialog(this, R.style.Transparent);
        View view = LayoutInflater.from(this).inflate(R.layout.intodialog, null, false);
        final ImageView imageView = view.findViewById(R.id.iv_condialog);
        rvConsultselling.setLayoutManager(new LinearLayoutManager(this));
        conSellingAdapter = new ConSellingAdapter(context,brightPoint);
        rvConsultselling.setAdapter(conSellingAdapter);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        switch (intExtra) {
            case 1:
                dialog.show();
                break;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                yunji();
            }
        });
    }


    private void yunji() {
//        AIUIAction.changeVoicer(context, AIUIAction.Voicer.mengmeng, 50, 50);
//        CognizePerform.create(callback).start(context);
//        FaceDetectAction.init(context).setTime(10000).setCallback(call);
    }

//    private FaceDetectCallback call = new FaceDetectCallback() {
//        @Override
//        public void findFaceHandler(List<FaceRect> featureList, int imageWidth, int imageHeight, List<String> nameList) {
//            super.findFaceHandler(featureList, imageWidth, imageHeight, nameList);
//            if (featureList != null && featureList.size() > 0) {
//                FaceRect faceRect = featureList.get(0);
//                if (faceRect.confidence > 0.75) {
//                    SpeakAction.getInstance().speak(context, faceRect.name + "您好");
//                } else {
//                    if (TextUtils.equals(FaceDetectConstant.Gender.Male, faceRect.gender)) {
//                        SpeakAction.getInstance().speak(context, "先生您好");
//                        Log.i("TAG", "FindFacesHandler: "  + featureList.get(0).image);
//                    } else if (TextUtils.equals(FaceDetectConstant.Gender.Female, faceRect.gender)) {
//                        SpeakAction.getInstance().speak(context, "女士您好");
//                        Log.i("TAG", "FindFacesHandler: "  + featureList.get(0).image);
//                    } else {
//                        SpeakAction.getInstance().speak(context, "您好");
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void nofindFaceHandler() {
//            super.nofindFaceHandler();
//        }
//    };
//    private PerformFaceDetectCallBack callback = new PerformFaceDetectCallBack() {
//        @Override
//        public void FindFacesHandler(final List<FaceRect> featureList) {
//            super.FindFacesHandler(featureList);
//
//        }
//
//        @Override
//        public void FindFacesNotMiddleHandler(final Context context, final float rad) {
//            super.FindFacesNotMiddleHandler(context, rad);
//            // 识别到人脸但人脸不在屏幕正中间，此处会自动调整机器人角度，使机器人正对着人。
//            // 建议在此位置发送唤醒 0 波束麦克风，实现人脸识别唤醒功能
//            WakeupAction.AIUIWakeUp(context, 0);
//        }
//
//        @Override
//        public void NotFindFacesHandler() {
//            super.NotFindFacesHandler();
//            ConsultActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context, "没有识别到人脸", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    };


    @Override
    protected void loadData() {
        mConsultPresenter.onCreate();
        mConsultPresenter.attachView(mmConsultView);
        mConsultPresenter.getQuestConsult(storeId,carId,"加载中...");
    }

    private ConsultView mmConsultView = new ConsultView() {
        @Override
        public void onSuccess(ConsultEntity mConsultEntity) {
            if (mConsultEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mConsultEntity.getMsg());
                return;
            }
            ConsultEntity.DataBean data = mConsultEntity.getData();
            String getprice = PriceUtils.getprice(data.getGuiPrice());
            GlideUtils.imageLoader(context, mConsultEntity.getData().getImage(), ivConsult);
            tvConprice1.setText(getprice);
            tvConcarsturcture.setText(data.getStructure());
            tvCongearbox.setText(data.getGearbox());
            setTitleText(data.getName());
            tvDondisplacement.setText(data.getMotor());
            tvConprice2.setText(PriceUtils.getprice(data.getPrice()));
            List<ConsultEntity.DataBean.BrightPointBean> dataBrightPoint = data.getBrightPoint();
            if (dataBrightPoint != null && !dataBrightPoint.isEmpty() && dataBrightPoint.size() > 0) {
                brightPoint.clear();
                brightPoint.addAll(dataBrightPoint);
                conSellingAdapter.notifyDataSetChanged();
            }

            List<ConsultEntity.DataBean.AnswersBean> answers = data.getAnswers();
            if (answers != null && !answers.isEmpty() && answers.size() > 0) {
                datas.clear();
                datas.addAll(answers);
            }
            setTwoFlowLayout();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };

    private void starInternets() {
        mCarTypePresenter.onCreate();
        mCarTypePresenter.attachView(mCarTypeView);
        mCarTypePresenter.getQuestCarType(storeId, "加载中...");
    }


    private CarTypeView mCarTypeView = new CarTypeView() {
        @Override
        public void onSuccess(CarTypeEntity mCarTypeEntity) {
            if (mCarTypeEntity.getResultCode().equals("1")) {
                ToastUtils.makeText(context, mCarTypeEntity.getMsg());
                return;
            }
            List<CarTypeEntity.DataBean.CarTypeListBean> carTypeList = mCarTypeEntity.getData().getCarTypeList();
            if (carTypeList != null && !carTypeList.isEmpty() && carTypeList.size() > 0) {
                mList.addAll(carTypeList);
                SelectCarStyleDialog selectCarStyleDialog = new SelectCarStyleDialog(context, mList, new SelectCarStyleDialog.OnSureBtnClickListener() {
                    @Override
                    public void sure(int position) {
                        carId = mList.get(position).getId() + "";
                        loadData();
                    }
                });
                selectCarStyleDialog.show();
            } else {
                ToastUtils.makeText(context, "没有可选车型");
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context, result);
        }
    };


    private void setTwoFlowLayout() {
        if (datas == null || datas.size() == 0) {
            return;
        }
        flvConsult.removeAllViews();
        for (int i = 0; i < datas.size(); i++) {
            addTextView(datas.get(i).getQuestion(), i);
        }
    }

    private void addTextView(String s, int i) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(20, 20, 20, 20);
        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.hot_new_question);
        child.setText(s);
        child.setTextColor(Color.WHITE);
        child.setTextSize(16);
        child.setGravity(Gravity.CENTER);
        child.setPadding(25, 15, 25, 15);
        initEvents(child, i);
        flvConsult.addView(child);
    }

    private void initEvents(TextView child, final int i) {
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultEntity.DataBean.AnswersBean answersBean = datas.get(i);
                if (answersBean == null) {
                } else {
                    int type = answersBean.getType();
                    Bundle bundle = new Bundle();
                    switch (type) {
                        case 1:
                            bundle.putSerializable(Constant.CONSULT, answersBean);
                            MyApplication.openActivity(context,SlideShowActivity.class,bundle);
                            break;
                        case 2:
                            bundle.putSerializable(Constant.CONSULT, answersBean);
                            MyApplication.openActivity(context,SlideShowActivity.class,bundle);
                            break;
                        case 3:
                            bundle.putString(Constant.VIDEO, answersBean.getVideoAddress());
                            MyApplication.openActivity(context,VideoActivity.class,bundle);
                            break;
                    }
                }
            }
        });
    }


    @OnClick({R.id.tv_base_rightText, R.id.iv_consultqus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_base_rightText:
                mList.clear();
                starInternets();
                break;
            case R.id.iv_consultqus:
                MyApplication.openActivity(context,QuestionActivity.class);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (intExtra == 1) {
                Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);
                AppManager.finishActivity();
            } else {
                AppManager.finishActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

