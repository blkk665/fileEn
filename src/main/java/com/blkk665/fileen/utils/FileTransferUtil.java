package com.blkk665.fileen.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;



/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/7
 */
public class FileTransferUtil {
    /**
     * @description ：上传文件方式:由Spring转到java
     * @date : 2020/6/29
     */
    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用当前时间作为文件名，防止生成的临时文件重复
        try {
            File file = File.createTempFile(System.currentTimeMillis() + "", prefix);

            multiFile.transferTo(file);

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
