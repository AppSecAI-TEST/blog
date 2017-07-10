package com.zeroq6.blog.operate.web.controller;

import com.zeroq6.blog.common.base.BaseController;
import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.blog.operate.service.PostService;
import com.zeroq6.common.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by yuuki asuna on 2017/5/24.
 */

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {


    @ModelAttribute
    public void loadState(Model model) {
        model.addAttribute("menu", "archives");
        model.addAttribute("title", "归档");
        model.addAllAttributes(postService.getSidebarInfo().getBody());
    }

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{category}")
    public String show(@PathVariable String category, Model view) {
        if (StringUtils.isBlank(category)) {
            return redirectIndex();
        }
        BaseResponse<Map<String, List<PostDomain>>> result = postService.getArchiveList(category, null);
        if (result.isSuccess()) {
            view.addAttribute("archiveMapList", result.getBody());
            view.addAttribute("classify", category);
            return baseDir + "/archives";
        }
        return null;
    }
}
