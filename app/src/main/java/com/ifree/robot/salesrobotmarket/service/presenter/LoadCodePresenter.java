package com.ifree.robot.salesrobotmarket.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.LoadCodeEntity;
import com.ifree.robot.salesrobotmarket.service.manager.DataManager;
import com.ifree.robot.salesrobotmarket.service.view.CarTypeView;
import com.ifree.robot.salesrobotmarket.service.view.LoadCodeView;
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
public class LoadCodePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private LoadCodeView mLoadCodeView;
    private LoadCodeEntity mLoadCodeEntity;
    public LoadCodePresenter(Context mContext){
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
        mLoadCodeView = (LoadCodeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestVerificationCode(String storeId, String mobile,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestVerificationCode(storeId,mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoadCodeEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mLoadCodeEntity != null){
                            mLoadCodeView.onSuccess(mLoadCodeEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mLoadCodeView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(LoadCodeEntity loadCodeEntity) {
                        mLoadCodeEntity = loadCodeEntity;
                    }
                })
        );
    }

}
