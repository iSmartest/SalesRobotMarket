package com.ifree.robot.salesrobotmarket.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ifree.robot.salesrobotmarket.ui.activity.TrailerVideoActivity;
import com.ifree.robot.salesrobotmarket.ui.base.BaseActivity;
import com.ifree.robot.salesrobotmarket.utils.AppManager;
import com.yunjichina.callback.FaceDetectCallback;
import com.yunjichina.facedetect.demo.FaceDetectAction;
import com.yunjichina.vision.facedetect.FaceRect;

import java.util.List;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/12 0012
 * Description:
 */
public class FaceRecognitionService extends Service {

    public static final String TAG = "FaceRecognitionService";
    public static Handler myHandler = new Handler();
    private boolean isStart;

    //创建服务时调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    //服务执行的操作
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            public void run() {
                //处理具体的逻辑
                FaceDetectAction.init(getApplicationContext()).setTime(20000).setCallback(call);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    //销毁服务时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private FaceDetectCallback call = new FaceDetectCallback() {
        @Override
        public void findFaceHandler(List<FaceRect> featureList, int imageWidth, int imageHeight, List<String> nameList) {
            super.findFaceHandler(featureList, imageWidth, imageHeight, nameList);
            Log.i(TAG, "findFaceHandler: " + featureList.get(0).image);
        }

        @Override
        public void nofindFaceHandler() {
            super.nofindFaceHandler();
            startAD();
        }
    };

    public void startAD() {
        Intent intent = new Intent(getApplicationContext(), TrailerVideoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        AppManager.finishAllActivity();
    }
}
