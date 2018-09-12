package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.RobotConsultEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/5.
 * Description:
 */
public interface RobotConsultView extends View {
    void onSuccess(RobotConsultEntity mRobotConsultEntity);
    void onError(String result);
}
