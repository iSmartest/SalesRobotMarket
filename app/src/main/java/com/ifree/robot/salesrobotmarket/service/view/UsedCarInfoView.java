package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.UsedCarInfoEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface UsedCarInfoView extends View {
    void onSuccess(UsedCarInfoEntity mUsedCarInfoEntity);
    void onError(String result);
}
