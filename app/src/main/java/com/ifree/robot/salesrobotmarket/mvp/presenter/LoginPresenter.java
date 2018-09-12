package com.ifree.robot.salesrobotmarket.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoginEntity;
import com.ifree.robot.salesrobotmarket.mvp.manager.DataManager;
import com.ifree.robot.salesrobotmarket.mvp.view.LoginView;
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
public class LoginPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private LoginView mLoginView;
    private LoginEntity mLoginEntity;
    public LoginPresenter(Context mContext){
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
        mLoginView = (LoginView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestLogin(String phone,String password,String index,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestLogin(phone,password,index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mLoginEntity != null){
                            mLoginView.onSuccess(mLoginEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mLoginView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(LoginEntity loginEntity) {
                        mLoginEntity = loginEntity;
                    }
                })
        );
    }

}
