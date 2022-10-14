package com.blkk665.fileen.controller;

import com.blkk665.fileen.utils.FileCryptoUtil;
import org.springframework.web.bind.annotation.RestController;

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


    /**
     *
     * AES加密
     */
    void encryptFile(String filePath, String fileName) throws Exception {
        File sourceFile;
        File encFile;
        sourceFile = new File(filePath + fileName);
        encFile = new File(filePath + fileName + ".llcc");


        // 加密
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(encFile, true)) {
            FileCryptoUtil.encryptFile(fis, fos, encKey);
        }

    }



    /**
     *
     * AES解密
     */
    void decryptFile(String filePath, String fileName) throws Exception {
        File encFile;
        File decFile;

        encFile = new File(filePath + fileName);
        decFile = new File(filePath + (fileName.substring(0, fileName.length() - 5)));


        // 解密
        try (FileInputStream fis = new FileInputStream(encFile);
             FileOutputStream fos = new FileOutputStream(decFile, true)) {
            FileCryptoUtil.decryptedFile(fis, fos, encKey);
        }
    }

}
