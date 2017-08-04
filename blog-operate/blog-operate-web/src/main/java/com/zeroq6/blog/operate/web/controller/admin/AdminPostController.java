package com.zeroq6.blog.operate.web.controller.admin;

import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.blog.operate.manager.PostManager;
import com.zeroq6.blog.operate.service.PostService;
import com.zeroq6.common.base.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zengshengxin on 2017/8/2.
 */
@Controller
@RequestMapping("/admin/post")
public class AdminPostController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostService postService;

    @Autowired
    private PostManager postManager;

    @RequestMapping("/list")
    public String list(PostDomain postDomain, Page<PostDomain> page,  Model view){
        postService.selectPage(postDomain, page);
        view.addAttribute("page", page);
        return "/admin/postList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model view){
        PostDomain postDomain = null;
        if(id > 0){
            postDomain = postService.selectByKey(id);
        }
        view.addAttribute("post", postDomain);
        return "/admin/postEdit";
    }

    @RequestMapping("/save")
    public String save(PostDomain postDomain, Model view){
        if(postDomain.getId() == 0){
            postService.insertFillingId(postDomain);
        }else{
            postService.updateByKey(postDomain);
        }
        return "redirect:/admin/post/list";
    }


    @RequestMapping("/del/{id}")
    public String del(@PathVariable Long id, Model view){
        postManager.deleteById(id);
        return "redirect:/admin/post/list";
    }

}
