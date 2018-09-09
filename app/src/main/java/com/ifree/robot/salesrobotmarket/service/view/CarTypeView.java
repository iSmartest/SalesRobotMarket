package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface CarTypeView extends View {
    void onSuccess(CarTypeEntity mCarTypeEntity);
    void onError(String result);
}
