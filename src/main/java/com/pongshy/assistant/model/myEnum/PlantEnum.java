package com.pongshy.assistant.model.myEnum;

/**
 * @ClassName: PlantEnum
 * @Description: 植物枚举类
 * @Author: pongshy
 * @Date: 2021/5/4-22:49
 * @Version: V1.0
 **/
public enum PlantEnum {


    carrot,
    tomato,
    onion;

    public static String getPlant(Integer i) {
        switch (i) {
            case 1: {
                return PlantEnum.carrot.toString();
            }
            case 2: {
                return PlantEnum.tomato.toString();
            }
            case 3: {
                return PlantEnum.onion.toString();
            }
            default: {
                return null;
            }
        }
    }
}
