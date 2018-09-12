package com.ifree.robot.salesrobotmarket.mvp.presenter;

import android.content.Intent;

import com.ifree.robot.salesrobotmarket.mvp.view.View;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到


}
