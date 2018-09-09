package com.ifree.robot.salesrobotmarket.service.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.service.entity.MaintainEntity;
import com.ifree.robot.salesrobotmarket.service.manager.DataManager;
import com.ifree.robot.salesrobotmarket.service.view.MaintainView;
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
public class MaintainPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private MaintainView mMaintainView;
    private MaintainEntity mMaintainEntity;
    public MaintainPresenter(Context mContext){
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
        mMaintainView = (MaintainView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestMaintain(String storeId, String customerId, String type,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestMaintain(storeId,customerId,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MaintainEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mMaintainEntity != null){
                            mMaintainView.onSuccess(mMaintainEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mMaintainView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(MaintainEntity MaintainEntity) {
                        mMaintainEntity = MaintainEntity;
                    }
                })
        );
    }

}
