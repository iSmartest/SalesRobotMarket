package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.IntegralEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface IntegralView extends View {
    void onSuccess(IntegralEntity mIntegralEntity);
    void onError(String result);
}
