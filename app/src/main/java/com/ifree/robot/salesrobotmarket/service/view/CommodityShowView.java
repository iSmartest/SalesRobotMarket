package com.ifree.robot.salesrobotmarket.service.view;

import com.ifree.robot.salesrobotmarket.service.entity.CommodityShowEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface CommodityShowView extends View {
    void onSuccess(CommodityShowEntity mCommodityShowEntity);
    void onError(String result);
}
