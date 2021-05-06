package com.pongshy.assistant.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: WechatInforRequest
 * @Description: 微信名和头像保存类
 * @Author: pongshy
 * @Date: 2021/5/6 20:17
 **/
@Data
@ApiModel(value = "微信名和头像保存类", description = "微信名和头像保存类")
public class WechatInforRequest {


    @ApiModelProperty(value = "微信头像URL", required = true)
    private String wimage;

    @ApiModelProperty(value = "微信名", required = true, example = "pongshy")
    private String wname;

    @NotEmpty(message = "openid不能为空")
    @ApiModelProperty(value = "微信的openid", required = true)
    private String openid;
}
