package com.blkk665.fileen.controller;

import com.blkk665.fileen.service.HttpFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/14
 */

@Controller
public class HttpFileController {
    @Autowired
    HttpFileService httpFileService;

    /**
     *
     * AES加密
     */
    @PostMapping("/httpEnFile")
    public String httpEnFile(@RequestParam(value = "multipartFile") MultipartFile multipartFile,
                             @RequestParam(value = "enkey") String enkey,
                             Model model) {
        File file = httpFileService.encryptFile(multipartFile, enkey);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            // 返回加密后的文件名
            model.addAttribute("enFileName", file.getName());
            return "httpfile";
        } else {
            return "加密失败！";
//            model.addAttribute("加密失败！");
        }



    }



    /**
     *
     * AES解密
     */
    @PostMapping("/httpDeFile")
    public String httpDeFile(@RequestParam(value = "multipartFile") MultipartFile multipartFile,
                             @RequestParam(value = "enkey") String enkey,
                             Model model) {


        File file = httpFileService.decryptFile(multipartFile, enkey);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            model.addAttribute("deFileName", file.getName());
            return "httpfile";
        } else {
            return "解密失败！";
        }

    }



    @GetMapping("/download")
    public String download(HttpServletResponse response, String fileName) throws IOException {

        System.out.println("进入下载");
        // 获取指定目录下的文件
        String filePath = "./file/" + fileName;
        File file = new File(filePath);
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
                System.out.println("下载成功!");
                // 文件下载一次就删除
                file.delete();
            }
            catch (Exception e) {
                System.out.println("下载失败!");
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
