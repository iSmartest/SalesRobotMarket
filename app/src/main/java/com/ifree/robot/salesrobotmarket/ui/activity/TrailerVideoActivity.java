package com.ifree.robot.salesrobotmarket.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.VideoView;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;
import com.ifree.robot.salesrobotmarket.utils.SPUtil;

import static com.ifree.robot.salesrobotmarket.config.BaseUrl.IMAGE_HTTP;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */

public class TrailerVideoActivity extends Activity {

    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_video);
        videoView = findViewById(R.id.videoView);
        initView();
    }


    protected void initView() {
        final String uri = IMAGE_HTTP + SPUtil.getString(TrailerVideoActivity.this, "address");
        videoView.setVideoPath(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
//                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
                mp.start();
                mp.setLooping(true);
            }
        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(uri);
                videoView.start();
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                Intent intent = new Intent(this, ConsultActivity.class);
                intent.putExtra(Constant.CONSULT, 1);
                intent.putExtra(Constant.INTENTHOTCAR, SPUtil.getString(TrailerVideoActivity.this, "carId"));
                startActivity(intent);
                finish();
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override

    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                break;
            case KeyEvent.ACTION_UP:
                break;

        }
        return super.dispatchKeyEvent(event);

    }


    @Override

    protected void onStop() {
        super.onStop();
        videoView.stopPlayback();

    }


    @Override

    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();

    }
}