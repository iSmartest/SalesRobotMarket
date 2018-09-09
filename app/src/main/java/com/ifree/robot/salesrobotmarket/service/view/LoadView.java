package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface LoadView extends View {
    void onSuccess(LoadEntity mLoadEntity);
    void onError(String result);
}
