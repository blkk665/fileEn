package com.blkk665.fileen.controller;

import com.blkk665.fileen.service.HttpFileService;
import com.blkk665.fileen.utils.FileCryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */

@RestController
public class HttpFileController {
    @Autowired
    HttpFileService httpFileService;

    /**
     *
     * AES加密
     */
    @PostMapping("/httpEnFile")
    public File enFile(@RequestParam(value = "multipartFile") MultipartFile multipartFile,
                       @RequestParam(value = "enkey") String enkey) throws Exception {
        return httpFileService.encryptFile(multipartFile, enkey);


    }



    /**
     *
     * AES解密
     */
    @GetMapping("/httpDeFile")
    public void deFile(@RequestParam(value = "filePath") String filePath,
                       @RequestParam(value = "fileName") String fileName) throws Exception {
//        decryptFile(filePath, fileName);

    }




}
