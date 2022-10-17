package com.blkk665.fileen.controller;

import com.blkk665.fileen.utils.FileCryptoUtil;
import com.blkk665.fileen.utils.FilePartUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/11
 */
@RestController
public class FileController {

    private String encKey = "UM0QMU7mWCDQTxaP";


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


    /**
    *
    * AES加密
    */
    @GetMapping("/enFile")
    public void enFile(@RequestParam(value = "filePath") String filePath,
                       @RequestParam(value = "fileName") String fileName) throws Exception {

        encryptFile(filePath, fileName);

    }



    /**
    *
    * AES解密
    */
    @GetMapping("/deFile")
    public void deFile(@RequestParam(value = "filePath") String filePath,
                       @RequestParam(value = "fileName") String fileName) throws Exception {
        decryptFile(filePath, fileName);

    }




    /**
    *
    * 文件切片
    * sourceFilePath 来源文件
    * chunkpath 保存路径,要/结尾
    */
    @GetMapping("/filePart")
    public void filePart(@RequestParam(value = "sourceFilePath") String sourceFilePath,
                         @RequestParam(value = "chunkpath") String chunkpath) throws IOException {
        FilePartUtil.filePart(sourceFilePath, chunkpath);

    }



    /**
     * 合并分片文件
     * chunkFolderPath 待合并的文件存放地址
     * /Users/pluttt/Downloads/jm/cut/
     *
     * mergeFilePathName 合并后的存放地址
     * /Users/pluttt/Downloads/jm/he/1.mkv
     */
    @GetMapping("/fileMerge")
    public void fileMerge(@RequestParam(value = "chunkFolderPath") String chunkFolderPath,
                         @RequestParam(value = "mergeFilePathName") String mergeFilePathName) throws IOException {
        FilePartUtil.fileMerge(chunkFolderPath, mergeFilePathName);

    }






}
