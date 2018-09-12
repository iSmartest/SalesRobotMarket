package com.ifree.robot.salesrobotmarket.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.mvp.entity.RobotConsultEntity;
import com.ifree.robot.salesrobotmarket.mvp.manager.DataManager;
import com.ifree.robot.salesrobotmarket.mvp.view.RobotConsultView;
import com.ifree.robot.salesrobotmarket.mvp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public class RobotConsultPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private RobotConsultView mRobotConsultView;
    private RobotConsultEntity mRobotConsultEntity;
    public RobotConsultPresenter(Context mContext){
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mRobotConsultView = (RobotConsultView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestRobotConsult(String storeId, String keyWord, String cId,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestRobotConsult(storeId,keyWord,cId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RobotConsultEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mRobotConsultEntity != null){
                            mRobotConsultView.onSuccess(mRobotConsultEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mRobotConsultView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(RobotConsultEntity robotConsultEntity) {
                        mRobotConsultEntity = robotConsultEntity;
                    }
                })
        );
    }

}
