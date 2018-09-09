package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.HotCarEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface HotCarView extends View {
    void onSuccess(HotCarEntity mHotCarEntity);
    void onError(String result);
}
