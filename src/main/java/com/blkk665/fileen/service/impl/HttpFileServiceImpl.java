package com.blkk665.fileen.service.impl;

import com.blkk665.fileen.service.HttpFileService;
import com.blkk665.fileen.utils.FileCryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */
@Service
@RequiredArgsConstructor
public class HttpFileServiceImpl implements HttpFileService {
    @Override
    public void encryptFile(MultipartFile mulitPartFile, String encKey) {
        File sourceFile = ;
        File encFile;
        sourceFile = new File(filePath + fileName);
        encFile = new File(filePath + fileName + ".llcc");


        // 加密
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(encFile, true)) {
            FileCryptoUtil.encryptFile(fis, fos, encKey);
        }

    }

    @Override
    public void decryptFile(MultipartFile mulitPartFile, String encKey) {
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
