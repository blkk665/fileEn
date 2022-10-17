package com.blkk665.fileen.service.impl;

import com.blkk665.fileen.service.HttpFileService;
import com.blkk665.fileen.utils.FileCryptoUtil;
import com.blkk665.fileen.utils.FileTransferUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */
@Service
@RequiredArgsConstructor
public class HttpFileServiceImpl implements HttpFileService {
    @Override
    public File encryptFile(MultipartFile mulitPartFile, String encKey) {
        File sourceFile = FileTransferUtil.multipartFileToFile(mulitPartFile);

        // 获取文件名
        String fileName = mulitPartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));


        File encFile;
        encFile = new File("./file/" + fileName + ".llcc");


        // 加密
        assert sourceFile != null;
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(encFile, true)) {
            FileCryptoUtil.encryptFile(fis, fos, encKey);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return encFile;

    }

    @Override
    public File decryptFile(MultipartFile mulitPartFile, String encKey) {
        File encFile = FileTransferUtil.multipartFileToFile(mulitPartFile);

        // 获取文件名
        String fileName = mulitPartFile.getOriginalFilename();
        // 获取文件后缀
//        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File decFile;

        decFile = new File("./file/" + (fileName.substring(0, fileName.length() - 5)));


        // 解密
        try {
            assert encFile != null;
            try (FileInputStream fis = new FileInputStream(encFile);
                     FileOutputStream fos = new FileOutputStream(decFile, true)) {
                FileCryptoUtil.decryptedFile(fis, fos, encKey);
            }
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }


        return decFile;
    }

}
