package com.ifree.robot.salesrobotmarket.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import com.ifree.robot.salesrobotmarket.dialog.ProgressDialog;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommonEntity;
import com.ifree.robot.salesrobotmarket.mvp.manager.DataManager;
import com.ifree.robot.salesrobotmarket.mvp.view.CommonView;
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
public class QuestionPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private CommonView mCommonView;
    private CommonEntity mCommonEntity;
    public QuestionPresenter(Context mContext){
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
        mCommonView = (CommonView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getQuestQuestion(String storeId,String name,String age,String sex,
                                String phone,String index,String question1,String question2,
                                String question3,String question4,String question5,String question6,
                                String question7,String question8,String question9,String mContent){
        final Dialog dialog = ProgressDialog.createLoadingDialog(mContext,mContent);
        dialog.show();
        mCompositeSubscription.add(manager.getQuestQuestion(storeId, name,age,sex,phone,index,question1,question2,question3,question4,question5,question6,question7,question8,question9)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonEntity>() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                        if (mCommonEntity != null){
                            mCommonView.onSuccess(mCommonEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                        mCommonView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(CommonEntity commonEntity) {
                        mCommonEntity = commonEntity;
                    }
                })
        );
    }

}
