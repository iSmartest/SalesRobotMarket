package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.ConsultEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface ConsultView extends View {
    void onSuccess(ConsultEntity mConsultEntity);
    void onError(String result);
}