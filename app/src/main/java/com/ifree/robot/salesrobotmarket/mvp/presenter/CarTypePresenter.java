package com.ifree.robot.salesrobotmarket.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.mvp.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.mvp.manager.DataManager;
import com.ifree.robot.salesrobotmarket.mvp.view.CarTypeView;
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
public class CarTypePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private CarTypeView mCarTypeView;
    private CarTypeEntity mCarTypeEntity;
    public CarTypePresenter(Context mContext){
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
        mCarTypeView = (CarTypeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestCarType(String storeId,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestCarType(storeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarTypeEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mCarTypeEntity != null){
                            mCarTypeView.onSuccess(mCarTypeEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mCarTypeView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CarTypeEntity carTypeEntity) {
                        mCarTypeEntity = carTypeEntity;
                    }
                })
        );
    }

}
