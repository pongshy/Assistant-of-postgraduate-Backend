package com.pongshy.assistant.tool;

import org.springframework.util.ObjectUtils;

import java.text.DecimalFormat;

/**
 * @ClassName: NumTool
 * @Description: 数字处理工具类
 * @Author: pongshy
 * @Date: 2021/5/6-12:53
 * @Version: V1.0
 **/
public class NumTool {


    /**
     * @Description: 浮点数保留两位小数
     * @Param: [num]
     * @return: java.lang.Double
     * @Author: pongshy
     * @Date: 2021/5/6
     * @Version: V1.0
     **/
    public static Double saveTwoDecimal(Double num) {
        if (ObjectUtils.isEmpty(num)) {
            return null;
        }
        String tmp = new DecimalFormat("#.00").format(num);
        return Double.parseDouble(tmp);
    }
}
