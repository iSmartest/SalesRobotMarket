package com.ifree.robot.salesrobotmarket.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ifree.robot.salesrobotmarket.R;

import static com.ifree.robot.salesrobotmarket.config.BaseUrl.IMAGE_HTTP;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/7/19.
 * Description:
 */
public class GlideUtils{
    public static void imageLoader(Context context, String image, ImageView imageView){
        String pic = IMAGE_HTTP + image;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.error(R.mipmap.ic_launcher);
        Glide.with(context).load(pic).apply(requestOptions).into(imageView);
    }
}
