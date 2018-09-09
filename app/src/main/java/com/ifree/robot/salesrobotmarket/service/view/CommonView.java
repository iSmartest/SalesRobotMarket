package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.CommonEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface CommonView extends View {
    void onSuccess(CommonEntity mCommonEntity);
    void onError(String result);
}
