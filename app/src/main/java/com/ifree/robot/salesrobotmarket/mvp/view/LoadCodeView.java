package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.LoadCodeEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface LoadCodeView extends View {
    void onSuccess(LoadCodeEntity mLoadCodeEntity);
    void onError(String result);
}
