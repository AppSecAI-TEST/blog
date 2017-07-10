package com.zeroq6.blog.operate.web.controller;

import com.zeroq6.blog.common.base.BaseController;
import com.zeroq6.blog.common.domain.DictDomain;
import com.zeroq6.blog.operate.service.DictService;
import com.zeroq6.blog.operate.service.PostService;
import com.zeroq6.common.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by yuuki asuna on 2017/5/24.
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController{

    @Autowired
    private PostService postService;

    @Autowired
    private DictService dictService;


    @ModelAttribute
    public void loadState(Model model) {
        model.addAttribute("menu", "about");
        model.addAttribute("title", "关于");
        model.addAllAttributes(postService.getSidebarInfo().getBody());
    }


    @RequestMapping
    public String index(Model view){
        BaseResponse<List<DictDomain>> result = dictService.getAboutInfo();
        if(result.isSuccess()){
            for(DictDomain item : result.getBody()){
                view.addAttribute(item.getDictKey(), item.getDictValue());
            }
            return baseDir + "/about";
        }
        return null;

    }
}
