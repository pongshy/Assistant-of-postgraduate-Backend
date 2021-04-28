package com.pongshy.assistant.tool;

/**
 * @ClassName: StrTool
 * @Description: 字符工具类
 * @Author: pongshy
 * @Date: 2021/4/28-15:22
 * @Version: V1.0
 **/
public class StrTool {


    public static String byteToStr(byte[] byteArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byteArray.length; ++i) {
            stringBuilder.append(byteToHex(byteArray[i]));
        }
        return stringBuilder.toString();
    }

    /*
     * @Description: 字节转换成16进制字符
     * @Param: [mByte]
     * @return: java.lang.String
     * @Author: pongshy
     * @Date: 2021/4/28 
     * @Version: V1.0
     **/
    public static String byteToHex(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5' ,'6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        char[] tmpArr = new char[2];
        tmpArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tmpArr[1] = Digit[mByte & 0X0F];
        String s = new String(tmpArr);
        return s;
    }


}
