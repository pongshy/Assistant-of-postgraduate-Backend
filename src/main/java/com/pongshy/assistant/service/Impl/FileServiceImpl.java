package com.pongshy.assistant.service.Impl;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.exception.EmAllException;
import com.pongshy.assistant.model.Result;
import com.pongshy.assistant.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @ClassName: FileServiceImpl
 * @Description: 文件控制具体实现接口
 * @Author: pongshy
 * @Date: 2021/5/2-12:42
 * @Version: V1.0
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {


    @Value("${upload.path}")
    private String directory;

    private final String path = "http://www.pongshy.com:8084/";


    @Override
    public Result uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new AllException(EmAllException.ErrorCode, "上传文件为空");
            }
            //上传文件的id
            String fileId = UUID.randomUUID().toString();
            //源文件名
            String originalFileName = ChangeCharSet(file.getOriginalFilename());
            //在指定目录下存放文件
            String responseUrl = ChangeCharSet(path + fileId + "---" + originalFileName);
            String absolutePath = ChangeCharSet(directory + File.separator + fileId + "---" + originalFileName);
            log.info("absolutePath: " + absolutePath);
            // 如果存放文件的文件夹不存在，则创建文件夹
            File destDirectory = new File(directory);
            if (!destDirectory.exists()){
                destDirectory.mkdirs();
            }

            try (OutputStream os = new FileOutputStream(absolutePath)) {
                os.write(file.getBytes());
            }  catch (FileNotFoundException e) {
                e.printStackTrace();
                return Result.error("上传失败", 500);
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传失败", 500);
            }
            return Result.success((Object) responseUrl);

        } catch (AllException ex) {
            return Result.error(ex);
        }
    }

    public String ChangeCharSet(String str) {
        if (str != null) {
            //用默认字符编码解吗字符串。与系统相关，中文windows默认编码为GB2312
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, StandardCharsets.UTF_8);
        }
        return null;
    }
}
