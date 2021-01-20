package com.jueee.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class UploadController {

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap m) {
        log.info("into update page");
        return "update";
    }

    @ResponseBody
    @RequestMapping("/updateAction")
    public String updateAction(HttpServletRequest request, HttpServletResponse response, ModelMap m) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        MultipartFile name = mRequest.getFile("name");
        log.info("name:"+name.getOriginalFilename());
        return "name is:"+name.getOriginalFilename();
    }
}
