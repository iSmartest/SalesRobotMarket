package com.ifree.robot.salesrobotmarket.ui.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.hotelrobot.common.utils.RkOperationUtil;
import com.ifree.robot.salesrobotmarket.R;
import com.ifree.robot.salesrobotmarket.config.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static com.ifree.robot.salesrobotmarket.config.BaseUrl.IMAGE_HTTP;


public class VideoActivity extends Activity {

    @BindView(R.id.jz_videoplayer)
    JZVideoPlayerStandard jzVideoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        //获取视频地址
        String uri = IMAGE_HTTP + getIntent().getStringExtra(Constant.VIDEO);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        jzVideoplayer.backButton.setVisibility(View.VISIBLE);
        jzVideoplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        jzVideoplayer.fullscreenButton.setVisibility(View.GONE);
        jzVideoplayer.titleTextView.setVisibility(View.GONE);
        jzVideoplayer.setUp(uri, JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
        //设置播放器封面
        jzVideoplayer.startVideo();

    }

    @Override

    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}