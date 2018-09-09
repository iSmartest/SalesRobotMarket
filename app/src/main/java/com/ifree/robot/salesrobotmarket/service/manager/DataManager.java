package com.ifree.robot.salesrobotmarket.service.manager;

import android.content.Context;
import com.ifree.robot.salesrobotmarket.service.RetrofitHelper;
import com.ifree.robot.salesrobotmarket.service.RetrofitService;
import com.ifree.robot.salesrobotmarket.service.entity.AfterSaleDecEntity;
import com.ifree.robot.salesrobotmarket.service.entity.CarTypeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.CommodityIntroduceEntity;
import com.ifree.robot.salesrobotmarket.service.entity.CommodityShowEntity;
import com.ifree.robot.salesrobotmarket.service.entity.CommonEntity;
import com.ifree.robot.salesrobotmarket.service.entity.ConsultEntity;
import com.ifree.robot.salesrobotmarket.service.entity.ConsumeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.HotCarEntity;
import com.ifree.robot.salesrobotmarket.service.entity.InsuranceEntity;
import com.ifree.robot.salesrobotmarket.service.entity.IntegralEntity;
import com.ifree.robot.salesrobotmarket.service.entity.LoadCodeEntity;
import com.ifree.robot.salesrobotmarket.service.entity.LoadEntity;
import com.ifree.robot.salesrobotmarket.service.entity.LoginEntity;
import com.ifree.robot.salesrobotmarket.service.entity.MaintainEntity;
import com.ifree.robot.salesrobotmarket.service.entity.RepairEntity;
import com.ifree.robot.salesrobotmarket.service.entity.RobotConsultEntity;
import com.ifree.robot.salesrobotmarket.service.entity.UsedCarEntity;
import com.ifree.robot.salesrobotmarket.service.entity.UsedCarInfoEntity;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/13.
 * Description:
 */
public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<LoginEntity> getQuestLogin(String phone,String password,String index){
        return mRetrofitService.getQuestLogin(phone,password,index);
    }

    public Observable<CarTypeEntity> getQuestCarType(String storeId){
        return mRetrofitService.getQuestCarType(storeId);
    }

    public Observable<HotCarEntity> getQuestHotCar(int carType, int priceType, String type, String storeId, int page, String rows, String carName){
        return mRetrofitService.getQuestHotCar(carType, priceType, type, storeId, page, rows, carName);
    }

    public Observable<UsedCarEntity> getQuestUsedCar(int carType, int priceType, String type, String storeId, int page, String rows, String carName){
        return mRetrofitService.getQuestUsedCar(carType, priceType, type, storeId, page, rows, carName);
    }

    public Observable<ConsultEntity> getQuestConsult(String storeId, String carId){
        return mRetrofitService.getQuestConsult(storeId, carId);
    }

    public Observable<CommonEntity> getQuestQuestion(String storeId,String name,String age,String sex,
                                                    String phone,String index,String question1,String question2,
                                                    String question3,String question4,String question5,String question6,
                                                    String question7,String question8,String question9){
        return mRetrofitService.getQuestQuestion(storeId, name,age,sex,phone,index,question1,question2,question3,question4,question5,question6,question7,question8,question9);
    }

    public Observable<CommodityIntroduceEntity> getQuestCommodityIntroduce(String storeId, String page){
        return mRetrofitService.getQuestCommodityIntroduce(storeId, page);
    }

    public Observable<CommodityShowEntity> getQuestCommodityShow(String storeId, String page){
        return mRetrofitService.getQuestCommodityShow(storeId, page);
    }

    public Observable<LoadCodeEntity> getQuestVerificationCode(String storeId, String mobile){
        return mRetrofitService.getQuestVerificationCode(storeId, mobile);
    }

    public Observable<LoadEntity> getQuestLoadLogin(String storeId, String phoneNumber,String code,String sessionId){
        return mRetrofitService.getQuestLoadLogin(storeId, phoneNumber,code,sessionId);
    }

    public Observable<IntegralEntity> getQuestIntegralInfo(String storeId, String customerId, String type, String page){
        return mRetrofitService.getQuestIntegralInfo(storeId, customerId,type,page);
    }

    public Observable<ConsumeEntity> getQuestConsumeInfo(String storeId, String customerId, String type, String page){
        return mRetrofitService.getQuestConsumeInfo(storeId, customerId,type,page);
    }

    public Observable<RepairEntity> getQuestRepair(String storeId, String customerId, String type){
        return mRetrofitService.getQuestRepair(storeId, customerId,type);
    }

    public Observable<MaintainEntity> getQuestMaintain(String storeId, String customerId, String type){
        return mRetrofitService.getQuestMaintain(storeId, customerId,type);
    }

    public Observable<InsuranceEntity> getQuestInsurance(String storeId, String customerId, String type){
        return mRetrofitService.getQuestInsurance(storeId, customerId,type);
    }

    public Observable<AfterSaleDecEntity> getQuestAfterSaleDec(String storeId, String customerId, String type, String detailId){
        return mRetrofitService.getQuestAfterSaleDec(storeId, customerId,type,detailId);
    }

    public Observable<UsedCarInfoEntity> getQuestUsedCarInfo(String storeId, String carId){
        return mRetrofitService.getQuestUsedCarInfo(storeId, carId);
    }
    public Observable<CommonEntity> getQuestTestDriveInfo(String storeId,String carId,String phoneNumber,String date){
        return mRetrofitService.getQuestTestDriveInfo(storeId, carId,phoneNumber,date);
    }

    public Observable<CommonEntity> getQuestMaintainInfo(String storeId,String carNumber,String phoneNumber,String date,String type){
        return mRetrofitService.getQuestMaintainInfo(storeId, carNumber,phoneNumber,date,type);
    }

    public Observable<RobotConsultEntity> getQuestRobotConsult(String storeId, String keyWord, String cId){
        return mRetrofitService.getQuestRobotConsult(storeId,keyWord,cId);
    }

}
