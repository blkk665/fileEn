package com.blkk665.fileen.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */
public interface HttpFileService {

    void encryptFile(MultipartFile mulitPartFile, String encKey);


    void decryptFile(MultipartFile mulitPartFile, String encKey);


}
