package com.zeroq6.blog.operate.service;

import com.zeroq6.blog.common.base.BaseManager;
import com.zeroq6.blog.common.base.BaseService;
import com.zeroq6.blog.common.dao.PostDao;
import com.zeroq6.blog.common.domain.CommentDomain;
import com.zeroq6.blog.common.domain.DictDomain;
import com.zeroq6.blog.common.domain.PostDomain;
import com.zeroq6.blog.common.domain.RelationDomain;
import com.zeroq6.blog.common.enums.field.*;
import com.zeroq6.blog.operate.manager.DictManager;
import com.zeroq6.blog.operate.manager.PostManager;
import com.zeroq6.common.base.BaseResponse;
import com.zeroq6.common.base.Page;
import com.zeroq6.common.utils.GravatarUtils;
import com.zeroq6.common.utils.MarkdownUtils;
import com.zeroq6.common.utils.MyDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**自定义开始 */

/**自定义结束 */

/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
@Service
public class PostService extends BaseService<PostDomain, Long> {

    @Autowired
    private PostManager contentManager;

    /**自定义开始 */


    private SimpleDateFormat commentFmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);


    @Autowired
    private CommentService commentService;

    @Autowired
    private PostDao contentDao;

    @Autowired
    private PostManager postManager;

    @Autowired
    private RelationService relationService;


    @Autowired
    private DictManager dictManager;

    private SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");

    /**自定义结束 */

    @Override
    public BaseManager<PostDomain, Long> getManager() {
        return contentManager;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */


    public BaseResponse<Map<String, Object>> getGuestBook() {
        try {
            PostDomain postDomain = selectOne(new PostDomain().setPostType(EmPostPostType.LIUYAN.value()));
            return show(postDomain.getId(), EmPostPostType.LIUYAN.value());
        } catch (Exception e) {
            logger.error("查询留言异常", e);
            return new BaseResponse<Map<String, Object>>(false, e.getMessage(), null);
        }

    }


    public BaseResponse<Map<String, Object>> getSidebarInfo() {
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            //
            List<DictDomain> sidebarCategories = dictManager.getDictByType(EmDictDictType.FENLEI.value());
            List<DictDomain> sidebarTags = dictManager.getDictByType(EmDictDictType.BIAOQIAN.value());
            List<DictDomain> sidebarLinks = dictManager.getDictByType(EmDictDictType.LIANJIE.value());
            PostDomain query0 = new PostDomain();
            query0.setPostType(EmPostPostType.WENZHANG.value());
            query0.setStatus(EmPostStatus.YI_FABU.value());
            query0.setStartIndex(0).setEndIndex(10);
            query0.setOrderField("created_time").setOrderFieldType("DESC");
            List<PostDomain> sidebarRecentPosts = contentManager.selectList(query0);
            //
            dataMap.put("sidebarCategories", sidebarCategories);
            dataMap.put("sidebarTags", sidebarTags);
            dataMap.put("sidebarLinks", sidebarLinks);
            dataMap.put("sidebarRecentPosts", sidebarRecentPosts);
            return new BaseResponse<Map<String, Object>>(true, "成功", dataMap);
        } catch (Exception e) {
            logger.error("查询sidebar异常", e);
            return new BaseResponse<Map<String, Object>>(false, e.getMessage(), null);
        }
    }

    /**
     * 首页文章列表
     * @param page
     * @return
     */
    public BaseResponse<Page<PostDomain>> index(String page) {
        try {
            int currentPage = 1;
            if (StringUtils.isNumeric(page)) {
                currentPage = Integer.valueOf(page);
            }
            PostDomain contentDomain = new PostDomain();
            contentDomain.setPostType(EmPostPostType.WENZHANG.value());
            contentDomain.setStatus(EmPostStatus.YI_FABU.value());
            Page<PostDomain> p = new Page<PostDomain>(currentPage, 8);
            this.selectPage(contentDomain, p);
            return new BaseResponse<Page<PostDomain>>(true, "成功", p);
        } catch (Exception e) {
            logger.error("查询文章列表异常, page: " + page, e);
            return new BaseResponse<Page<PostDomain>>(false, e.getMessage(), null);
        }
    }


    /**
     * 查看文章详情页
     * @param id
     * @return
     */
    public BaseResponse<Map<String, Object>> show(Long id, int type) {
        try {
            if (type != EmPostPostType.LIUYAN.value() && type != EmPostPostType.WENZHANG.value()) {
                throw new RuntimeException("类型错误, " + type);
            }
            if (null == id) {
                throw new RuntimeException("id不能为空, " + id);
            }
            // 文章
            PostDomain post = this.selectOne(new PostDomain().setId(id).setStatus(EmPostStatus.YI_FABU.value()).setPostType(type));
            post.getExtendMap().put("content", MarkdownUtils.parse(post.getContent()));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            // 评论
            List<CommentDomain> commentDomainList = commentService.selectList(new CommentDomain().setPostId(id).setOrderField("id").setOrderFieldType("ASC"));
            for (CommentDomain item : commentDomainList) {
                Map<String, Object> extendMap = item.getExtendMap();
                extendMap.put("avatar", GravatarUtils.getAvatar(item.getEmail()));
                extendMap.put("timeBefore", MyDateUtils.getDateBeforeNow(item.getCreatedTime()));
                extendMap.put("timeFmt", commentFmt.format(item.getCreatedTime()));
                if (item.getParentType() == EmCommentParentType.PINGLUN.value()) {
                    extendMap.put("commentParent", commentService.selectByKey(item.getParentId()));
                }
            }
            // 只有文章才查询标签，上一篇，下一篇文章
            if (type == EmPostPostType.WENZHANG.value()) {
                // 标签
                dataMap.put("tags", postManager.getTagsById(id));

                // 分类
                dataMap.put("category", postManager.getCategoryById(id));

                // 上一篇，下一篇
                PostDomain prev = contentDao.selectPrevPost(post.getId());
                PostDomain next = contentDao.selectNextPost(post.getId());
                if (null != prev) {
                    dataMap.put("prev", prev);
                }
                if (null != next) {
                    dataMap.put("next", next);
                }
            }
            dataMap.put("post", post);
            dataMap.put("commentList", commentDomainList);
            return new BaseResponse<Map<String, Object>>(true, "成功", dataMap);
        } catch (Exception e) {
            logger.error("查看文章异常, id: " + id, e);
            return new BaseResponse<Map<String, Object>>(false, e.getMessage(), null);
        }
    }


    /**
     * 归档，可指定分类，标签
     * @param category
     * @param tag
     * @return
     */
    public BaseResponse<Map<String, List<PostDomain>>> getArchiveList(String category, String tag) {
        try {
            if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(tag)) {
                throw new RuntimeException("不能同时指定分类和标签");
            }
            Map<String, List<PostDomain>> data = new HashMap<String, List<PostDomain>>();
            PostDomain query0 = new PostDomain();
            query0.setOrderField("created_time").setOrderFieldType("DESC").setPostType(EmPostPostType.WENZHANG.value()).setStatus(EmPostStatus.YI_FABU.value());
            List<String> ids = new ArrayList<String>();
            // 标签对应文章id
            if (StringUtils.isNotBlank(tag)) {
                DictDomain dictDomain = dictManager.getDictByTypeAndKey(EmDictDictType.BIAOQIAN.value(), tag);
                RelationDomain query1 = new RelationDomain();
                query1.setType(EmRelationType.WEN_ZHANG_BIAOQIAN.value());
                query1.setChildId(dictDomain.getId() + "");
                List<RelationDomain> relationDomainList = relationService.selectList(query1);
                for (RelationDomain relationDomain : relationDomainList) {
                    ids.add(relationDomain.getParentId() + "");
                }
            }
            // 分类对应文章id
            if (StringUtils.isNotBlank(category)) {
                DictDomain dictDomain = dictManager.getDictByTypeAndKey(EmDictDictType.FENLEI.value(), category);
                RelationDomain query2 = new RelationDomain();
                query2.setType(EmRelationType.WEN_ZHANG_FENLEI.value());
                query2.setChildId(dictDomain.getId() + "");
                List<RelationDomain> relationDomainList = relationService.selectList(query2);
                for (RelationDomain relationDomain : relationDomainList) {
                    ids.add(relationDomain.getParentId() + "");
                }
            }
            if(null != ids && !ids.isEmpty()){
                query0.put("idIn", ids);
            }
            List<PostDomain> contentDomainList = contentManager.selectList(query0);
            for (PostDomain post : contentDomainList) {
                String key = yyyy.format(post.getCreatedTime());
                if (null == data.get(key)) {
                    data.put(key, new ArrayList<PostDomain>());
                }
                data.get(key).add(post);
            }
            return new BaseResponse<Map<String, List<PostDomain>>>(true, "成功", data);
        } catch (Exception e) {
            logger.error("查询归档异常, categoryCode: " + category + ", tag: " + tag, e);
            return new BaseResponse<Map<String, List<PostDomain>>>(false, e.getMessage(), null);
        }
    }


    /**自定义结束 */

}
