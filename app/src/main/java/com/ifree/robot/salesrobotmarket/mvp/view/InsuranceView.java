package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.InsuranceEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface InsuranceView extends View {
    void onSuccess(InsuranceEntity mInsuranceEntity);
    void onError(String result);
}
