package com.pongshy.assistant.model.myEnum;

/**
 * @ClassName: Color
 * @Description: 颜色类
 * @Author: pongshy
 * @Date: 2021/4/26 20:50
 **/
public enum Color {

    red,
    yellow,
    blue,
    green;

    public static String getColor(Integer i) {
        String color = "";
        switch (i) {
            case 0: {
                color = Color.red.toString();
                break;
            }
            case 1: {
                color =  Color.yellow.toString();
                break;
            }
            case 2: {
                color =  Color.blue.toString();
                break;
            }
            case 3: {
                color =  Color.green.toString();
                break;
            }
        }
        return color;
    }
}
