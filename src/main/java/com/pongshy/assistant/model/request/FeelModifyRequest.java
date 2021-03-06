package com.pongshy.assistant.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: FeelModifyRequest
 * @Description: 心情修改接受类
 * @Author: pongshy
 * @Date: 2021/5/2-15:29
 * @Version: V1.0
 **/
@Data
public class FeelModifyRequest {


    @NotEmpty(message = "日记不能为空")
    private String words;

    private String imageUrl;

    @NotBlank(message = "id不能为空")
    private String id;
}
