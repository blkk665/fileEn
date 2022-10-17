package com.blkk665.fileen;

import com.blkk665.fileen.utils.DESUtil;


import java.io.*;

/**
 * @Description
 * @Author blkk665
 * @Date 2022/10/12
 */
public class main {
    public static void main(String[] args) throws IOException {
       File file1 = new File("./file/1.txt");
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
