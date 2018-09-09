package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.LoginEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface LoginView extends View {
    void onSuccess(LoginEntity mLoginEntity);
    void onError(String result);
}
