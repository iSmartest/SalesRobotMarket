package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.RepairEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface RepairView extends View {
    void onSuccess(RepairEntity mRepairEntity);
    void onError(String result);
}
