package com.blkk665.fileen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author pumum
 * @Date 2022/10/11
 */
@Controller
public class PageController {

    @GetMapping("/index")
    public String showIndex() {
//        model.addAttribute("message", "HelloWorld");
        return "index";
    }
}