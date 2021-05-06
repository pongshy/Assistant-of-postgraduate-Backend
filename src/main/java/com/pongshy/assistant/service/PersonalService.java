package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.DayRequest;

/**
 * @ClassName: PersonalService
 * @Description: 个人主页实现接口类
 * @Author: pongshy
 * @Date: 2021/5/6-12:18
 * @Version: V1.0
 **/
public interface PersonalService {

    /*
     * @Description: 获取个人主页的已完成任务数，总任务数，打卡天数
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    Result getPersonalInformation(String openid);

    /*
     * @Description: 设置倒数日和title
     * @Param: [dayRequest]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    Result saveCountdownDay(DayRequest dayRequest);

    /*
     * @Description: 获取结束日期和标题
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    Result getCountdownDayAndTitle(String openid);

    /*
     * @Description: 修改倒数日和title
     * @Method: [dayRequest]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/5/6 22:17
     */
    Result modifyDayAndTitle(DayRequest dayRequest);

}
