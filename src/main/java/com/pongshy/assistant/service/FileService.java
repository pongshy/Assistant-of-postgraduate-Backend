package com.pongshy.assistant.service;

import com.pongshy.assistant.model.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileService
 * @Description: 文件控制实现接口类
 * @Author: pongshy
 * @Date: 2021/5/2-12:41
 * @Version: V1.0
 **/
public interface FileService {


    Result uploadFile(MultipartFile file);
}
