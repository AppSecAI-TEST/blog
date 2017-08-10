package com.zeroq6.blog.operate.web.controller.admin;

import com.zeroq6.blog.common.domain.AttachDomain;
import com.zeroq6.blog.operate.manager.AttachManager;
import com.zeroq6.blog.operate.service.AttachService;
import com.zeroq6.common.base.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by yuuki asuna on 2017/8/2.
 */

@Controller
@RequestMapping("/admin/attach")
public class AdminAttachController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AttachService attachService;

    @Autowired
    private AttachManager attachManager;

    @RequestMapping("")
    public String list(AttachDomain attachDomain, Page<AttachDomain> page, Model view) {
        attachService.selectPage(attachDomain, page);
        view.addAttribute("page", page);
        view.addAttribute("attachDomain", attachDomain);
        return "/admin/attach/attachList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model view) {
        return "/admin/attach/attachEdit";
    }

    @RequestMapping("/save")
    public String save(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        String writeToPath = null;
        try {
            MultipartFile clientFile = files[0];
            byte[] data = clientFile.getBytes();
            String md5 = DigestUtils.md5Hex(data);

            AttachDomain attachDomainDb = attachService.selectOne(new AttachDomain().setMd5(md5), true);
            if (null != attachDomainDb) {
                logger.warn("文件{1}已存在, 跳过上传，" + clientFile.getOriginalFilename());
            } else {
                //
                writeToPath = attachManager.getUploadPath() + File.separator + clientFile.getOriginalFilename();
                FileUtils.writeByteArrayToFile(new File(writeToPath), data, false);

                //
                AttachDomain attachDomain = new AttachDomain();
                attachDomain.setName(clientFile.getOriginalFilename());
                attachDomain.setMd5(md5);
                attachDomain.setSize(clientFile.getSize());

                attachService.insert(attachDomain);
            }
        } catch (Exception e) {
            logger.error("上传文件异常, " + writeToPath, e);
            if (StringUtils.isNotBlank(writeToPath)) {
                FileUtils.deleteQuietly(new File(writeToPath));
            }
        }
        return "redirect:/admin/attach";
    }


    @RequestMapping("/delete/{id}")
    public String del(@PathVariable Long id, Model view) {
        attachManager.deleteById(id);
        return "redirect:/admin/attach";
    }

}
