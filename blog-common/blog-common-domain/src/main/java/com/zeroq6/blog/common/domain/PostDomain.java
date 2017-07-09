package com.zeroq6.blog.common.domain;

import com.zeroq6.common.base.BaseDomain;

/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
public class PostDomain extends BaseDomain<PostDomain> {

    private static final long serialVersionUID = 1L;

    /**
    * 文章 => post
    */
    public PostDomain(){
        // 默认无参构造
    }

    /**
     * 标题
     */
    private String title;
    /**
     * 用户名
     */
    private String username;
    /**
     * 内容
     */
    private String content;
    /**
     * 文章类型，1，文章，2，留言
     */
    private Integer postType;
    /**
     * 状态，1，未发布，2，已发布
     */
    private Integer status;
    /**
     * 评论次数
     */
    private Integer commentCount;
    /**
     * 查看次数
     */
    private Integer viewCount;

    /**
     * 获取标题 title
     *
     * @return
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置标题 title
     *
     * @param title 标题
     */
    public PostDomain setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 获取用户名 username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置用户名 username
     *
     * @param username 用户名
     */
    public PostDomain setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 获取内容 content
     *
     * @return
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置内容 content
     *
     * @param content 内容
     */
    public PostDomain setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 获取文章类型，1，文章，2，留言 postType
     *
     * @return
     */
    public Integer getPostType() {
        return postType;
    }
    /**
     * 设置文章类型，1，文章，2，留言 postType
     *
     * @param postType 文章类型，1，文章，2，留言
     */
    public PostDomain setPostType(Integer postType) {
        this.postType = postType;
        return this;
    }

    /**
     * 获取状态，1，未发布，2，已发布 status
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置状态，1，未发布，2，已发布 status
     *
     * @param status 状态，1，未发布，2，已发布
     */
    public PostDomain setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取评论次数 commentCount
     *
     * @return
     */
    public Integer getCommentCount() {
        return commentCount;
    }
    /**
     * 设置评论次数 commentCount
     *
     * @param commentCount 评论次数
     */
    public PostDomain setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    /**
     * 获取查看次数 viewCount
     *
     * @return
     */
    public Integer getViewCount() {
        return viewCount;
    }
    /**
     * 设置查看次数 viewCount
     *
     * @param viewCount 查看次数
     */
    public PostDomain setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }


    /**系统生成结束,请勿修改,重新生成会覆盖*/

    /**自定义开始 */

    /**自定义结束 */
}