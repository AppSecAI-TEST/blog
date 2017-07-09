package com.zeroq6.blog.operate.web.controller;

import com.zeroq6.blog.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
@Controller("/")
public class IndexController extends BaseController{


    @RequestMapping(value = "vm", method = {RequestMethod.GET, RequestMethod.POST})
    public String vm(String vm) throws Exception{
        return baseDir + "/" + vm;
    }


    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() throws Exception {
        return "redirect:/post" ;
    }

}


