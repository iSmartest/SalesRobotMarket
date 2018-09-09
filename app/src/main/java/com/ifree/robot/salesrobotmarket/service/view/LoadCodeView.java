package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.LoadCodeEntity;

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
