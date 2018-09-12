package com.ifree.robot.salesrobotmarket.mvp.view;

import com.ifree.robot.salesrobotmarket.mvp.entity.CommodityIntroduceEntity;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/3.
 * Description:
 */
public interface CommodityIntroduceView extends View {
    void onSuccess(CommodityIntroduceEntity mCommodityIntroduceEntity);
    void onError(String result);
}