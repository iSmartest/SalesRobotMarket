package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.MaintainEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface MaintainView extends View {
    void onSuccess(MaintainEntity mMaintainEntity);
    void onError(String result);
}
