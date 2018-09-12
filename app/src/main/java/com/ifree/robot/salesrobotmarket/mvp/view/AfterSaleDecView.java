package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.AfterSaleDecEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface AfterSaleDecView extends View {
    void onSuccess(AfterSaleDecEntity mAfterSaleDecEntity);
    void onError(String result);
}
