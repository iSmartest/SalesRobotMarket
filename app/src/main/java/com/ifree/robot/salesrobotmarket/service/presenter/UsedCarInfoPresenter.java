package com.ifree.robot.salesrobotmarket.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.service.entity.UsedCarInfoEntity;
import com.ifree.robot.salesrobotmarket.service.manager.DataManager;
import com.ifree.robot.salesrobotmarket.service.view.UsedCarInfoView;
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
public class UsedCarInfoPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private UsedCarInfoView mUsedCarInfoView;
    private UsedCarInfoEntity mUsedCarInfoEntity;
    public UsedCarInfoPresenter(Context mContext){
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
        mUsedCarInfoView = (UsedCarInfoView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestUsedCarInfo(String storeId, String carId,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestUsedCarInfo(storeId, carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsedCarInfoEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mUsedCarInfoEntity != null){
                            mUsedCarInfoView.onSuccess(mUsedCarInfoEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mUsedCarInfoView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(UsedCarInfoEntity UsedCarInfoEntity) {
                        mUsedCarInfoEntity = UsedCarInfoEntity;
                    }
                })
        );
    }

}
