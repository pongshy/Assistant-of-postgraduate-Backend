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

    public static Integer getImp(String color) {
        Integer imp = -1;


        if (Color.red.toString().equals(color)) {
            imp = 0;
        } else if (Color.yellow.toString().equals(color)) {
            imp = 1;
        } else if (Color.blue.toString().equals(color)) {
            imp = 2;
        } else if (Color.green.toString().equals(color)) {
            imp = 3;
        }
        return imp;
    }

    public static String getImportant(Integer i) {
        if (i.equals(0) || i.equals(1)) {
            return "重要";
        } else if (i.equals(2) || i.equals(3)) {
            return "不重要";
        }
        return null;
    }

    public static String getEmergency(Integer i) {
        if (i.equals(0) || i.equals(2)) {
            return "紧急";
        } else if (i.equals(1) || i.equals(3)) {
            return "不紧急";
        }
        return null;
    }

}
