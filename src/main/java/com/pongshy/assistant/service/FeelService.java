package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.model.request.FeelModifyRequest;
import com.pongshy.assistant.model.request.FeelRequest;

/**
 * @ClassName: FeelService
 * @Description: 心情模块实现类接口
 * @Author: pongshy
 * @Date: 2021/5/2-12:14
 * @Version: V1.0
 **/
public interface FeelService {


    /*
     * @Description: 添加用户的心情和句子
     * @Param: [feelRequest]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    Result commitFeelAndWords(FeelRequest feelRequest);

    /*
     * @Description: 获取图片和文字
     * @Param: [openid]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    Result getImageAndWord(String openid);

    /*
     * @Description: 修改心情
     * @Param: [request]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    Result modifyFeel(FeelModifyRequest request);

    /*
     * @Description: 删除指定文字和图片
     * @Param: [id]
     * @return: com.pongshy.assistant.model.Result
     * @Author: pongshy
     * @Date: 2021/5/2
     * @Version: V1.0
     **/
    Result deleteFeel(String id);

}
