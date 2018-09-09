package com.ifree.robot.salesrobotmarket.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.service.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.service.manager.DataManager;
import com.ifree.robot.salesrobotmarket.service.view.UsedCarView;
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
public class UsedCarPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private UsedCarView mUsedCarView;
    private UsedCarEntity mUsedCarEntity;
    public UsedCarPresenter(Context mContext){
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
        mUsedCarView = (UsedCarView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestUsedCar(int carType, int priceType, String type, String storeId, int page, String rows, String carName,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestUsedCar(carType, priceType, type, storeId, page, rows, carName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsedCarEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mUsedCarEntity != null){
                            mUsedCarView.onSuccess(mUsedCarEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mUsedCarView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(UsedCarEntity UsedCarEntity) {
                        mUsedCarEntity = UsedCarEntity;
                    }
                })
        );
    }

}
