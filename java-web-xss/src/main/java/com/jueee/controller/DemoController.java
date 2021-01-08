package com.jueee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class DemoController {

    @RequestMapping("/demo")
    public String demo(HttpServletRequest request, HttpServletResponse response, ModelMap m) {
        log.info("into demo page");
        return "demo";
    }

    @ResponseBody
    @RequestMapping("/demoAction")
    public String demoAction(@RequestParam(value = "name") String name,
                             HttpServletRequest request, HttpServletResponse response, ModelMap m) {
        log.info("name:"+name);
        return "name is:"+name;
    }
}
