package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;

/**
 * @ClassName: HomePageService
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/30 22:02
 **/
public interface HomePageService {


    /*
     * @Description: 签到接口
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:06
     */
    Result signInOneDay(String openid);

    /*
     * @Description: 获取签到记录
     * @Method: [openid]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 22:17
     */
    Result getSignRecord(String openid);

    /*
     * @Description: 增加心灵鸡汤
     * @Method: [sentence]
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:02
     */
    Result insertSentence(String sentence, Integer sentiment);

    /*
     * @Description: 获取心灵鸡汤
     * @Method: []
     * @Return: com.pongshy.assistant.model.Result
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/30 23:16
     */
    Result getSentence();

    /*
     * @Description: 选择植物
     * @Param: [plantId, openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/4
     * @Version: V1.0
     **/
    Result choosePlant(Integer plantId, String openid);

    /*
     * @Description: 获取当天设定的植物和任务完成百分比
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/4
     * @Version: V1.0
     **/
    Result getPlantAndPercent(String openid);

}
