package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.ConsumeEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface ConsumeView extends View {
    void onSuccess(ConsumeEntity mConsumeEntity);
    void onError(String result);
}
