package com.ifree.robot.salesrobotmarket.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.service.manager.DataManager;
import com.ifree.robot.salesrobotmarket.service.view.CarTypeView;
import com.ifree.robot.salesrobotmarket.service.view.ConsultView;
import com.ifree.robot.salesrobotmarket.service.view.View;

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
public class ConsultPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private ConsultView mConsultView;
    private ConsultEntity mConsultEntity;
    public ConsultPresenter(Context mContext){
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
        mConsultView = (ConsultView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestConsult(String storeId,String carId,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestConsult(storeId, carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConsultEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mConsultEntity != null){
                            mConsultView.onSuccess(mConsultEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mConsultView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(ConsultEntity carTypeEntity) {
                        mConsultEntity = carTypeEntity;
                    }
                })
        );
    }

}