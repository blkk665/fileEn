package com.blkk665.fileen.controller;

import com.blkk665.fileen.service.HttpFileService;
import com.blkk665.fileen.utils.FileCryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

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



    @GetMapping("/download2")
    public String downloadFile2( HttpServletResponse response) throws IOException {
        // 获取指定目录下的文件
        String fileName = "/Users/pluttt/Downloads/2.mp4";
        File file = new File(fileName);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
            }
            catch (Exception e) {
                System.out.println("Download the song failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }




}
