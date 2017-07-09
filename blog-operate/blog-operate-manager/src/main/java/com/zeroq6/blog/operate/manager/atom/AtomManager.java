package com.zeroq6.blog.operate.manager.atom;

import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.blog.common.enums.field.EmPostPostType;
import com.zeroq6.blog.common.enums.field.EmPostStatus;
import com.zeroq6.blog.operate.manager.DictManager;
import com.zeroq6.blog.operate.manager.PostManager;
import com.zeroq6.blog.operate.manager.velocity.VelocityManager;
import com.zeroq6.common.utils.MarkdownUtils;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuuki asuna on 2017/5/25.
 */

@Service
public class AtomManager implements InitializingBean{


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostManager postManager;

    @Autowired
    private DictManager dictManager;

    @Autowired
    private VelocityManager velocityManager;


    @Value("${atom.title}")
    private String title;
    @Value("${atom.subtitle}")
    private String subtitle;
    @Value("${atom.uri}")
    private String uri;
    @Value("${atom.site}")
    private String site;
    @Value("${atom.author}")
    private String author;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final String home = "https://6zeroq.com";

    private final String postUrlPrefix = home + "/postManager/show";

    private final String categoryUrlPrefix = home + "/categories";

    private final String tagUrlPrefix = home + "/tags";


    public void genAtomXml() {
        logger.info("开始生成atom.xml");
        try{
            //
            VelocityContext vc = new VelocityContext();
            vc.put("title", title);
            vc.put("subtitle", subtitle);
            vc.put("uri", uri);
            vc.put("site", site);
            vc.put("author", author);
            vc.put("updated", sdf.format(new Date()));
            // 文章列表
            PostDomain query = new PostDomain();
            query.setPostType(EmPostPostType.WENZHANG.value()).setStatus(EmPostStatus.YI_FABU.value());
            query.setStartIndex(0).setEndIndex(10000);
            List<PostDomain> postDomainList = postManager.selectList(query);
            for (PostDomain item : postDomainList) {
                Map<String, Object> extendMap = item.getExtendMap();
                extendMap.put("url", postUrlPrefix + "/" + item.getId());
                extendMap.put("published", sdf.format(item.getCreatedTime()));
                extendMap.put("updated", sdf.format(item.getCreatedTime()));
                extendMap.put("content", MarkdownUtils.parse(item.getContent()));
                Map<String, Object> categoryMap = new HashMap<String, Object>();
                StringBuffer summary = new StringBuffer();

            }
            vc.put("contentDomainList", postDomainList);
            String desPath = velocityManager.getResourceRootPath() + "/" + "atom.xml";
            velocityManager.merge(vc, "atom.xml.vm", desPath);
            logger.info("生成atom.xml成功, 服务端文件路径: " + desPath);
        }catch (Exception e){
            logger.error("生成atom.xml失败", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        genAtomXml();
    }
}
