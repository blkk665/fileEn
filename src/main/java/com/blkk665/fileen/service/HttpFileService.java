package com.blkk665.fileen.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */
public interface HttpFileService {

    File encryptFile(MultipartFile mulitPartFile, String encKey) throws FileNotFoundException;


//    void decryptFile(MultipartFile mulitPartFile, String encKey);


}
