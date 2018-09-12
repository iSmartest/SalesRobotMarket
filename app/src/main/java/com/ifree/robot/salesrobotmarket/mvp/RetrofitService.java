package com.ifree.robot.salesrobotmarket.mvp;

import com.ifree.robot.salesrobotmarket.config.BaseUrl;
import com.ifree.robot.salesrobotmarket.mvp.entity.AfterSaleDecEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommodityIntroduceEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommodityShowEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.CommonEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.ConsumeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.HotCarEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.InsuranceEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.IntegralEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadCodeEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.LoginEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.MaintainEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.RepairEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.RobotConsultEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.mvp.entity.UsedCarInfoEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public interface RetrofitService {
    @GET(BaseUrl.LOGIN_INFO)
    Observable<LoginEntity> getQuestLogin(@Query("phone") String phone,
                                          @Query("password") String password,
                                          @Query("index") String index);

    @GET(BaseUrl.CAR_TYPE)
    Observable<CarTypeEntity> getQuestCarType(@Query("storeId") String storeId);

    @GET(BaseUrl.HOT_CAR)
    Observable<HotCarEntity> getQuestHotCar(@Query("carType") int carType,
                                            @Query("priceType") int priceType,
                                            @Query("type") String type,
                                            @Query("storeId") String storeId,
                                            @Query("page") int page,
                                            @Query("rows") String rows,
                                            @Query("carName") String carName);

    @GET(BaseUrl.SCREEN_CONSULT)
    Observable<ConsultEntity> getQuestConsult(@Query("storeId") String storeId,
                                              @Query("carId") String carId);

    @GET(BaseUrl.EXAMTION)
    Observable<CommonEntity> getQuestQuestion(@Query("storeId") String storeId,
                                              @Query("name") String name,
                                              @Query("age") String age,
                                              @Query("sex") String sex,
                                              @Query("phone") String phone,
                                              @Query("index") String index,
                                              @Query("question1") String question1,
                                              @Query("question2") String question2,
                                              @Query("question3") String question3,
                                              @Query("question4") String question4,
                                              @Query("question5") String question5,
                                              @Query("question6") String question6,
                                              @Query("question7") String question7,
                                              @Query("question8") String question8,
                                              @Query("question9") String question9);

    @GET(BaseUrl.BRIEF)
    Observable<CommodityIntroduceEntity> getQuestCommodityIntroduce(@Query("storeId") String storeId,
                                                                    @Query("page") String page);

    @GET(BaseUrl.SHOW)
    Observable<CommodityShowEntity> getQuestCommodityShow(@Query("storeId") String storeId,
                                                          @Query("page") String page);

    @GET(BaseUrl.LOAD_V_CODE)
    Observable<LoadCodeEntity> getQuestVerificationCode(@Query("storeId") String storeId,
                                                        @Query("mobile") String mobile);

    @GET(BaseUrl.LOAD_LOGIN)
    Observable<LoadEntity> getQuestLoadLogin(@Query("storeId") String storeId,
                                             @Query("phoneNumber") String phoneNumber,
                                             @Query("code") String code,
                                             @Query("sessionId") String sessionId);

    @GET(BaseUrl.INTEGRAL_OR_CONSUMPTION)
    Observable<IntegralEntity> getQuestIntegralInfo(@Query("storeId") String storeId,
                                                    @Query("customerId") String customerId,
                                                    @Query("type") String type,
                                                    @Query("page") String page);

    @GET(BaseUrl.INTEGRAL_OR_CONSUMPTION)
    Observable<ConsumeEntity> getQuestConsumeInfo(@Query("storeId") String storeId,
                                                  @Query("customerId") String customerId,
                                                  @Query("type") String type,
                                                  @Query("page") String page);

    @GET(BaseUrl.AFTER_SALE)
    Observable<RepairEntity> getQuestRepair(@Query("storeId") String storeId,
                                            @Query("customerId") String customerId,
                                            @Query("type") String type);

    @GET(BaseUrl.AFTER_SALE)
    Observable<MaintainEntity> getQuestMaintain(@Query("storeId") String storeId,
                                                @Query("customerId") String customerId,
                                                @Query("type") String type);

    @GET(BaseUrl.AFTER_SALE)
    Observable<InsuranceEntity> getQuestInsurance(@Query("storeId") String storeId,
                                                  @Query("customerId") String customerId,
                                                  @Query("type") String type);

    @GET(BaseUrl.DETAIL)
    Observable<AfterSaleDecEntity> getQuestAfterSaleDec(@Query("storeId") String storeId,
                                                        @Query("customerId") String customerId,
                                                        @Query("type") String type,
                                                        @Query("detailId") String detailId);

    @GET(BaseUrl.HOT_CAR)
    Observable<UsedCarEntity> getQuestUsedCar(@Query("carType") int carType,
                                              @Query("priceType") int priceType,
                                              @Query("type") String type,
                                              @Query("storeId") String storeId,
                                              @Query("page") int page,
                                              @Query("rows") String rows,
                                              @Query("carName") String carName);

    @GET(BaseUrl.USED_CAR_INFO)
    Observable<UsedCarInfoEntity> getQuestUsedCarInfo(@Query("storeId") String storeId,
                                                      @Query("carId") String carId);

    @GET(BaseUrl.TEST_DRIVE)
    Observable<CommonEntity> getQuestTestDriveInfo(@Query("storeId") String storeId,
                                                   @Query("carId") String carId,
                                                   @Query("phoneNumber") String phoneNumber,
                                                   @Query("date") String date);

    @GET(BaseUrl.MAINTAIN)
    Observable<CommonEntity> getQuestMaintainInfo(@Query("storeId") String storeId,
                                                  @Query("carNumber") String carNumber,
                                                  @Query("phoneNumber") String phoneNumber,
                                                  @Query("date") String date,
                                                  @Query("type") String type);

    @GET(BaseUrl.ROBOT_CONSULT)
    Observable<RobotConsultEntity> getQuestRobotConsult(@Query("storeId") String storeId,
                                                        @Query("keyWord") String keyWord,
                                                        @Query("cId") String cId);
}
